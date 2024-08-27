package com.ezentwix.teamcostco.pagination;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface PagingRepositoryInterface<T> {
    // 페이지 시작번호, 마지막번호
    List<T> findTableItems(
            @Param("start") int start,
            @Param("end") int end);

    // 어느 테이블 아이템을 셀 것인지 결정
    Integer countTableItems();
}
