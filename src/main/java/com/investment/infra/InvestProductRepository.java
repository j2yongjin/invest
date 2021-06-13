package com.investment.infra;

import com.investment.domain.InvestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestProductRepository extends JpaRepository<InvestProduct,Long> {

}
