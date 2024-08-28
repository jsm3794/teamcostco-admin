package com.ezentwix.teamcostco.repository;

import java.util.Map;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final SqlSessionTemplate sql;

    public EmployeeDTO getByIdAndPw(String id, String pw) {
        Map<String, String> params = Map.of("id", id, "pw", pw);
        return sql.selectOne("Employees.getEmpByIdAndPw", params);
    }

    public List<EmployeeDTO> getEmpList(Map<String, Object> params) {
        return sql.selectList("Employees.getEmpList", params);
    }

    public List<String> getAllJobTitles() {
        return sql.selectList("Employees.getAllJobTitles");
    }

    public int countEmp() {
        return sql.selectOne("Employees.countEmp");
    }
}
