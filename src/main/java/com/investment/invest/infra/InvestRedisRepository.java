package com.investment.invest.infra;

import com.investment.domain.InvestProduct;

import java.math.BigDecimal;

public interface InvestRedisRepository {
    Boolean checkAndIncrement(InvestProduct product, Double amount);
    Double getTotalInvestedAmount(InvestProduct product);
    void save(InvestProduct product,Double amount);
}
