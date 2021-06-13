package com.investment.invest.infra;

import com.investment.domain.InvestProduct;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvestRedisRepositoryImplTest {

    @Autowired
    InvestRedisRepository investRedisRepository;


    @Test
    public void whenCheckAndIncrement_thenIncrement(){


        Double givenValue = 100.0;

        InvestProduct investProduct = InvestProduct.builder()
                .productId(2L)
                .build();

        // when
        investRedisRepository.checkAndIncrement(investProduct, 100.0);


        // then
        Double amount = investRedisRepository.getTotalInvestedAmount(investProduct);

        assertThat(amount, Matchers.equalTo(givenValue));
    }

    @Test
    public void whenCheckAndIncrement_then2TimeIncrement(){

        Double givenValue = 100.0;

        InvestProduct investProduct = InvestProduct.builder()
                .productId(2L)
                .totalInvestingAmount(BigDecimal.valueOf(500.0))
                .build();

        // when
        investRedisRepository.checkAndIncrement(investProduct, givenValue);

        investRedisRepository.checkAndIncrement(investProduct, givenValue);


        // then
        Double amount = investRedisRepository.getTotalInvestedAmount(investProduct);

        assertThat(amount, Matchers.equalTo(givenValue*2));
    }

    @Test
    public void whenCheckAndIncrement_thenExceed(){

        Double givenValue = 100.0;

        InvestProduct investProduct = InvestProduct.builder()
                .productId(2L)
                .totalInvestingAmount(BigDecimal.valueOf(300.0))
                .build();

        // when
        investRedisRepository.checkAndIncrement(investProduct, givenValue);
        investRedisRepository.checkAndIncrement(investProduct, givenValue);
        investRedisRepository.checkAndIncrement(investProduct, givenValue);
        Boolean result = investRedisRepository.checkAndIncrement(investProduct, givenValue);

        // then
        assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

}