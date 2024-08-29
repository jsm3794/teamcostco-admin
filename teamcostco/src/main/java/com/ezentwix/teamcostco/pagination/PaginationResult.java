package com.ezentwix.teamcostco.pagination;

import java.util.List;

import lombok.Data;

@Data
public class PaginationResult<T> {
    private List<T> data;
    private int count;
    private PageDetails pageDetails;

    public PaginationResult(List<T> data, int count, int startPageNumber, int endPageNumber, int currentPage, int totalPages) {
        this.count = count;
        this.data = data;
        this.pageDetails = new PageDetails(startPageNumber, endPageNumber, currentPage, totalPages);
    }
}