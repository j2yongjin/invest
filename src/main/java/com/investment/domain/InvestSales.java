package com.investment.domain;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invest_sales")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor()
public class InvestSales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_no" , nullable = false)
    private Long salesNo;

    @Column(name = "prodcut_id" , nullable = false)
    private Long productId;

    @Column(name = "invested_amt" , nullable = false)
    private BigDecimal investedAmount;

    @Column(name = "invested_user_cnt" , nullable = false)
    private Long investedUserCnt;


    public static InvestSales of(Long productId , BigDecimal investedAmount , Long investedUserCnt){

        return InvestSales.builder().productId(productId).investedAmount(investedAmount)
                .investedUserCnt(investedUserCnt).build();
    }

}
