package com.investment.lookup.endpoint;

import com.investment.InvestmentApplication;
import com.investment.invest.model.InvestRequest;
import com.investment.lookup.model.InvestedProductsResponse;
import com.investment.lookup.model.MemberInvestedResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = InvestmentApplication.class)
@RunWith(SpringRunner.class)
public class InvestLookupControllerTest {

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
    public void whenSalesLookup_thenSuccess(){

        String url = "/investment/v1/lookup/sales";

        //when
        ResponseEntity responseEntity = testRestTemplate.getForEntity(url, InvestedProductsResponse.class);

        //then
        assertThat(responseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        assertThat(responseEntity.getBody(),Matchers.notNullValue());
    }

    @Test
    public void whenMemberLookup_thenSuccess(){
        String url = "/investment/v1/lookup/member/sales";

        HttpEntity<?> requestEntity = new HttpEntity<>(null,this.httpHeaders);


        ResponseEntity responseEntity = testRestTemplate.exchange(url, HttpMethod.GET,requestEntity,MemberInvestedResponse.class);

        // then
        assertThat(responseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        assertThat(responseEntity.getBody() , Matchers.notNullValue());
    }

}