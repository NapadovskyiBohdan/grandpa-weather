package com.grandpaweather.config.dbmigrations;

import com.grandpaweather.domain.Mediator;
import com.grandpaweather.domain.Trigger;
import com.grandpaweather.repository.MediatorRepository;
import com.grandpaweather.repository.TriggerRepository;
import io.mongock.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@ChangeUnit(id = "trigger-initializer", order = "", author = "Bohdan")
public class TriggerInitializerChangeUnit {

    private static final Logger log = LoggerFactory.getLogger(TriggerInitializerChangeUnit.class);
    private final MongoTemplate template;
    private final MediatorRepository mediatorRepository;

    public TriggerInitializerChangeUnit(MongoTemplate template, MediatorRepository mediatorRepository) {
        this.template = template;
        this.mediatorRepository = mediatorRepository;
    }

    @BeforeExecution
    public void beforeExecution(MongoTemplate mongoTemplate) {
        log.info("Creating collection in mongo db");
        mongoTemplate.createCollection("triggers");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("triggers");
    }

    @Execution
    public void execution(TriggerRepository triggerRepository) {
        log.info("Saving triggers in mongo collection");
        List<Trigger> triggers = createListOfTriggers();
        triggerRepository.saveAll(triggers);
    }

    @RollbackExecution
    public void rollbackExecution(TriggerRepository triggerRepository) {
        log.error("Error by creating triggers");
        triggerRepository.deleteAll();
    }

    private List<Trigger> createListOfTriggers() {
        log.info("Creating triggers for saving in mongo collection");
        List<Trigger> triggers = new ArrayList<>();
        List<String> coldSnap = getMediatorIdsByMediatorNameNameList(Arrays.asList("Головний біль", "Біль у суглобах", "Біль у загоєних переломах", "Задишка, ускладнене дихання", "Змінити резину", "Народна медицина"));
        List<String> fog = getMediatorIdsByMediatorNameNameList(Arrays.asList("Задишка, ускладнене дихання", "Народна медицина"));
        List<String> thunderstorm = getMediatorIdsByMediatorNameNameList(Arrays.asList("Головний біль", "Біль у суглобах", "Купити дощовик/парасолю", "Народна медицина"));
        List<String> rain = getMediatorIdsByMediatorNameNameList(Arrays.asList("Головний біль", "Біль у суглобах", "Купити дощовик/парасолю"));
        List<String> snow = getMediatorIdsByMediatorNameNameList(Arrays.asList("Головний біль", "Біль у суглобах", "Біль у загоєних переломах", "Змінити резину", "Народна медицина"));


        triggers.add(Trigger.buildByNameAndDescription("coldsnap", "Snap cold", coldSnap));
        triggers.add(Trigger.buildByNameAndDescription("suddenwarming", "Sudden warming", coldSnap));
        triggers.add(Trigger.buildByNameAndDescription("fog", "Fog", fog));
        triggers.add(Trigger.buildByNameAndDescription("sand", "Sand", fog));
        triggers.add(Trigger.buildByNameAndDescription("thunderstorm", "Thunderstorm", thunderstorm));
        triggers.add(Trigger.buildByNameAndDescription("snow", "Snow", snow));
        triggers.add(Trigger.buildByNameAndDescription("dust", "Dust", fog));
        triggers.add(Trigger.buildByNameAndDescription("suddenrain", "Sudden rain", thunderstorm));
        triggers.add(Trigger.buildByNameAndDescription("clouds", "Clouds", getMediatorIdsByMediatorNameNameList(Arrays.asList("Народна медицина"))));
        triggers.add(Trigger.buildByNameAndDescription("rain", "Rain", rain));
        return triggers;
    }

    private List<String> getMediatorIdsByMediatorNameNameList(List<String> mediators) {
        return this.mediatorRepository.getMediatorByMediatorIn(mediators).stream()
                .map(Mediator::getId)
                .collect(Collectors.toList());
    }
}
