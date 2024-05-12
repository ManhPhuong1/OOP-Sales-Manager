package com.sapo.salemanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PagedResponseObject{
    private int responseCode;
    private String message;

    private int page;
    private int perPage;
    private long totalItems;
    private int totalPages;
    private Object data;
}
