package com.learn.springsecuritymyimpl.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public class Query<T> extends Page<T> {
    private static final String PAGE = "current_page";
    private static final String LIMIT = "page_size";

    public Query(Map<String, Object> params) {
        super(Integer.parseInt(params.getOrDefault(PAGE, 1).toString())
                , Integer.parseInt(params.getOrDefault(LIMIT, 10).toString()));
        params.remove(PAGE);
        params.remove(LIMIT);
    }
}
