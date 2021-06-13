package com.investment.infra;

import com.investment.domain.InvestSalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestSalesDetailRepository extends JpaRepository<InvestSalesDetail,Long> {

}
