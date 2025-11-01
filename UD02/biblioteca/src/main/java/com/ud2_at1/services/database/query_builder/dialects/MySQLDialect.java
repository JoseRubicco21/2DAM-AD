package com.ud2_at1.services.database.query_builder.dialects;

public class MySQLDialect implements SQLDialect {
    
    @Override
    public String getAutoIncrement() {
        return "AUTO_INCREMENT";
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
                return length != null ? "VARCHAR(" + length + ")" : "VARCHAR(255)";
            case "DECIMAL":
                return precision != null && scale != null ? 
                    "DECIMAL(" + precision + "," + scale + ")" : "DECIMAL(10,2)";
            case "TEXT":
                return "TEXT";
            case "BOOLEAN":
                return "BOOLEAN";
            default:
                return baseType;
        }
    }
    
    @Override
    public String getStringConcatenation(String... parts) {
        return "CONCAT(" + String.join(", ", parts) + ")";
    }
    
    @Override
    public String getDateFormat() {
        return "DATE";
    }
    
    @Override
    public String getBooleanTrue() {
        return "TRUE";
    }
    
    @Override
    public String getBooleanFalse() {
        return "FALSE";
    }
    
    @Override
    public String getQuoteIdentifier(String identifier) {
        return "`" + identifier + "`";
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
    public String getLengthFunction() {
        return "LENGTH";
    }

    @Override
    public String getSubstringFunction() {
        return "SUBSTRING";
    }

    @Override
    public String getCurrentTimestamp() {
        return "NOW()";
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