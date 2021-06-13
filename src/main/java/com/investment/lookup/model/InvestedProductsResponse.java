package com.investment.lookup.model;

import com.investment.lookup.domain.InvestedProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class InvestedProductsResponse {
    private List<InvestedProduct> investedProducts;
}
