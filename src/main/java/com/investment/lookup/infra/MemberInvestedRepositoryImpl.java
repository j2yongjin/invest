package com.investment.lookup.infra;

import com.investment.domain.QInvestProduct;
import com.investment.domain.QInvestSalesDetail;
import com.investment.lookup.domain.MemberInvested;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.investment.domain.QInvestProduct.*;
import static com.investment.domain.QInvestSalesDetail.*;

@Repository
@RequiredArgsConstructor
public class MemberInvestedRepositoryImpl implements MemberInvestedRepository {

    private final JPAQueryFactory queryFactory;

    public List<MemberInvested> findAllByUserid(Long userid){
        return queryFactory.select(Projections.constructor(MemberInvested.class,investProduct.productId,investProduct.title
        ,investProduct.totalInvestingAmount,investSalesDetail.amount,investSalesDetail.investDttm))
                .from(investSalesDetail).join(investProduct).on(investSalesDetail.productId.eq(investProduct.productId))
                .where(investSalesDetail.userid.eq(userid))
                .fetch();

    }

}
