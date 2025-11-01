package com.ad_ud2_at2.services.database.query_builder.enums;

public enum DMLKeyword {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    FROM("FROM"),
    INTO("INTO"),
    VALUES("VALUES"),
    SET("SET"),
    WHERE("WHERE"),
    AND("AND"),
    OR("OR"),
    LIKE("LIKE"),
    BETWEEN("BETWEEN"),
    IS("IS"),
    ORDER_BY("ORDER BY"),
    GROUP_BY("GROUP BY"),
    HAVING("HAVING"),
    LIMIT("LIMIT"),
    DISTINCT("DISTINCT");

    private final String keyword;

    DMLKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}