package com.ezentwix.teamcostco.pagination;

import lombok.Data;

@Data
class PageDetails {
    private int startPage;
    private int endPage;
    private int currentPage;
    private int totalPages;

    public PageDetails(int startPage, int endPage, int currentPage, int totalPages) {
        this.startPage = startPage;
        this.endPage = endPage;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}