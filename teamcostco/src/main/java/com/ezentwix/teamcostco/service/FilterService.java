package com.ezentwix.teamcostco.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.filter.FilterDTO;

@Service
public class FilterService {

    private enum FilterType {
        EMPLOYEE,
        ORDERREQUEST,
        NOTICE
    }

    public List<FilterDTO> getFilter(String filterType) {
        FilterType type = FilterType.valueOf(filterType.toUpperCase());

        switch (type) {
            case EMPLOYEE:
                return createEmployeeFilters();
            case ORDERREQUEST:
                return createOrderRequestFilters();
            case NOTICE:
                return createNoticeFilters();
            default:
                return List.of();
        }
    }

    private List<FilterDTO> createEmployeeFilters() {
        return List.of(
            new FilterDTO("이메일", "input", "text", "email", "example@ex.com", null),
            new FilterDTO("연락처", "input", "text", "phone_number", "example@ex.com", null),
            new FilterDTO("직책", "input", "select", "job_title", "", Map.of(
                "1", "관리자",
                "2", "개발자")),
            new FilterDTO("고용일", "input", "date", "hire_date", "", null),
            new FilterDTO("가입일", "input", "date", "join_date", "", null)
        );
    }

    private List<FilterDTO> createOrderRequestFilters() {
        return List.of(
            new FilterDTO("주문번호", "input", "text", "order_number", "주문 번호 입력", null),
            new FilterDTO("주문날짜", "input", "date", "order_date", "", null),
            new FilterDTO("상태", "select", "", "status", "", Map.of(
                "", "전체",
                "pending", "대기 중",
                "shipped", "발송됨",
                "delivered", "배송 완료")),
            new FilterDTO("고객 이름", "input", "text", "customer_name", "고객 이름 입력", null)
        );
    }

    private List<FilterDTO> createNoticeFilters() {
        return List.of(
            new FilterDTO("주문번호", "input", "text", "order_number", "주문 번호 입력", null),
            new FilterDTO("주문날짜", "input", "date", "order_date", "", null),
            new FilterDTO("상태", "select", "", "status", "", Map.of(
                "", "전체",
                "pending", "대기 중",
                "shipped", "발송됨",
                "delivered", "배송 완료")),
            new FilterDTO("고객 이름", "input", "text", "customer_name", "고객 이름 입력", null)
        );
    }
}
