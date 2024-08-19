package com.ezentwix.teamcostco.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.TestDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TestRepository {

    private final SqlSessionTemplate sql;

    public List<TestDTO> getAll() {
        System.out.println("TEST");
        return sql.selectList("Test.getAll");
    }
}
