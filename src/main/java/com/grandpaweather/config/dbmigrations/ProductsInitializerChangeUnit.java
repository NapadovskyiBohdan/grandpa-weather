package com.grandpaweather.config.dbmigrations;

import com.grandpaweather.domain.Trigger;
import com.grandpaweather.repository.ProductRepository;
import com.grandpaweather.domain.Product;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.RollbackExecution;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.Execution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@ChangeUnit(id="product-initializer", order = "1", author = "Bohdan")
public class ProductsInitializerChangeUnit {
    private static final Logger log = LoggerFactory.getLogger(ProductsInitializerChangeUnit.class);

    @BeforeExecution
    public void beforeExecution(MongoTemplate mongoTemplate) {
        log.info("Creating collection in mongo db");
        mongoTemplate.createCollection("products");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("products");
    }

    @Execution
    public void execution(ProductRepository repository) {
        log.info("Saving products in mongo collection");
        List<Product> products = createListOfProducts();
        repository.saveAll(products);

    }

    @RollbackExecution
    public void rollbackExecution(ProductRepository repository) {
        log.error("Error by creating triggers");
        repository.deleteAll();
    }


    private List<Product> createListOfProducts() {
        log.info("Creating products for saving in mongo collection");
        List<Product> products = new ArrayList<>();
        products.add(Product.buildByNameAndDescription("Знеболюючі", "Цитрамон"));
        products.add(Product.buildByNameAndDescription("Знеболюючі", "Нурофен"));
        products.add(Product.buildByNameAndDescription("Знеболюючі", "Спазмалгон"));

        products.add(Product.buildByNameAndDescription("Знеболюючі місцевої дії", "Фастум гель"));
        products.add(Product.buildByNameAndDescription("Знеболюючі місцевої дії", "Диклофенак"));
        products.add(Product.buildByNameAndDescription("Знеболюючі місцевої дії", "Долорен"));

        products.add(Product.buildByNameAndDescription("Кліматичні прилади", "Кондиціонери"));
        products.add(Product.buildByNameAndDescription("Кліматичні прилади", "Зволожувачі повітря"));
        products.add(Product.buildByNameAndDescription("Кліматичні прилади", "Очишувачі повітря"));


        products.add(Product.buildByNameAndDescription("Інші товари", "Парасоля"));
        products.add(Product.buildByNameAndDescription("Інші товари", "Дощовик"));
        products.add(Product.buildByNameAndDescription("Інші товари", "Водовідштовхуючий крем/спрей для взуття"));
        products.add(Product.buildByNameAndDescription("Інші товари", "Автомобільні шини"));
        products.add(Product.buildByNameAndDescription("Інші товари", "Шиномонтаж"));
        products.add(Product.buildByNameAndDescription("Інші товари", "Фольга"));



        return products;
    }


}
