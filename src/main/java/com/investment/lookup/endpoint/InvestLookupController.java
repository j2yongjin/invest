package com.investment.lookup.endpoint;

import com.investment.lookup.application.InvestProductLookupHandler;
import com.investment.lookup.model.InvestedProductsResponse;
import com.investment.lookup.model.MemberInvestedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investment/v1/lookup")
@RequiredArgsConstructor
public class InvestLookupController {

    private final InvestProductLookupHandler productLookupHandler;


    @GetMapping("sales")
    public ResponseEntity<InvestedProductsResponse> productLookup(){
        InvestedProductsResponse response = productLookupHandler.getInvestProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("member/sales")
    public ResponseEntity<MemberInvestedResponse> memberLookup(@RequestHeader("x-user-id") Long userId){
        return new ResponseEntity<>(productLookupHandler.getMemberInvestDetails(userId), HttpStatus.OK);
    }
}
