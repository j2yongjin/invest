package com.investment.invest.infra;

import com.investment.domain.InvestProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InvestRedisRepositoryImpl implements InvestRedisRepository{

    private final RedisTemplate redisTemplate;
    private final RedisScript redisScript;

    private static final String INVEST_PRODUCT_PREFIX = "invest-product-";

    @Override
    public Boolean checkAndIncrement(final InvestProduct product, final Double amount) {
        Boolean result = (Boolean) redisTemplate.execute(redisScript,
                Arrays.asList(product.getRedisKey(INVEST_PRODUCT_PREFIX))
                ,product.getTotalInvestingAmount().toString()
                ,amount.toString());

        return result;
    }

    @Override
    public Double getTotalInvestedAmount(InvestProduct product) {

        String result = (String) redisTemplate.opsForValue().get(product.getRedisKey(INVEST_PRODUCT_PREFIX));
        if(result ==null) return 0.0;
        return Double.valueOf(result);
    }

    @Override
    public void save(InvestProduct product,Double amount){
        redisTemplate.opsForValue().set(INVEST_PRODUCT_PREFIX+product.getProductId(),amount);
    }

}
