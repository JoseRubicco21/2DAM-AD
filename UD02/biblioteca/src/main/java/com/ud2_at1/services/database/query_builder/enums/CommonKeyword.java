package com.ud2_at1.services.database.query_builder.enums;

public enum CommonKeyword {
    IF("IF"),
    EXISTS("EXISTS"),
    NOT("NOT"),
    ON("ON"),
    ALL("ALL"),
    TO("TO"),
    BY("BY"),
    ASC("ASC"),
    DESC("DESC"),
    CASCADE("CASCADE"),
    EQUALS("="),
    COMMA(","),
    SEMICOLON(";"),
    ASTERISK("*"),
    PERCENT("%"),
    AT_SIGN("@"),
    DATABASE("DATABASE"),
    TABLE("TABLE"),
    USER("USER"),
    PRIVILEGES("PRIVILEGES"),
    SHOW("SHOW");

    private final String keyword;

    CommonKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}