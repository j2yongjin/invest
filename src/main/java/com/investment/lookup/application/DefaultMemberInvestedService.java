package com.investment.lookup.application;

import com.investment.lookup.domain.MemberInvested;
import com.investment.lookup.infra.MemberInvestedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMemberInvestedService implements MemberInvestedService{

    private final MemberInvestedRepository memberInvestedRepository;


    public List<MemberInvested> getMemberInvestedDetails(Long userId){
        return memberInvestedRepository.findAllByUserid(userId);
    }

}
