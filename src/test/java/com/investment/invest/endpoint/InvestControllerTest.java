package com.investment.invest.endpoint;

import com.investment.InvestmentApplication;
import com.investment.invest.model.InvestRequest;
import com.investment.support.error.Error;
import com.investment.support.error.ErrorCode;
import com.investment.support.error.ErrorResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpStatusCodeException;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = InvestmentApplication.class)
@RunWith(SpringRunner.class)
public class InvestControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    private HttpHeaders httpHeaders;

    @Before
    public void setup() {
        this.httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept","application/json, application/json, application/*+json, application/*+json");
        httpHeaders.add("Content-Type","application/json;charset=UTF-8");
        httpHeaders.add("x-user-id" , "1004");
    }

    @Test
    public void whenInvest_thenSuccess(){

        String url = "/investment/v1/invest";

        InvestRequest request = InvestRequest.builder()
                .product(new InvestRequest.Product(1L))
                .monetaryAmount(new InvestRequest.MonetaryAmount(100))
                .build();

        HttpEntity<InvestRequest> requestEntity = new HttpEntity<>(request,this.httpHeaders);

        // when
        ResponseEntity responseEntity = testRestTemplate.exchange(url, HttpMethod.POST,requestEntity,Void.class);

        // then
        assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.NO_CONTENT));
    }

    @Test
    public void whenInvest_thenInvalidParam(){

            String url = "/investment/v1/invest";

            InvestRequest request = InvestRequest.builder()
                    .monetaryAmount(new InvestRequest.MonetaryAmount(100))
                    .build();

            HttpEntity<InvestRequest> requestEntity = new HttpEntity<>(request, this.httpHeaders);

            // when
            ResponseEntity responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, ErrorResponse.class);

            // then
            assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.BAD_REQUEST));
            ErrorResponse response = (ErrorResponse) responseEntity.getBody();

            assertThat(response.getError().getCode() , Matchers.equalTo(ErrorCode.INVALID_PARAMETER.toString()));
    }

    @Test
    public void whenInvest_thenExceedInvestingAmount(){

        String url = "/investment/v1/invest";

        InvestRequest request = InvestRequest.builder()
                .product(new InvestRequest.Product(1L))
                .monetaryAmount(new InvestRequest.MonetaryAmount(9999999))
                .build();

        HttpEntity<InvestRequest> requestEntity = new HttpEntity<>(request, this.httpHeaders);

        // when
        ResponseEntity responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, ErrorResponse.class);

        // then
        assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.BAD_REQUEST));
        ErrorResponse response = (ErrorResponse) responseEntity.getBody();

        assertThat(response.getError().getCode() , Matchers.equalTo(ErrorCode.EXCEED_INVESTING_AMOUNT.toString()));
    }





}