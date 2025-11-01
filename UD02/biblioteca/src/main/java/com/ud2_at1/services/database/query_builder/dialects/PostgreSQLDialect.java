package com.ud2_at1.services.database.query_builder.dialects;

public class PostgreSQLDialect implements SQLDialect {
    
    @Override
    public String getAutoIncrement() {
        return "SERIAL";
    }
    
    @Override
    public String getLimit(int count) {
        return "LIMIT " + count;
    }
    
    @Override
    public String getIfNotExists() {
        return "IF NOT EXISTS";
    }
    
    @Override
    public String getDataType(String baseType, Integer length, Integer precision, Integer scale) {
        switch (baseType.toUpperCase()) {
            case "VARCHAR":
                return length != null ? "VARCHAR(" + length + ")" : "VARCHAR";
            case "TEXT":
                return "TEXT";
            case "BOOLEAN":
                return "BOOLEAN";
            case "AUTO_INCREMENT":
                return "SERIAL";
            default:
                return baseType;
        }
    }
    
    @Override
    public String getStringConcatenation(String... parts) {
        return String.join(" || ", parts);
    }
    
    @Override
    public String getDateFormat() {
        return "DATE";
    }
    
    @Override
    public String getBooleanTrue() {
        return "true";
    }
    
    @Override
    public String getBooleanFalse() {
        return "false";
    }
    
    @Override
    public String getQuoteIdentifier(String identifier) {
        return "\"" + identifier + "\"";
    }

    @Override
    public String getLengthFunction() {
        return "CHAR_LENGTH"; // Different from MySQL
    }

    @Override
    public String getCurrentTimestamp() {
        return "CURRENT_TIMESTAMP"; // Different from MySQL
    }

    @Override
    public String getCountFunction() {
        return "COUNT";
    }

    @Override
    public String getSumFunction() {
        return "SUM";
    }

    @Override
    public String getAvgFunction() {
        return "AVG";
    }

    @Override
    public String getMinFunction() {
        return "MIN";
    }

    @Override
    public String getMaxFunction() {
        return "MAX";
    }

    @Override
    public String getUpperFunction() {
        return "UPPER";
    }

    @Override
    public String getLowerFunction() {
        return "LOWER";
    }

    @Override
    public String getSubstringFunction() {
        return "SUBSTRING";
    }

    @Override
    public String getNullFunction() {
        return "NULL";
    }

    @Override
    public String getCoalesceFunction() {
        return "COALESCE";
    }
}