package com.investment.invest.application;

import com.investment.application.InternalException;
import com.investment.application.InvestServiceException;
import com.investment.invest.model.InvestRequest;
import com.investment.support.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InvestServiceHandler {

    private final InvestService investService;

    public void invest(InvestRequest investRequest,Long userId){
        try{
            validate(investRequest,userId);
            investService.invest(investRequest,userId);
        }catch (InvestServiceException se){
            throw se;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR,e.getMessage(),e);
        }
    }

    private void validate(InvestRequest investRequest,Long userId){
        if(Objects.isNull(userId) && userId <= 0L) throw new InvestServiceException(ErrorCode.INVALID_PARAMETER,"userId 값이 유효하지 않습니다.");
        investRequest.validate();
    }

}
