package com.ammaPaasam.unavagam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {

    private List<T> content; // to get actual content

    private int page; // get current page number;

    private int size;//  count of items available  on current page

    private long totalElements; //total items in db;

    private int totalPages; //total paes in db;


}
