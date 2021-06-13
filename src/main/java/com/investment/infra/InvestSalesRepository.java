package com.investment.infra;

import com.investment.domain.InvestSales;
import com.investment.lookup.domain.InvestedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface InvestSalesRepository {
    void save(InvestSales investSales);
    InvestSales findOneByProductId(Long productId);
    Long updateSales(BigDecimal amount , Long productId);
}
