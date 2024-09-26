package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ezentwix.teamcostco.dto.shipping.ShippingDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShippingRepository{

    private final SqlSessionTemplate sql;

    public List<ShippingDTO> getAll() {
        return sql.selectList("Shipping.getAll");
    }

}
