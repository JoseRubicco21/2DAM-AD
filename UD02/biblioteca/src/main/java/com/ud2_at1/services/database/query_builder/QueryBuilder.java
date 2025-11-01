package com.ud2_at1.services.database.query_builder;

import com.ud2_at1.services.database.query_builder.dialects.SQLDialect;
import com.ud2_at1.services.database.query_builder.dialects.MySQLDialect;
import com.ud2_at1.services.database.query_builder.enums.*;

public class QueryBuilder {
    
    private String query = "";
    private SQLDialect dialect;
    
    public QueryBuilder() {
        this.dialect = new MySQLDialect(); // Default
    }
    
    public QueryBuilder(SQLDialect dialect) {
        this.dialect = dialect;
    }
    
    public QueryBuilder withDialect(SQLDialect dialect) {
        this.dialect = dialect;
        return this;
    }
    
    private void addSpace(){
        this.query += " ";
    }

    // Generic keyword methods using enums
    public QueryBuilder keyword(DDLKeyword keyword) {
        this.query += keyword.getKeyword();
        this.addSpace();
        return this;
    }

    public QueryBuilder keyword(DMLKeyword keyword) {
        this.query += keyword.getKeyword();
        this.addSpace();
        return this;
    }

    public QueryBuilder keyword(CommonKeyword keyword) {
        this.query += keyword.getKeyword();
        this.addSpace();
        return this;
    }

    // Dialect-aware data type methods
    public QueryBuilder dataType(DataType type) {
        this.query += dialect.getDataType(type.getType(), null, null, null);
        this.addSpace();
        return this;
    }

    public QueryBuilder dataType(DataType type, int length) {
        this.query += dialect.getDataType(type.getType(), length, null, null);
        this.addSpace();
        return this;
    }

    public QueryBuilder dataType(DataType type, int precision, int scale) {
        this.query += dialect.getDataType(type.getType(), null, precision, scale);
        this.addSpace();
        return this;
    }

    // Dialect-aware constraint methods
    public QueryBuilder constraint(ColumnConstraint constraint) {
        if (constraint == ColumnConstraint.AUTO_INCREMENT) {
            this.query += dialect.getAutoIncrement();
        } else {
            this.query += constraint.getConstraint();
        }
        this.addSpace();
        return this;
    }

