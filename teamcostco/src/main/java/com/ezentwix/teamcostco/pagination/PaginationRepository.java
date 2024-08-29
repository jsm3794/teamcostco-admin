package com.ezentwix.teamcostco.pagination;

import oracle.sql.TIMESTAMP;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.SqlSessionTemplate;

@Slf4j
@Repository
public class PaginationRepository {
    private final SqlSessionTemplate sessionTemplate;
    private final ModelMapper modelMapper;

    // map의 시간 데이터 타입이 oracle.sql.TIMESTAMP 이기 때문에 따로 ojdbc 라이브러리를 가져와
    // date 형식으로 변환하는것이 필요함
    public PaginationRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sessionTemplate = sqlSessionTemplate;
        this.modelMapper = new ModelMapper();

        modelMapper.addConverter(new Converter<TIMESTAMP, Date>() {
            @Override
            public Date convert(MappingContext<TIMESTAMP, Date> context) {
                TIMESTAMP source = context.getSource();
                if (source != null) {
                    // TIMESTAMP를 long 값으로 변환한 후, Date로 변환
                    Date date = null;
                    try {
                        date = source.dateValue();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return date;
                }
                return null;
            }
        });
    }

    /**
     * 페이지네이션을 적용한 데이터를 가져옵니다.
     *
     * @param queryId          MyBatis 쿼리의 ID (count와 selectPage 쿼리에서 사용됨)
     * @param pageable         페이지네이션 정보 (page, size, sort 등)
     * @param additionalParams 추가 쿼리 파라미터 (ex: 필터 값)
     * @return 페이지데이터를 반환
     */
    public <T> PaginationResult<T> getPage(String queryId, Pageable pageable, Map<String, Object> additionalParams,
            Class<T> dtoClass) {

        int page = pageable.getPageNumber() - 1;
        int offset = Math.max(0, pageable.getPageSize() * page);
        int limit = pageable.getPageSize();
        log.info("offset : {}", offset);

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        params.put("queryId", queryId);

        if (additionalParams != null) {
            params.putAll(additionalParams);
        }

        int count = sessionTemplate.selectOne("pagination.count", params);

        int totalPages = (int) Math.ceil((double) count / limit);
        int currentPage = pageable.getPageNumber();
        int showPageNum = 5;
        int startPageNumber = (page / showPageNum) * showPageNum + 1;

        // 현재 페이지의 마지막 페이지 번호
        int endPageNumber = Math.max(Math.min(startPageNumber + showPageNum - 1, totalPages), 1);
        log.info("전체 페이지 수 : {}", totalPages);
        log.info("***** 현재 페이지의 시작 페이지 번호({}), 마지막({}) *****", startPageNumber, endPageNumber);
        List<Map<String, Object>> rawData;
        if (page < totalPages && page >= 0) {
            rawData = sessionTemplate.selectList("pagination.selectPage", params);
        } else {
            rawData = List.of();
        }

        // 쿼리 결과를 실제 사용할 DTO 객체로 변환
        // MAP 결과가 DTO 구조와 일치하지 않아 데이터 바인딩 등에 문제가 발생

        List<T> data = rawData.stream()

                .map(map -> {
                    T dto = modelMapper.map(map, dtoClass);
                    return dto;
                })
                .collect(Collectors.toList());

        PaginationResult<T> result = new PaginationResult<>(data, count, startPageNumber, endPageNumber, currentPage,
                totalPages);

        return result;
    }

}
