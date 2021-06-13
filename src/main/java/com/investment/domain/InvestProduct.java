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
@Table(name = "invest_product")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor()
public class InvestProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodcut_id" , nullable = false)
    private Long productId;

    @Column(name="title" , length = 200, nullable = false)
    private String title;

    @Column(name = "total_investing_amount" , nullable = false)
    private BigDecimal totalInvestingAmount;

    @Column(name = "started_at" , nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime finishedAt;


    public static InvestProduct of(String title , BigDecimal totalInvestingAmount , LocalDateTime startedAt , LocalDateTime finishedAt){
        return InvestProduct.builder()
                .title(title)
                .totalInvestingAmount(totalInvestingAmount)
                .startedAt(startedAt)
                .finishedAt(finishedAt)
                .build();
    }

    public Boolean isExceedProductAmount(BigDecimal amount){
        int result = totalInvestingAmount.compareTo(amount);
        if(result == -1) return Boolean.FALSE;

        return Boolean.TRUE;
    }

    public Boolean isExpireInvestDate(){
        if(LocalDateTime.now().isAfter(finishedAt)) return Boolean.TRUE;
        return Boolean.FALSE;
    }


    public String getRedisKey(String prefix){
        return prefix+ this.getProductId();
    }



}
