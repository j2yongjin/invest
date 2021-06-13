package com.investment.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberInvested {

    private Long productId;
    private String title;
    private BigDecimal totalInvestingAmount;
    private BigDecimal investedAmount;
    private LocalDateTime investDatetime;

}
