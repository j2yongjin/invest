package com.investment.lookup.application;

import com.investment.application.InternalException;
import com.investment.application.InvestServiceException;
import com.investment.lookup.model.InvestedProductsResponse;
import com.investment.lookup.model.MemberInvestedResponse;
import com.investment.support.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvestProductLookupHandler {

    private final InvestProductService investProductService;

    private final MemberInvestedService memberInvestedService;

    public InvestedProductsResponse getInvestProducts(){
        try {
            return new InvestedProductsResponse(investProductService.getInvestProduct());
        }catch (InvestServiceException se){
            throw  se;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR);
        }
    }

    public MemberInvestedResponse getMemberInvestDetails(Long userId){
        try {
            Optional.ofNullable(userId).orElseThrow(() -> new InvestServiceException(ErrorCode.INVALID_PARAMETER , "userId 값을 입력해 주세요"));
            return new MemberInvestedResponse(memberInvestedService.getMemberInvestedDetails(userId));
        }catch (InvestServiceException se){
            throw se;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR);
        }
    }
}
