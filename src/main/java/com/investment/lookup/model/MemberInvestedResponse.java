package com.investment.lookup.model;

import com.investment.lookup.domain.MemberInvested;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MemberInvestedResponse {

    private List<MemberInvested> memberInvestedDetails;


}