    // Remove the old function method and replace with dialect-aware methods
    public QueryBuilder COUNT() {
        this.query += dialect.getCountFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder SUM() {
        this.query += dialect.getSumFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder AVG() {
        this.query += dialect.getAvgFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder MIN() {
        this.query += dialect.getMinFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder MAX() {
        this.query += dialect.getMaxFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder UPPER() {
        this.query += dialect.getUpperFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder LOWER() {
        this.query += dialect.getLowerFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder LENGTH() {
        this.query += dialect.getLengthFunction();
        this.addSpace();
        return this;
    }

    public QueryBuilder NOW() {
        this.query += dialect.getCurrentTimestamp();
        this.addSpace();
        return this;
    }

    // Dialect-aware convenience methods
    public QueryBuilder AUTO_INCREMENT() {
        this.query += dialect.getAutoIncrement();
        this.addSpace();
        return this;
    }
    
    public QueryBuilder IF_NOT_EXISTS() {
        String ifNotExists = dialect.getIfNotExists();
        if (!ifNotExists.isEmpty()) {
            this.query += ifNotExists;
            this.addSpace();
        }
        return this;
    }
    
    public QueryBuilder LIMIT(int count) {
        this.query += dialect.getLimit(count);
        this.addSpace();
        return this;
    }
    
    public QueryBuilder TRUE() {
        this.query += dialect.getBooleanTrue();
        this.addSpace();
        return this;
    }
    
    public QueryBuilder FALSE() {
        this.query += dialect.getBooleanFalse();
        this.addSpace();
        return this;
    }

    // Simple convenience methods that use enums (same across all SQL flavors)
    public QueryBuilder CREATE() { return keyword(DDLKeyword.CREATE); }
    public QueryBuilder DROP() { return keyword(DDLKeyword.DROP); }
    public QueryBuilder ALTER() { return keyword(DDLKeyword.ALTER); }
    public QueryBuilder TABLE() { return keyword(DDLKeyword.TABLE); }
    public QueryBuilder INDEX() { return keyword(DDLKeyword.INDEX); }
    public QueryBuilder CONSTRAINT() { return keyword(DDLKeyword.CONSTRAINT); }
    public QueryBuilder ADD() { return keyword(DDLKeyword.ADD); }
    public QueryBuilder PRIMARY() { return keyword(DDLKeyword.PRIMARY); }
    public QueryBuilder FOREIGN() { return keyword(DDLKeyword.FOREIGN); }
    public QueryBuilder KEY() { return keyword(DDLKeyword.KEY); }
    public QueryBuilder UNIQUE() { return keyword(DDLKeyword.UNIQUE); }
    public QueryBuilder REFERENCES() { return keyword(DDLKeyword.REFERENCES); }
    public QueryBuilder DATABASE() { return keyword(DDLKeyword.DATABASE); }
    public QueryBuilder USE() { return keyword(DDLKeyword.USE); }
    public QueryBuilder DATABASES() { return keyword(DDLKeyword.DATABASES); }
    public QueryBuilder IDENTIFIED() { return keyword(DDLKeyword.IDENTIFIED); }
    
    
    public QueryBuilder SELECT() { return keyword(DMLKeyword.SELECT); }
    public QueryBuilder INSERT() { return keyword(DMLKeyword.INSERT); }
    public QueryBuilder UPDATE() { return keyword(DMLKeyword.UPDATE); }
    public QueryBuilder DELETE() { return keyword(DMLKeyword.DELETE); }
    public QueryBuilder FROM() { return keyword(DMLKeyword.FROM); }
    public QueryBuilder WHERE() { return keyword(DMLKeyword.WHERE); }
    public QueryBuilder INTO() { return keyword(DMLKeyword.INTO); }
    public QueryBuilder VALUES() { return keyword(DMLKeyword.VALUES); }
    public QueryBuilder SET() { return keyword(DMLKeyword.SET); }
    
    public QueryBuilder USER() { return keyword(CommonKeyword.USER); }
    public QueryBuilder IF() { return keyword(CommonKeyword.IF); }
    public QueryBuilder EXISTS() { return keyword(CommonKeyword.EXISTS); }
    public QueryBuilder NOT() { return keyword(CommonKeyword.NOT); }
    public QueryBuilder ON() { return keyword(CommonKeyword.ON); }
    public QueryBuilder ALL() { return keyword(CommonKeyword.ALL); }
    public QueryBuilder TO() { return keyword(CommonKeyword.TO); }
    public QueryBuilder BY() { return keyword(CommonKeyword.BY); }
    public QueryBuilder ASC() { return keyword(CommonKeyword.ASC); }
    public QueryBuilder DESC() { return keyword(CommonKeyword.DESC); }
    public QueryBuilder CASCADE() { return keyword(CommonKeyword.CASCADE); }
    public QueryBuilder EQUALS() { return keyword(CommonKeyword.EQUALS); }
    public QueryBuilder COMMA() { return keyword(CommonKeyword.COMMA); }
    public QueryBuilder SEMICOLON() { return keyword(CommonKeyword.SEMICOLON); }
    public QueryBuilder ASTERISK() { return keyword(CommonKeyword.ASTERISK); }
    public QueryBuilder PERCENT() { return keyword(CommonKeyword.PERCENT); }
    public QueryBuilder AT_SIGN() { return keyword(CommonKeyword.AT_SIGN); }
    public QueryBuilder PRIVILEGES() { return keyword(CommonKeyword.PRIVILEGES); }
    public QueryBuilder FLUSH() { return keyword(DDLKeyword.FLUSH); }
    public QueryBuilder GRANT() { return keyword(DDLKeyword.GRANT); }
    public QueryBuilder SHOW() { return keyword(CommonKeyword.SHOW); }
    
    // Data types using dialect
    public QueryBuilder VARCHAR(int length) { return dataType(DataType.VARCHAR, length); }
    public QueryBuilder INT() { return dataType(DataType.INT); }
    public QueryBuilder DATE() { return dataType(DataType.DATE); }
    public QueryBuilder TEXT() { return dataType(DataType.TEXT); }
    public QueryBuilder BOOLEAN() { return dataType(DataType.BOOLEAN); }
    
    // Constraints using dialect
    public QueryBuilder NOTNULL() { return constraint(ColumnConstraint.NOT_NULL); }

    // Keep existing methods
    public QueryBuilder INPUT(String input) {
        this.query += input;
        this.addSpace();
        return this;
    }

    public QueryBuilder PARAM(String input){
        this.query += "'" + input + "'";
        this.addSpace();
        return this;
    }
    public QueryBuilder MODEL(String modelQuery) {
        this.query += "(" + modelQuery + ")";
        return this;
    }

    public QueryBuilder TRIM(String input) {
        this.query += input.replaceAll("\\s+", "");
        this.addSpace();
        return this;
    }

    public String CONSUME(){
        String query = this.getQuery();
        this.setQuery("");
        return query;
    }
    public String build() {
        String query = this.query.trim() + ";";
        this.setQuery("");
        return query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public SQLDialect getDialect() {
        return dialect;
    }

    public void setDialect(SQLDialect dialect) {
        this.dialect = dialect;
    }

}

