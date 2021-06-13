package com.investment.lookup.application;

import com.investment.lookup.domain.MemberInvested;

import java.util.List;

public interface MemberInvestedService {
    List<MemberInvested> getMemberInvestedDetails(Long userId);
}
