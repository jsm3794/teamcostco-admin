package com.ezentwix.teamcostco.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.pagination.PaginationRepository;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService implements PageMetadataProvider {
    private final EmployeeRepository employeeRepository;
    private final PaginationRepository paginationRepository;

    public PaginationResult<EmployeeDTO> getPage(Integer page, Integer limit) {
        return paginationRepository.getPage(
                "Employees.getEmpList",
                PageRequest.of(page, limit),
                Map.of(),
                EmployeeDTO.class);
    }

    public PaginationResult<EmployeeDTO> getPage(Integer page, Integer limit, Map<String, Object> params) {
        return paginationRepository.getPage(
                "Employees.getEmpListByFilter",
                PageRequest.of(page, limit),
                params,
                EmployeeDTO.class);
    }

    public List<EmployeeDTO> getEmpList() {
        return employeeRepository.getEmpList();
    }

    @Override
    public String getUri() {
        return "employee/emp_list";
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