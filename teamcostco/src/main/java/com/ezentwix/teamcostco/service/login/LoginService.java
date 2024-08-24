package com.ezentwix.teamcostco.service.login;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.EmployeeDTO;
import com.ezentwix.teamcostco.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService  {
    private final EmployeeRepository employeeRepository;

    public EmployeeDTO login(String id, String pw) {
        return employeeRepository.getByIdAndPw(id, pw);
    }

}
