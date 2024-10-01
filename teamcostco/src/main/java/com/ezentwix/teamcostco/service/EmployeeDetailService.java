package com.ezentwix.teamcostco.service;

import java.util.List;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeDetailService implements PageMetadataProvider {

    private final EmployeeRepository employeeRepository;

    public EmployeeDTO getEmp(Integer emp_id) {
        return employeeRepository.getEmp(emp_id);
    }

    public EmployeeDTO getOne(Integer emp_id) {
        return employeeRepository.getOne(emp_id);
    }

    public void fix(EmployeeDTO empDTO) {
        employeeRepository.fix(empDTO);
    }

    public int calculateAge(LocalDate birthday) {
        if (birthday != null) {
            int age = Period.between(birthday, LocalDate.now()).getYears();
            return age;
        } else {
            return 0;
        }
    }
    

    @Override
    public String getUri() {
        return "employee/emp_detail";
    }

    @Override
    public String getPageTitle() {
        return "직원관리";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/employeeDetail.css");
    }

}
