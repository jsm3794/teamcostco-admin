package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.config.BCryptUtils;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDTO login(String id, String pw) {

        // 데이터베이스에서 해시된 비밀번호를 가져옵니다
        String hashedPwFromDB = employeeRepository.getPwById(id);
    
        // 해시된 비밀번호가 존재하지 않으면 로그인 실패
        if (hashedPwFromDB == null) {
            return null;
        }
    
        // 입력된 비밀번호와 데이터베이스의 해시된 비밀번호를 비교합니다
        boolean success = BCryptUtils.checkPassword(pw, hashedPwFromDB);
    
        if (success) {
            // 비밀번호가 맞으면 사용자 정보를 반환합니다
            return employeeRepository.getById(id);
        }
    
        // 비밀번호가 틀리면 null을 반환합니다
        return null;
    }

    public String getToken(String id) {
        return employeeRepository.getToken(id);
    }
    

    public void setSession(HttpSession session, EmployeeDTO emp) {
        session.setAttribute("emp", emp);
    }

    public void clearSession(HttpSession session) {
        session.removeAttribute("emp");
    }

}
