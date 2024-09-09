package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.join.JoinDTO;
import com.ezentwix.teamcostco.repository.JoinRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JoinService implements PageMetadataProvider{

    private final JoinRepository joinRepository;

    public JoinDTO add(JoinDTO joinDTO) {
        return joinRepository.add(joinDTO);
    }

    public boolean countId(String login_id) {
        return joinRepository.countId(login_id);
    }

    public void updateEmailVerificationToken(String loginId, String token) {
        joinRepository.updateEmailVerificationToken(loginId, token);
    }

    public boolean verifyEmail(String token) {
        return joinRepository.verifyEmail(token);
    }
    
    @Override
    public String getUri() {
        return "join";
    }

    @Override
    public String getPageTitle() {
        return "회원가입";
    }

    
}
