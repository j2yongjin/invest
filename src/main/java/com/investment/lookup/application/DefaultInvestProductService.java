package com.investment.lookup.application;

import com.investment.lookup.domain.InvestedProduct;
import com.investment.lookup.infra.InvestedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultInvestProductService implements InvestProductService{

    private final InvestedProductRepository investedProductRepository;

    public List<InvestedProduct> getInvestProduct(){
        return investedProductRepository.findAllByInvestDate(LocalDateTime.now());
    }

}
