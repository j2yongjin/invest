package com.investment.lookup.infra;

import com.investment.domain.InvestProduct;
import com.investment.lookup.domain.InvestedProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.investment.domain.QInvestProduct.investProduct;
import static com.investment.domain.QInvestSales.investSales;

@Repository
@RequiredArgsConstructor
public class InvestedProductRepositoryImpl implements InvestedProductRepository{

    private final JPAQueryFactory queryFactory;

    public List<InvestedProduct> findAllByInvestDate(LocalDateTime nowTime) {

        return queryFactory.select(Projections.constructor(InvestedProduct.class,investProduct.productId,investProduct.title
                ,investProduct.totalInvestingAmount
                ,investSales.investedAmount
                , investSales.investedUserCnt
                ,investProduct.startedAt , investProduct.finishedAt))
                .from(investProduct).leftJoin(investSales).on(investProduct.productId.eq(investSales.productId))
//                .where(investProduct.startedAt.goe(nowTime).and(investProduct.finishedAt.loe(nowTime)))
                .where(investProduct.startedAt.loe(nowTime).and(investProduct.finishedAt.goe(nowTime)))
                .fetch();

    }
}
