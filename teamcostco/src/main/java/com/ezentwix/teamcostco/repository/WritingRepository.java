package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.notice.WritingDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class WritingRepository {

    private final SqlSessionTemplate sql;
    
    public WritingDTO add(WritingDTO writingDTO) {
        sql.insert("Writing.add", writingDTO);
        return writingDTO;
    }
}
