package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponseMapper {
    public static <T, R> PageResponse<R> toPageResponse(Page<T> page, List<R> content) {
        return PageResponse.<R>builder()
                .content(content)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .page(page.getNumber())
                .hasPrevious(page.hasPrevious())
                .hasNext(page.hasNext())
                .build();
    }
}
