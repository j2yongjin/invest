package com.investment.support.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {

    INVALID_PARAMETER("INVALID_PARAMETER","요청 값 또는 요청인자 형식 오류입니다."),
    NOT_FOUND_PRODUCT("NOT_FOUND_PRODUCT","투장 상품이 존재하지 않습니다."),
    EXPIRED_DATE("EXPIRED_DATE","투자 신청 기간이 종료 되었습니다."),
    EXCEED_INVESTING_AMOUNT("EXCEED_INVESTING_AMOUNT","투장 상품 판매 완료되었습니다.(sold-out"),

    SYSTEM_ERROR("SYSTEM_ERROR","시스템 오류")
    ;

    @Getter
    private String code;

    @Getter
    private String msg;


}
