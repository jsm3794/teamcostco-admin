package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.notice.BoardFixDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardFixRepository {

    private final SqlSessionTemplate sql;

    public BoardFixDTO get(Integer notice_id) {
        return sql.selectOne("Fix.get", notice_id);
    }

    public void fix(BoardFixDTO boardFixDTO) {
        sql.update("Fix.fix", boardFixDTO);
    }

    public void delete(Integer notice_id) {
        sql.delete("Fix.delete", notice_id);
    }
}
