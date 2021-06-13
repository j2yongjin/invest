package com.investment.invest.application;

import com.investment.domain.InvestProduct;
import com.investment.invest.model.InvestRequest;
import lombok.Getter;

@Getter
public class InvestSalesEvent  {


    private InvestProduct investProduct;
    private Double amount;


    public InvestSalesEvent(InvestProduct investProduct, Double amount) {
        this.investProduct = investProduct;
        this.amount = amount;
    }
}
