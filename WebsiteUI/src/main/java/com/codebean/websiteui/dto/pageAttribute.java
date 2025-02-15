package com.codebean.websiteui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class pageAttribute<T> {

    @JsonProperty("sort-by")
    private String sortBy;

    @JsonProperty("sort")
    private String sort;

    @JsonProperty("current-page")
    private Integer currentPage;

    @JsonProperty("total-page")
    private Integer totalPage;

    @JsonProperty("size-per-page")
    private Integer sizePerPage;

    @JsonProperty("column-name")
    private String columnName;

    @JsonProperty("value")
    private String value;

    @JsonProperty("total-data")
    private Integer totalData;

    @JsonProperty("content")
    private List<T> content;
}
