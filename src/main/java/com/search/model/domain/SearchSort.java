package com.search.model.domain;

import lombok.Value;

@Value
public class SearchSort {
    private final String property;
    private final String direction;
}
