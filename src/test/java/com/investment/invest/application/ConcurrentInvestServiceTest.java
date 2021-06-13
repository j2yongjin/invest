package com.investment.invest.application;

import com.investment.domain.InvestSales;
import com.investment.infra.InvestSalesDetailRepository;
import com.investment.infra.InvestSalesRepository;
import com.investment.invest.model.InvestRequest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConcurrentInvestServiceTest {


    ExecutorService executorService = null;

    @Autowired
    private InvestService investService;

    @Autowired
    InvestSalesRepository investSalesRepository;

    @Autowired
    InvestSalesDetailRepository salesDetailRepository;

    int count = 0;
    int threadCount = 0;
    @Before
    public void init(){
        count = 10;
        threadCount =10;
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    @Test
    public void givenConcurrent_whenInvest_thenSuccess() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int th = 0;th<threadCount;th++) {

            executorService.execute(() -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < count; i++) {
                    InvestRequest request = InvestRequest.builder()
                            .monetaryAmount(new InvestRequest.MonetaryAmount(1))
                            .product(new InvestRequest.Product(1))
                            .build();
                    // when
                    investService.invest(request, 1004L);
                }
            });
        }

        executorService.awaitTermination(2,TimeUnit.SECONDS);

        InvestSales investSales = investSalesRepository.findOneByProductId(1L);

        assertThat(investSales.getInvestedAmount().doubleValue() , Matchers.equalTo(Double.valueOf(100.0)));
    }
}
