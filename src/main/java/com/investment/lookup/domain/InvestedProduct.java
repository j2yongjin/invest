package com.investment.lookup.domain;

import com.investment.domain.InvestEventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@AllArgsConstructor
//@Builder
@NoArgsConstructor
@Getter
public class InvestedProduct {

    private Long productId;
    private String title;
    private BigDecimal totalInvestingAmount;
    private BigDecimal investedAmount;
    private Long investedPersonCnt;
    private InvestEventStatus status;

    public InvestedProduct(Long productId, String title, BigDecimal totalInvestingAmount, BigDecimal investedAmount, Long investedPersonCnt, LocalDateTime startedAt , LocalDateTime finishedAt) {
        BigDecimal investedAmountNonNull = investedAmount == null ? BigDecimal.valueOf(0) : investedAmount;
        long investedPersonCntNonNull = investedPersonCnt == null ? 0 : investedPersonCnt;
        this.productId = productId;
        this.title = title;
        this.totalInvestingAmount = totalInvestingAmount;
        this.investedAmount = investedAmountNonNull;
        this.investedPersonCnt = investedPersonCntNonNull;

        this.status = InvestEventStatus.NONE;
        if(LocalDateTime.now().isAfter(startedAt) && LocalDateTime.now().isBefore(finishedAt)) this.status = InvestEventStatus.PROGRESS;
        else if(LocalDateTime.now().isAfter(finishedAt)) this.status = InvestEventStatus.COMPLETED;
        else this.status = InvestEventStatus.NONE;

        if(totalInvestingAmount.compareTo(investedAmountNonNull) == 0 && totalInvestingAmount.compareTo(investedAmountNonNull) == 1 )
            this.status = InvestEventStatus.COMPLETED;
    }
}
