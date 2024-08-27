package com.ezentwix.teamcostco.test;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.pagination.PagingRepositoryInterface;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestRepository implements PagingRepositoryInterface<ProductDTO>{

    private final SqlSessionTemplate sql;

    @Override
    public List<ProductDTO> findTableItems(int start, int end) {
        Map<String, Integer> parmas = new HashMap<>();
        parmas.put("start", start);
        parmas.put("end", end);

        return sql.selectList("Page.findTableItems", parmas);
    }

    @Override
    public Integer countTableItems() {
       return sql.selectOne("Page.countTableItems");
    }

}
