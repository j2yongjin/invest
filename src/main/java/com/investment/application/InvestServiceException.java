package com.investment.application;

import com.investment.support.error.ErrorCode;

public class InvestServiceException extends RuntimeException{

    private ErrorCode errorCode;

    public InvestServiceException(ErrorCode errorCode , String message , Throwable throwable){
        super(message,throwable);
        this.errorCode = errorCode;
    }

    public InvestServiceException(ErrorCode errorCode , String message){
        super(message);
        this.errorCode = errorCode;
    }

    public InvestServiceException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
