package com.grandpaweather.service.impl;

import com.grandpaweather.domain.Mediator;
import com.grandpaweather.domain.Trigger;
import com.grandpaweather.domain.WeatherData;
import com.grandpaweather.domain.WeatherTriggerRelation;
import com.grandpaweather.domain.dto.MediatorDTO;
import com.grandpaweather.domain.dto.ProductDTO;
import com.grandpaweather.domain.dto.TriggerDTO;
import com.grandpaweather.domain.dto.WeatherTriggerDTO;
import com.grandpaweather.repository.MediatorRepository;
import com.grandpaweather.repository.ProductRepository;
import com.grandpaweather.repository.TriggerRepository;
import com.grandpaweather.repository.WeatherTriggerRelationRepository;
import com.grandpaweather.service.WeatherTriggerBuilder;
import com.grandpaweather.service.WeatherTriggerManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherTriggerManagerImpl implements WeatherTriggerManager {

    private final WeatherTriggerBuilder builder;
    private final ProductRepository productRepository;
    private final TriggerRepository triggerRepository;
    private final MediatorRepository mediatorRepository;
    private final WeatherTriggerRelationRepository weatherTriggerRelationRepository;
    private static final Logger log = LoggerFactory.getLogger(WeatherTriggerManagerImpl.class);


    @Override
    public List<WeatherTriggerRelation> updateTriggersFromResponse(List<WeatherData> responses) {
        log.info("Update triggers from http response ");
        Map<LocalDateTime, WeatherTriggerRelation> relationHashMap = updateRelationsFromResponse(responses);
        List<WeatherTriggerRelation> relations = calculateTrigger(relationHashMap);
        return weatherTriggerRelationRepository.saveAll(relations);
    }

    @Override
    public WeatherTriggerDTO buildWeatherTriggerDTO(WeatherTriggerRelation relation) {
        TriggerDTO trigger = triggerRepository.getTriggerById(relation.getTrigger_id())
                .map(t -> TriggerDTO.buildDtoFromEntity(t, buildMediatorsDTO(t.getMediators_id())))
                .orElseThrow();
        return WeatherTriggerDTO.buildDTOFromEntity(relation, trigger);

    }

    private List<MediatorDTO> buildMediatorsDTO(List<String> mediatorsId) {
        List<Mediator> mediators = mediatorRepository.getMediatorByIdIn(mediatorsId);
        return mediators.stream()
                .map(m -> MediatorDTO.buildDTOFromEntity(m, getProductsByMediator(m, getProductsIdFromMediatorList(mediators))))
                .collect(Collectors.toList());
    }

    private List<ProductDTO> getProductsByMediator(Mediator mediator, List<String> productsId) {
        List<ProductDTO> productsDTO = getProductsByIds(productsId);
        return mediator.getProducts_id().stream()
                .map(id->getProductDTObyId(productsDTO, id))
                .collect(Collectors.toList());
    }

    private ProductDTO getProductDTObyId(List<ProductDTO> productsDTO, String id) {
        return productsDTO.stream().filter(dto->dto.getId().equals(id)).findFirst()
                .orElseThrow();
    }

    private List<String> getProductsIdFromMediatorList(List<Mediator> mediators) {
        return mediators.stream()
                .map(Mediator::getProducts_id)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ProductDTO> getProductsByIds(List<String> productsId) {
        return productRepository.getProductsByIdIn(productsId).stream()
                .map(ProductDTO::buildDTOFromEntity)
                .toList();
    }


    private Map<LocalDateTime, WeatherTriggerRelation> updateRelationsFromResponse(List<WeatherData> responses) {
        Map<LocalDateTime, WeatherTriggerRelation> relationHashMap = buildWatherMap(responses);
        updateRelation(relationHashMap, responses);
        return relationHashMap;
    }


    private Map<LocalDateTime, WeatherTriggerRelation> buildWatherMap(List<WeatherData> responses) {
        List<LocalDateTime> dates = responses.stream()
                .map(WeatherData::getDate)
                .toList();
        Long cityId = responses.stream().findFirst().map(r -> r.getCity().getId()).orElseThrow();
        return weatherTriggerRelationRepository.getAllByDateInAndCityId(dates, cityId)
                .stream()
                .collect(Collectors.toMap(WeatherTriggerRelation::getDate, Function.identity()));
    }

    private void updateRelation(Map<LocalDateTime, WeatherTriggerRelation> relationMap, List<WeatherData> responses) {
        responses.forEach(
                response -> {
                    if (relationMap.containsKey(response.getDate())) {
                        updateTriggerRelation(response, relationMap);
                    } else {
                        addResponseToMap(response, relationMap);
                    }
                }
        );
    }

    private void addResponseToMap(WeatherData response, Map<LocalDateTime, WeatherTriggerRelation> relationMap) {
        WeatherTriggerRelation relation = WeatherTriggerRelation.buildFromResponse(response);
        relationMap.put(relation.getDate(), relation);
    }

    private void updateTriggerRelation(WeatherData response, Map<LocalDateTime, WeatherTriggerRelation> relationMap) {
        WeatherTriggerRelation relation = relationMap.get(response.getDate());
        relation.updateFromResponse(response);
    }

    private List<WeatherTriggerRelation> calculateTrigger(Map<LocalDateTime, WeatherTriggerRelation> relationMap) {
        relationMap.keySet()
                .forEach(key -> {
                    LocalDateTime nextDate = key.plusDays(1l);
                    WeatherTriggerRelation currentDayData = relationMap.get(key);
                    Trigger trigger = builder.getTriggerByName(currentDayData.getWeather_main());
                    if (relationMap.get(nextDate) != null)
                        trigger = builder.buildTriggerByWeatherData(currentDayData, relationMap.get(nextDate));
                    currentDayData.setTrigger_id(trigger.getId());
                });
        return relationMap.keySet().stream().map(relationMap::get).collect(Collectors.toList());
    }


}
