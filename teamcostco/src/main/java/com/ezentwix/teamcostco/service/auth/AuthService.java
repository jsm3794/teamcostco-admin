package com.ezentwix.teamcostco.service.auth;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.EmployeeDTO;
import com.ezentwix.teamcostco.repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public EmployeeDTO login(String id, String pw) {
        return authRepository.login(id, pw);
    }

}
