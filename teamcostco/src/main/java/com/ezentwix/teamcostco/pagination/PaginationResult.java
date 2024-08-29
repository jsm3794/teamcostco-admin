package com.ezentwix.teamcostco.pagination;

import java.util.List;

import lombok.Data;

@Data
public class PaginationResult<T> {
    private List<T> data;
    private int count;

    // 한 페이지에 보여줄 페이지번호수를 의미합니다 (예시 > 3이면 1-3, 4-6 ...)
    // private static int showPageCount;
    private int startPageNumber;
    private int endPageNumber;
    private int totalPages;

    public PaginationResult(List<T> data, int count, int startPageNumber, int endPageNumber, int totalPages) {
        this.count = count;
        this.data = data;
        this.startPageNumber = startPageNumber;
        this.endPageNumber = endPageNumber;
        this.totalPages = totalPages;
        
    }
}