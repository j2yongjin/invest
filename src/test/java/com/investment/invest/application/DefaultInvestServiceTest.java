package com.investment.invest.application;

import com.investment.application.InvestServiceException;
import com.investment.domain.InvestSales;
import com.investment.infra.InvestSalesDetailRepository;
import com.investment.infra.InvestSalesRepository;
import com.investment.invest.model.InvestRequest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultInvestServiceTest {

    @Autowired
    private InvestService investService;

    @Autowired
    InvestSalesRepository investSalesRepository;

    @Autowired
    InvestSalesDetailRepository salesDetailRepository;

    @Test
    public void whenInvest_thenSuccess() throws InterruptedException {
        // given
        InvestRequest request = InvestRequest.builder()
                .monetaryAmount(new InvestRequest.MonetaryAmount(100))
                .product(new InvestRequest.Product(1))
                .build();

        // when
        investService.invest(request,1004L);

        Thread.sleep(1000L);

        // then
        InvestSales investSales = investSalesRepository.findOneByProductId(request.getProduct().getIdentifier().longValue());

        assertThat(investSales.getProductId(), Matchers.equalTo(1L));
    }

    @Test(expected = InvestServiceException.class)
    public void whenInvest_thenInvestServiceException_notfound_product(){

        // given
        InvestRequest request = InvestRequest.builder()
                .monetaryAmount(new InvestRequest.MonetaryAmount(100))
                .product(new InvestRequest.Product(10))
                .build();

        // when
        investService.invest(request,1004L);
    }

    @Test(expected = InvestServiceException.class)
    public void whenInvest_thenInvestServiceException_exceed_amount(){

        // given
        InvestRequest request = InvestRequest.builder()
                .monetaryAmount(new InvestRequest.MonetaryAmount(1000000))
                .product(new InvestRequest.Product(1))
                .build();

        // when
        investService.invest(request,1004L);
        investService.invest(request,1004L);
    }

}