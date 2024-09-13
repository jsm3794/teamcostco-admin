package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.find.FindDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FindRepository {

    private final SqlSessionTemplate sql;

    public boolean find_idCount(FindDTO findDTO) {
        int count = sql.selectOne("Find.getIdCount", findDTO);
        return count == 1;
    }

    public boolean find_pwCount(FindDTO findDTO) {
        int count = sql.selectOne("Find.getPwCount", findDTO);
        return count == 1;
    }

    public String find_id(FindDTO findDTO) {
        return sql.selectOne("Find.getId", findDTO);
    }

    public String find_pw(FindDTO findDTO) {
        return sql.selectOne("Find.getPw", findDTO);
    }

}
