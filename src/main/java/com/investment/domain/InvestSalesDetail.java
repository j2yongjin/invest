package com.investment.domain;


import com.investment.support.converter.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invest_details")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor()
public class InvestSalesDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invest_no" , nullable = false)
    private Long investNo;

    @Column(name = "product_id" , nullable = false)
    private Long productId;

    @Column(name = "amount" , nullable = false)
    private BigDecimal amount;

    @Column(name = "user_id" , length = 30,nullable = false)
    private Long userid;

    @Column(name="invest_dttm" , nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime investDttm;

    public static InvestSalesDetail of(Long productId , BigDecimal amount,Long userId){
        return InvestSalesDetail.builder()
                .amount(amount)
                .productId(productId)
                .userid(userId)
                .investDttm(LocalDateTime.now())
                .build();
    }
}
