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

    public List<EmployeeDTO> getEmpList(){
        return sql.selectList("Employees.getEmpList");
    }
}
