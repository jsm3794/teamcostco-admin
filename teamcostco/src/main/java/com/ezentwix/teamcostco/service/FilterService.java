package com.ezentwix.teamcostco.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.filter.FilterDTO;

@Service
public class FilterService {

    public List<FilterDTO> getFilter(String filtertype) {

        if (filtertype.equals("employee")) {

            return List.of(
                    new FilterDTO("이메일", "input", "text", "email", "example@ex.com", null),
                    new FilterDTO("연락처", "input", "text", "phone_number", "example@ex.com", null),
                    new FilterDTO("직책", "input", "select", "job_title", "",
                            Map.of(
                                    "1", "관리자",
                                    "2", "개발자")),
                    new FilterDTO("고용일", "input", "date", "date", "", null),
                    new FilterDTO("가입일", "input", "date", "date", "", null));
        } else if (filtertype.equals("order")) {
            return List.of(
                    new FilterDTO("주문 번호", "input", "text", "order_number", "주문 번호 입력", null),
                    new FilterDTO("주문 날짜", "input", "date", "order_date", "", null),
                    new FilterDTO("상태", "input", "select", "status", "",
                            Map.of(
                                    "pending", "대기 중",
                                    "shipped", "발송됨",
                                    "delivered", "배송 완료")),
                    new FilterDTO("고객 이름", "input", "text", "customer_name", "고객 이름 입력", null)
            );
        }

        return List.of();
    }

}
