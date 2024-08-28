package com.ezentwix.teamcostco.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService implements PageMetadataProvider{
    private final EmployeeRepository employeeRepository;

    private static final int PAGE_SIZE = 10;

    public List<EmployeeDTO> getEmpList(int page) {
        int offset = page * PAGE_SIZE;
        Map<String, Object> params = new HashMap<>();
        params.put("limit", PAGE_SIZE);
        params.put("offset", offset);

        return employeeRepository.getEmpList(params);
    }

    public Set<String> getAllJobTitles() {
        List<String> jobTitlesList = employeeRepository.getAllJobTitles();
        return new HashSet<>(jobTitlesList);
    }

    public int getTotalPages() {
        int totalEmployees = employeeRepository.countEmp();
        return (int) Math.ceil((double) totalEmployees / PAGE_SIZE);
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
        return List.of(
            "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js", // Bootstrap JS 추가
            "/js/contents/employee.js"  // 기존의 JS 파일
        );
    }

    
}