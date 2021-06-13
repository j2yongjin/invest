package com.investment.lookup.infra;

import com.investment.lookup.domain.InvestedProduct;

import java.time.LocalDateTime;
import java.util.List;

public interface InvestedProductRepository {
    List<InvestedProduct> findAllByInvestDate(LocalDateTime nowTime);
}
