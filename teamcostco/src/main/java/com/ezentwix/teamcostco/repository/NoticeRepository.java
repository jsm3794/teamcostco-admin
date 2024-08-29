package com.ezentwix.teamcostco.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.notice.NoticeDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class NoticeRepository {

    private final SqlSessionTemplate sql;

    public List<NoticeDTO> getAll() {
        return sql.selectList("Notice.getAll");
    }
}
