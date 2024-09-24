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

    // 발주 상태 업데이트 메서드 추가
    public void updateStatus(Integer requestId, String status) {
        sql.update("OrderRequest.updateStatus", Map.of("requestId", requestId, "status", status));
    }

    public void updateReceivedQty(Integer requestId, int receivedQty) {
        // 수령 수량 업데이트 로직 추가 가능
        throw new UnsupportedOperationException("Unimplemented method 'updateReceivedQty'");
    }

    public void updateDefectiveQty(Integer requestId, int defectiveQty) {
        // 불량 수량 업데이트 로직 추가 가능
        throw new UnsupportedOperationException("Unimplemented method 'updateDefectiveQty'");
    }

    public OrderTrackDTO getStatusQty() {
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        return orderTrackDTO;
    }
}
