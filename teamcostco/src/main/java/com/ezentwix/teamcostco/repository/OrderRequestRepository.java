package com.ezentwix.teamcostco.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.dto.product.RequestAndProductDTO;
import com.ezentwix.teamcostco.dto.sales.OrderTrackDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderRequestRepository {

    private final SqlSessionTemplate sql;

    public List<OrderRequestDTO> getAll() {
        return sql.selectList("OrderRequest.getAllWithProductName");
    }

    public List<RequestAndProductDTO> getRequestAndProductInfo() {
        return sql.selectList("OrderRequest.getRequestAndProductInfo");
    }

    public void insertOrderRequest(OrderRequestDTO orderRequest) {
        int seq = sql.selectOne("OrderRequest.getNextRequestId");
        orderRequest.setRequest_id(seq);
        System.out.println(orderRequest);
        sql.insert("OrderRequest.insertOrderRequest", orderRequest);
    }

    public int getNextRequestId() {
        return sql.selectOne("OrderRequest.getNextRequestId");
    }

    public OrderRequestDTO getById(Integer request_id){
        return sql.selectOne("OrderRequest.getById", request_id);
    }

    public OrderTrackDTO getStatusQty() {
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        return orderTrackDTO;
    }

    public boolean exist(Long product_code) {

        int check = sql.selectOne("OrderRequest.exist", product_code);

        if (check == 0) {
            return false;
        }

        return true;
    }

    public void newProduct(OrderRequestDTO dto) {
        sql.selectOne("OrderRequest.newProduct", dto);
    }

    public void updateQTY(OrderRequestDTO dto) {
        sql.update("OrderRequest.updateQTY", dto);
    }

    public void complete(OrderRequestDTO dto) {
        sql.update("OrderRequest.complete", dto);
    }

    public void defectiveProduct(OrderRequestDTO dto) {
        sql.insert("OrderRequest.defectiveProduct", dto);
    }

}
