package com.investment.lookup.infra;

import com.investment.lookup.domain.MemberInvested;

import java.util.List;

public interface MemberInvestedRepository {
    List<MemberInvested> findAllByUserid(Long userid);
}
