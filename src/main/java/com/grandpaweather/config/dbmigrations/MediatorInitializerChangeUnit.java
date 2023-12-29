package com.grandpaweather.config.dbmigrations;

import com.grandpaweather.domain.Mediator;
import com.grandpaweather.domain.Product;
import com.grandpaweather.domain.Trigger;
import com.grandpaweather.repository.MediatorRepository;
import com.grandpaweather.repository.ProductRepository;
import com.grandpaweather.repository.TriggerRepository;
import io.mongock.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@ChangeUnit(id = "mediator-initializer", order = "2", author = "Bohdan")
public class MediatorInitializerChangeUnit {
    private static final Logger log = LoggerFactory.getLogger(MediatorInitializerChangeUnit.class);
    private final MongoTemplate template;
    private final ProductRepository productRepository;


    public MediatorInitializerChangeUnit(MongoTemplate template, ProductRepository productRepository) {
        this.template = template;
        this.productRepository = productRepository;
    }

    @BeforeExecution
    public void beforeExecution() {
        log.info("Creating collection in mongo db");
        this.template.createCollection("mediators");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        this.template.dropCollection("mediators");
    }

    @Execution
    public void execution(MediatorRepository repository) {
        log.info("Saving mediators in mongo collection");
        List<Mediator> mediators = createListOfMediators();
        repository.saveAll(mediators);
    }

    @RollbackExecution
    public void rollbackExecution(MediatorRepository repository) {
        log.error("Error by creating mediators");
        repository.deleteAll();
    }

    private List<Mediator> createListOfMediators() {
        log.info("Creating mediators for saving in mongo collection");
        List<Mediator> mediators = new ArrayList<>();
        List<String> painkillers = getProductIdsByProductNameList(Arrays.asList("Цитрамон","Нурофен", "Спазмалгон"));
        List<String> LocalPainRelievers = getProductIdsByProductNameList(Arrays.asList("Цитрамон","Нурофен", "Спазмалгон"));
        List<String> clearAir = getProductIdsByProductNameList(Arrays.asList("Кондиціонери","Зволожувачі повітря", "Очишувачі повітря"));
        List<String> wiseAdvice = getProductIdsByProductNameList(Arrays.asList("Парасоля","Дощовик", "Водовідштовхуючий крем/спрей для взуття"));;

        mediators.add(Mediator.createMediatorByNameAndProducts("Головний біль", "Health",painkillers));
        mediators.add(Mediator.createMediatorByNameAndProducts("Біль у суглобах","Health", LocalPainRelievers));
        mediators.add(Mediator.createMediatorByNameAndProducts("Біль у загоєних переломах","Health", LocalPainRelievers));
        mediators.add(Mediator.createMediatorByNameAndProducts("Задишка, ускладнене дихання","Health", clearAir));
        mediators.add(Mediator.createMediatorByNameAndProducts("Купити дощовик/парасолю","Wise advice", wiseAdvice));
        mediators.add(Mediator.createMediatorByNameAndProducts("Змінити резину","Wise advice", getProductIdsByProductNameList(Arrays.asList("Автомобільні шини", "Шиномонтаж"))));
        mediators.add(Mediator.createMediatorByNameAndProducts("Шапочка з фольги","Funny tips", getProductIdsByProductNameList(Arrays.asList("Фольга"))));
        mediators.add(Mediator.createMediatorByNameAndProducts("Народна медицина","Funny tips", new ArrayList<>()));
        return mediators;
    }

    private List<String> getProductIdsByProductNameList(List<String> products) {
        return this.productRepository.getProductByNameIn(products).stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }


}
