package com.search.model.domain;

import lombok.Value;

@Value
public class SortCriteria {
    private final String property;
    private final String direction;
}
