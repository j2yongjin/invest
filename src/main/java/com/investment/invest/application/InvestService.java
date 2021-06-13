package com.investment.invest.application;

import com.investment.invest.model.InvestRequest;

public interface InvestService {
    void invest(InvestRequest investRequest,Long userId);
}
