package com.investment.infra;

import com.investment.domain.InvestProduct;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class InvestProductRepositoryTest {


    @Autowired
    InvestProductRepository investProductRepository;

    @Test
    public void whenSave_thenSuccess(){

        LocalDateTime startedAt = LocalDateTime.of(LocalDate.of(2021,03,01), LocalTime.MIN);
        LocalDateTime finishedAt = LocalDateTime.of(LocalDate.of(2021,03,01), LocalTime.MIN);
        String title = "개인신용 포트폴리오";
        BigDecimal totalInvestingAmount = BigDecimal.valueOf(1000000L);

        InvestProduct investProduct = InvestProduct.of(title, totalInvestingAmount,startedAt,finishedAt);

        // when
        InvestProduct saveResult  = investProductRepository.save(investProduct);

        // then
        assertThat(saveResult , Matchers.notNullValue());
        assertThat(saveResult.getProductId() , Matchers.greaterThan(0L));

    }

}