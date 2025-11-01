package com.ud2_at1.services.database.query_builder.dialects;

public interface SQLDialect {
    String getAutoIncrement();
    String getLimit(int count);
    String getIfNotExists();
    String getDataType(String baseType, Integer length, Integer precision, Integer scale);
    String getStringConcatenation(String... parts);
    String getDateFormat();
    String getBooleanTrue();
    String getBooleanFalse();
    String getQuoteIdentifier(String identifier);
    
    String getCountFunction();
    String getSumFunction();
    String getAvgFunction();
    String getMinFunction();
    String getMaxFunction();
    
    String getUpperFunction();
    String getLowerFunction();
    String getLengthFunction();
    String getSubstringFunction();
    String getCurrentTimestamp();
    String getNullFunction();
    String getCoalesceFunction();
}