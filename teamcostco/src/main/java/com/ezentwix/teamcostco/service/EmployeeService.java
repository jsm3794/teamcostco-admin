package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService implements PageMetadataProvider{
    private final EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getEmpList(){
        return employeeRepository.getEmpList();
    }

    @Override
    public String getUri() {
        return "employee/employee";
    }

    @Override
    public String getPageTitle() {
        return "직원관리";
    }

    
    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/employee.css");
    }
    
    @Override
    public List<String> getJsFiles() {
        return null;
    }

}