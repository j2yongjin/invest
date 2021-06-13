package com.investment.infra;

import com.investment.domain.InvestProduct;
import com.investment.domain.InvestSales;
import com.investment.domain.QInvestProduct;
import com.investment.domain.QInvestSales;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.internal.compiler.ast.DoubleLiteral;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.investment.domain.QInvestProduct.*;
import static com.investment.domain.QInvestSales.*;

@Repository
@RequiredArgsConstructor
public class InvestSalesRepositoryImpl implements InvestSalesRepository{
    private final JPAQueryFactory queryFactory;

    private final EntityManager entityManager;

    @Transactional
    public void save(InvestSales investSales){
        entityManager.persist(investSales);
    }
    public InvestSales findOneByProductId(Long productId){
        return queryFactory.select(investSales)
                .from(investSales)
                .where(investSales.productId.eq(productId))
                .fetchOne();
    }

    @Transactional
    public Long updateSales(BigDecimal amount , Long productId){

        return queryFactory.update(investSales)
                .where(investSales.productId.eq(productId))
                .set(investSales.investedAmount, investSales.investedAmount.add(amount))
                .set(investSales.investedUserCnt,investSales.investedUserCnt.add(1L).longValue())
                .execute();
    }

}
