package com.investment.config;

import com.investment.domain.InvestProduct;
import com.investment.domain.InvestSales;
import com.investment.infra.InvestProductRepository;
import com.investment.infra.InvestSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("local")
public class DataInitConfig {

    @Autowired
    InvestProductRepository investProductRepository;

    @Autowired
    InvestSalesRepository investSalesRepository;

    @PostConstruct
    public void init(){
        List<InvestProduct> products = Arrays.asList(
                InvestProduct.of("개인신용 포트폴리오", BigDecimal.valueOf(1000000L), LocalDateTime.of(LocalDate.of(2021,6,01), LocalTime.MIN) , LocalDateTime.of(LocalDate.of(2021,6,21), LocalTime.MIN))
                ,InvestProduct.of("부동산 포트톨리오", BigDecimal.valueOf(1000000L), LocalDateTime.of(LocalDate.of(2021,6,02), LocalTime.MIN) , LocalDateTime.of(LocalDate.of(2021,6,22), LocalTime.MIN))
        );

        List<InvestProduct> savedProducts = investProductRepository.saveAll(products);

        savedProducts.stream().forEach( s -> {
            investSalesRepository.save(
                    InvestSales.of(s.getProductId(), BigDecimal.valueOf(0)
                            ,0L));
        });

    }
}
