package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.notice.BoardDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    
    private final SqlSessionTemplate sql;

    public BoardDTO get(Integer notice_id) {
        return sql.selectOne("Board.get", notice_id);
    }
}
