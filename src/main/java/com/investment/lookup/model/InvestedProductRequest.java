package com.investment.lookup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InvestedProductRequest {

    private Long userId;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    public static InvestedProductRequest of(Long userId , LocalDateTime startedAt , LocalDateTime finishedAt){
        return InvestedProductRequest.builder()
                .userId(userId)
                .startedAt(startedAt)
                .finishedAt(finishedAt)
                .build();
    }

}
