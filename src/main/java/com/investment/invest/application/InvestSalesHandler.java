package com.investment.invest.application;

import com.investment.domain.InvestSales;
import com.investment.infra.InvestSalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvestSalesHandler{

    private final InvestSalesRepository salesRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void saveInvestSales(InvestSalesEvent event) {
        salesRepository.updateSales(BigDecimal.valueOf(event.getAmount()),event.getInvestProduct().getProductId());
        log.info("saved investSales : {} " , event.getInvestProduct());
    }
}
