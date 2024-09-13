package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeFixService implements PageMetadataProvider {

    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;
    
    public void fix(EmployeeDTO empDTO) {
        // 기존 데이터 조회
        EmployeeDTO existingEmployee = employeeRepository.getOne(empDTO.getEmp_id());

        // 기존 값과 비교하여 DTO에 설정
        empDTO.setOldEmpName(existingEmployee.getEmp_name());
        empDTO.setOldEmpEmail(existingEmployee.getEmp_email());
        empDTO.setOldPhoneNumber(existingEmployee.getPhone_number());
        empDTO.setOldLoginId(existingEmployee.getLogin_id());
        empDTO.setOldLoginPw(existingEmployee.getLogin_pw());
        empDTO.setOldJobTitle(existingEmployee.getJob_title());
        empDTO.setOldBirthday(existingEmployee.getBirthday());
        empDTO.setOldGender(existingEmployee.getGender());
        empDTO.setOldAddress(existingEmployee.getAddress());
        empDTO.setOldDetailAddress(existingEmployee.getDetail_address());

        // 데이터베이스에 업데이트
        employeeRepository.fix(empDTO);
    }

    public EmployeeDTO getOne(Integer emp_id) {
        return employeeRepository.getOne(emp_id);
    }

    public void email(String login_id) {
        employeeRepository.email(login_id);
    }

    public void verifyEmail(String token) {
        employeeRepository.verifyEmail(token);
    }

    public void updateEmailVerificationToken(EmployeeDTO empDTO) {
        employeeRepository.updateEmailVerificationToken(empDTO);
    }

    @Override
    public String getUri() {
        return "employee/emp_fix";
    }

    @Override
    public String getPageTitle() {
        return "정보수정";
    }
    
    public void sendVerificationEmailIfNeeded(EmployeeDTO empDTO) {
        EmployeeDTO existingEmployee = employeeRepository.getOne(empDTO.getEmp_id());
        
        // 이메일이 변경되었는지 확인
        if (empDTO.getEmp_email() != null && !empDTO.getEmp_email().equals(existingEmployee.getEmp_email())) {
            String token = UUID.randomUUID().toString();
            empDTO.setSetEmailVerificationToken(token);
            emailService.sendnewToken(empDTO.getEmp_email(), token);
            updateEmailVerificationToken(empDTO);
        }
    }
}

