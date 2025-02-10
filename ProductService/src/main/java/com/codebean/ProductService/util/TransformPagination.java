package com.codebean.ProductService.util;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Feb 2025 12:35
@Last Modified 10 Feb 2025 12:35
Version 1.0
*/

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransformPagination {

    private String sortByColumn;
    private String sort;
    private String[] sortArr = new String[2];

    public Map<String, Object> transformPagination(List ls, Page page, String column, String value) {
        sortArr = page.getSort().toString().split(":");
        sortByColumn = sortArr[0];
        Boolean isSorted = sortByColumn.equals("UNSORTED");
        sortByColumn = isSorted ? "id" : sortByColumn;
        sort = isSorted ? "asc" : sortArr[1];
        Map<String, Object> map = new HashMap<>();
        map.put("content", ls);
        map.put("total-data", page.getTotalElements());
        map.put("current-page", page.getNumber());
        map.put("total-page", page.getTotalPages());
        map.put("sort", sort.trim().toLowerCase());
        map.put("size-per-page", page.getSize());
        map.put("column-name", column);
        map.put("sort-by", sortByColumn);
        map.put("value", value);
        return map;
    }
}
