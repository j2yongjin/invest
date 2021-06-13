package com.investment.invest.endpoint;

import com.investment.invest.application.InvestServiceHandler;
import com.investment.invest.model.InvestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investment/v1")
@RequiredArgsConstructor
public class InvestController {

    private final InvestServiceHandler serviceHandler;

    @PostMapping("invest")
    public ResponseEntity invest(@RequestHeader("x-user-id") Long userId, @RequestBody InvestRequest request){
        serviceHandler.invest(request,userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
