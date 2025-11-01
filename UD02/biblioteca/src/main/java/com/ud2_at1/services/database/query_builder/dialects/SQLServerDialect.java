package com.ud2_at1.services.database.query_builder.dialects;

public class SQLServerDialect implements SQLDialect {
    
    @Override
    public String getAutoIncrement() {
        return "IDENTITY(1,1)";
    }
    
    @Override
    public String getLimit(int count) {
        return "TOP " + count;
    }
    
    @Override
    public String getIfNotExists() {
        return ""; // SQL Server uses different syntax
    }
    
    @Override
    public String getDataType(String baseType, Integer length, Integer precision, Integer scale) {
        switch (baseType.toUpperCase()) {
            case "VARCHAR":
                return length != null ? "NVARCHAR(" + length + ")" : "NVARCHAR(255)";
            case "TEXT":
                return "NTEXT";
            case "BOOLEAN":
                return "BIT";
            default:
                return baseType;
        }
    }
    
    @Override
    public String getStringConcatenation(String... parts) {
        return String.join(" + ", parts);
    }
    
    @Override
    public String getDateFormat() {
        return "DATE";
    }
    
    @Override
    public String getBooleanTrue() {
        return "1";
    }
    
    @Override
    public String getBooleanFalse() {
        return "0";
    }
    
    @Override
    public String getQuoteIdentifier(String identifier) {
        return "[" + identifier + "]";
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
        return "LEN"; // SQL Server uses LEN instead of LENGTH
    }

    @Override
    public String getSubstringFunction() {
        return "SUBSTRING";
    }

    @Override
    public String getCurrentTimestamp() {
        return "GETDATE()"; // SQL Server specific function
    }

    @Override
    public String getNullFunction() {
        return "NULL";
    }

    @Override
    public String getCoalesceFunction() {
        return "ISNULL"; // SQL Server prefers ISNULL over COALESCE for 2 parameters
    }
}