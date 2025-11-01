package com.ad_ud2_at2.services.database.query_builder.enums;

public enum AggregateFunction {
    COUNT("COUNT"),
    SUM("SUM"),
    AVG("AVG"),
    MIN("MIN"),
    MAX("MAX");

    private final String function;

    AggregateFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }
}