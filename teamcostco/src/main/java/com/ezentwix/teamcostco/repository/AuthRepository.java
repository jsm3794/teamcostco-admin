package com.ezentwix.teamcostco.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.EmployeeDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuthRepository {
    private final SqlSessionTemplate sql;

    public EmployeeDTO login(String id, String pw) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("pw", pw);

        // SQL 쿼리 실행
        EmployeeDTO emp = sql.selectOne("Employees.get", params);

        return emp;
    }

}
