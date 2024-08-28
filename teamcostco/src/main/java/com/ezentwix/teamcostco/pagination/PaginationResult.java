package com.ezentwix.teamcostco.pagination;

import java.util.List;

import lombok.Data;

@Data
public class PaginationResult<T> {
    private List<T> data;
    private int count;

    public PaginationResult(List<T> data, int count) {
        this.count = count;
        this.data = data;
    }
}