package com.ezentwix.teamcostco.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.inventory.DetailCategoryDTO;
import com.ezentwix.teamcostco.dto.inventory.LargeCategoryDTO;
import com.ezentwix.teamcostco.dto.inventory.MediumCategoryDTO;
import com.ezentwix.teamcostco.dto.inventory.SmallCategoryDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CategoriesRepository {

    private final SqlSessionTemplate sql;
    
    public List<LargeCategoryDTO> getlarge() {
        List<LargeCategoryDTO> list = sql.selectList("Category.getLarge");
        return list;
    }

    public List<MediumCategoryDTO> getMedium(Integer large_id) {
        return sql.selectList("Category.getMedium", large_id);
    }

    public List<SmallCategoryDTO> getSmall(Integer medium_id) {
        return sql.selectList("Category.getSmall", medium_id);
    }
    
    public List<DetailCategoryDTO> getDetail(Integer small_id) {
        return sql.selectList("Category.getDetail", small_id);
    }

}
