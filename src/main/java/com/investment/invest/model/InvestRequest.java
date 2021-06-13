package com.investment.invest.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.investment.application.InvestServiceException;
import com.investment.application.RequestValidate;
import com.investment.support.error.ErrorCode;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
@Builder
@AllArgsConstructor
public class InvestRequest implements RequestValidate {

    private Product product;
    private MonetaryAmount monetaryAmount;


    @NoArgsConstructor
    @Getter
    @ToString
    public static class Product{
        private Number identifier;

        public Product(Number identifier) {
            this.identifier = identifier;
        }
    }

    @NoArgsConstructor
    @ToString
    @Getter
    public static class MonetaryAmount {
        public MonetaryAmount(Number value) {
            this.value = value;
        }

        private Number value;
    }

    @Override
    public void validate() {
        if(Objects.isNull(product) || Objects.isNull(product.identifier) || product.identifier.longValue() <=0L)
            throw new InvestServiceException(ErrorCode.INVALID_PARAMETER,"product.identifier 값이 유효하지 않습니다.");

        if(Objects.isNull(monetaryAmount) || Objects.isNull(monetaryAmount.value) || monetaryAmount.value.doubleValue() <=0)
            throw new InvestServiceException(ErrorCode.INVALID_PARAMETER,"monetaryAmount.value 값이 유효하지 않습니다.");
    }
}
