package com.ad_ud2_at2.services.database.query_builder.enums;

public enum DataType {
    INT("INT"),
    VARCHAR("VARCHAR"),
    TEXT("TEXT"),
    DATE("DATE"),
    DATETIME("DATETIME"),
    BOOLEAN("BOOLEAN"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DECIMAL("DECIMAL"),
    CHAR("CHAR"),
    BLOB("BLOB");

    private final String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String withLength(int length) {
        return type + "(" + length + ")";
    }

    public String withPrecision(int precision, int scale) {
        return type + "(" + precision + "," + scale + ")";
    }
}