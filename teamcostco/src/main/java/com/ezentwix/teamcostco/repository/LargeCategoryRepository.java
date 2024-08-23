package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.CategoryDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LargeCategoryRepository {

    private final SqlSessionTemplate sql;

    public void saveLargeCategories(CategoryDTO categoryDTO) {
        sql.insert("Category.savelargeCategory", categoryDTO);
    }
}