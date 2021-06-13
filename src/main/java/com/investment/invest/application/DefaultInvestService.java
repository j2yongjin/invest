package com.investment.invest.application;

import com.investment.application.InvestServiceException;
import com.investment.domain.InvestProduct;
import com.investment.domain.InvestSalesDetail;
import com.investment.infra.InvestProductRepository;
import com.investment.infra.InvestSalesDetailRepository;
import com.investment.invest.infra.InvestRedisRepository;
import com.investment.invest.model.InvestRequest;
import com.investment.support.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DefaultInvestService implements InvestService{

    private final InvestProductRepository investProductRepository;
    private final InvestRedisRepository redisRepository;
    private final InvestSalesDetailRepository salesDetailRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public void invest(InvestRequest request,Long userId) {

        double amount = request.getMonetaryAmount().getValue().doubleValue();

        InvestProduct investProduct = investProductRepository.findById(request.getProduct().getIdentifier().longValue())
        .orElseThrow(() -> new InvestServiceException(ErrorCode.NOT_FOUND_PRODUCT));

        if(investProduct.isExpireInvestDate()) throw new InvestServiceException(ErrorCode.EXPIRED_DATE);


        Boolean incrementResult = redisRepository.checkAndIncrement(investProduct, amount);
        if(incrementResult.equals(Boolean.FALSE)) throw new InvestServiceException(ErrorCode.EXCEED_INVESTING_AMOUNT," 투자 금액 모집이 끝났습니다.");

        InvestSalesDetail salesDetail = InvestSalesDetail.of(investProduct.getProductId(),BigDecimal.valueOf(amount),userId);
        salesDetailRepository.save(salesDetail);

        publisher.publishEvent(new InvestSalesEvent(investProduct, amount));
    }
}
