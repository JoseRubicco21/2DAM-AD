package com.ud2_at1.services.database.query_builder;

public class QueryBuilder {
    
    private String query;
    
    private void addSpace(){
        this.query += " ";
    }

    public QueryBuilder CREATE(){
        this.query += "CREATE";
        this.addSpace();
        return this;
    }

    public QueryBuilder TABLE(){
        this.query += "TABLE";
        this.addSpace();
        return this;
    }

    public QueryBuilder DROP(){
        this.query += "DROP";
        this.addSpace();
        return this;
    }

    public QueryBuilder IF(){
        this.query += "IF";
        this.addSpace();
        return this;
    }

    public QueryBuilder EXISTS(){
        this.query += "EXISTS";
        this.addSpace();
        return this;
    }

    public QueryBuilder NOT(){
        this.query += "NOT";
        this.addSpace();
        return this;
    }

    public QueryBuilder DATABASE(){
        this.query += "DATABASE";
        this.addSpace();
        return this;
    }

    public QueryBuilder INPUT(String input){
        this.query += input;
        this.addSpace();
        return this;
    }

    public QueryBuilder GRANT(){
        this.query += "GRANT";
        this.addSpace();
        return this;
    }


    public QueryBuilder FLUSH(){
        this.query += "FLUSH";
        this.addSpace();
        return this;
    }

    public QueryBuilder PRIVILEGES(){
        this.query += "PRIVILEGES";
        this.addSpace();
        return this;
    }

    public QueryBuilder ATSIGN(){
        this.query += "@";
        this.addSpace();
        return this;
    }

    public QueryBuilder IDENTIFIED(){
        this.query += "IDENTIFIED";
        this.addSpace();
        return this;
    }

    public QueryBuilder BY(){
        this.query += "BY";
        this.addSpace();
        return this;
    }

    public QueryBuilder PERCENT(){
        this.query += "%";
        this.addSpace();
        return this;
    }

    public String build(){
        return this.query.trim() + ";";
    }


    public QueryBuilder SHOW(){
        this.query += "SHOW";
        this.addSpace();
        return this;
    }

    public QueryBuilder USE(){
        this.query += "USE";
        this.addSpace();
        return this;
    }
    
    public QueryBuilder DATABASES(){
        this.query += "DATABASES";
        this.addSpace();
        return this;
    }

    public QueryBuilder CHARSET(){
        this.query += "CHARSET";
        this.addSpace();
        return this;
    }
    
    public QueryBuilder COLLATION(){
        this.query += "COLLATION";
        this.addSpace();
        return this;
    }

    public QueryBuilder USER(){
        this.query += "USER";
        this.addSpace();
        return this;
    }

    public QueryBuilder ON(){
        this.query += "ON";
        this.addSpace();
        return this;
    }

    public QueryBuilder ALL(){
        this.query += "ALL";
        this.addSpace();
        return this;
    }

    public QueryBuilder TO(){
        this.query += "TO";
        this.addSpace();
        return this;
    }
    
    public QueryBuilder VALUES(){
        this.query += "VALUES";
        this.addSpace();
        return this;
    }

    public QueryBuilder SET(){
        this.query += "SET";
        this.addSpace();
        return this;
    }

    public QueryBuilder WHERE(){
        this.query += "WHERE";
        this.addSpace();
        return this;
    }

    public QueryBuilder AND(){
        this.query += "AND";
        this.addSpace();
        return this;
    }

    public QueryBuilder OR(){
        this.query += "OR";
        this.addSpace();
        return this;
    }

    public QueryBuilder UPDATE(){
        this.query += "UPDATE";
        this.addSpace();
        return this;
    }

    public QueryBuilder DELETE(){
        this.query += "DELETE";
        this.addSpace();
        return this;
    }

    public QueryBuilder FROM(){
        this.query += "FROM";
        this.addSpace();
        return this;
    }

    public QueryBuilder INSERT(){
        this.query += "INSERT";
        this.addSpace();
        return this;
    }

    public QueryBuilder INTO(){
        this.query += "INTO";
        this.addSpace();
        return this;
    }

    public QueryBuilder SELECT(){
        this.query += "SELECT";
        this.addSpace();
        return this;
    }

    public QueryBuilder LIMIT(){
        this.query += "LIMIT";
        this.addSpace();
        return this;
    }

    public QueryBuilder ORDERBY(){
        this.query += "ORDER BY";
        this.addSpace();
        return this;
    }
    public QueryBuilder DESC(){
        this.query += "DESC";
        this.addSpace();
        return this;
    }
    public QueryBuilder ASC(){
        this.query += "ASC";
        this.addSpace();
        return this;
    }

    public QueryBuilder COUNT(){
        this.query += "COUNT";
        this.addSpace();
        return this;
    }

    public QueryBuilder AVG(){
        this.query += "AVG";
        this.addSpace();
        return this;
    }
    public QueryBuilder SUM(){
        this.query += "SUM";
        this.addSpace();
        return this;
    }
    public QueryBuilder MIN(){
        this.query += "MIN";
        this.addSpace();
        return this;
    }
    public QueryBuilder MAX(){
        this.query += "MAX";
        this.addSpace();
        return this;
    }
    public QueryBuilder DISTINCT(){
        this.query += "DISTINCT";
        this.addSpace();
        return this;
    }

    public QueryBuilder JOIN(){
        this.query += "JOIN";
        this.addSpace();
        return this;
    }

    public QueryBuilder INNER(){
        this.query += "INNER";
        this.addSpace();
        return this;
    }
    public QueryBuilder LEFT(){
        this.query += "LEFT";
        this.addSpace();
        return this;
    }
    public QueryBuilder RIGHT(){
        this.query += "RIGHT";
        this.addSpace();
        return this;
    }

    public QueryBuilder ONDELETE(){
        this.query += "ON DELETE";
        this.addSpace();
        return this;
    }
    public QueryBuilder CASCADE(){
        this.query += "CASCADE";
        this.addSpace();
        return this;
    }

    public QueryBuilder PRIMARY(){
        this.query += "PRIMARY";
        this.addSpace();
        return this;
    }
    
    public QueryBuilder KEY(){
        this.query += "KEY";
        this.addSpace();
        return this;
    }

    public QueryBuilder AUTO_INCREMENT(){
        this.query += "AUTO_INCREMENT";
        this.addSpace();
        return this;
    }

    public QueryBuilder UNIQUE(){
        this.query += "UNIQUE";
        this.addSpace();
        return this;
    }

    public QueryBuilder DEFAULT(){
        this.query += "DEFAULT";
        this.addSpace();
        return this;
    }

    public QueryBuilder NULL(){
        this.query += "NULL";
        this.addSpace();
        return this;
    }

    public QueryBuilder NOTNULL(){
        this.NOT().NULL().addSpace();
        return this;
    }

    public QueryBuilder FOREIGN(){
        this.query += "FOREIGN";
        this.addSpace();
        return this;
    }

    public QueryBuilder REFERENCES(){
        this.query += "REFERENCES";
        this.addSpace();
        return this;
    }

    public QueryBuilder CHECK(){
        this.query += "CHECK";
        this.addSpace();
        return this;
    }

    public QueryBuilder LIKE(){
        this.query += "LIKE";
        this.addSpace();
        return this;
    }

    public QueryBuilder BETWEEN(){
        this.query += "BETWEEN";
        this.addSpace();
        return this;
    }

    public QueryBuilder IS(){
        this.query += "IS";
        this.addSpace();
        return this;
    }

    public QueryBuilder TRUE(){
        this.query += "TRUE";
        this.addSpace();
        return this;
    }

    public QueryBuilder FALSE(){
        this.query += "FALSE";
        this.addSpace();
        return this;
    }

    public QueryBuilder ALTER(){
        this.query += "ALTER";
        this.addSpace();
        return this;
    }

    public QueryBuilder ADD(){
        this.query += "ADD";
        this.addSpace();
        return this;
    }

    public QueryBuilder COLUMN(){
        this.query += "COLUMN";
        this.addSpace();
        return this;
    }

    public QueryBuilder RENAME(){
        this.query += "RENAME";
        this.addSpace();
        return this;
    }

    public QueryBuilder VARCHAR(int length){
        this.query += "VARCHAR(" + length + ")";
        this.addSpace();
        return this;
    }

    public QueryBuilder INT(){
        this.query += "INT";
        this.addSpace();
        return this;
    }

    public QueryBuilder TEXT(){
        this.query += "TEXT";
        this.addSpace();
        return this;
    }

    public QueryBuilder DATE(){
        this.query += "DATE";
        this.addSpace();
        return this;
    }

    public QueryBuilder DATETIME(){
        this.query += "DATETIME";
        this.addSpace();
        return this;
    }

    public QueryBuilder BOOLEAN(){
        this.query += "BOOLEAN";
        this.addSpace();
        return this;
    }

    public QueryBuilder FLOAT(){
        this.query += "FLOAT";
        this.addSpace();
        return this;
    }

    public QueryBuilder DOUBLE(){
        this.query += "DOUBLE";
        this.addSpace();
        return this;
    }

    public QueryBuilder DECIMAL(int precision, int scale){
        this.query += "DECIMAL(" + precision + "," + scale + ")";
        this.addSpace();
        return this;
    }

    public QueryBuilder CHAR(int length){
        this.query += "CHAR(" + length + ")";
        this.addSpace();
        return this;
    }

    public QueryBuilder BLOB(){
        this.query += "BLOB";
        this.addSpace();
        return this;
    }

    public QueryBuilder MODEL(String modelQuery){
        this.query += "(" + modelQuery + ")";
        return this;
    }

    public QueryBuilder EVERYTHING(){
        this.query += "*";
        this.addSpace();
        return this;
    }

    public QueryBuilder EQUALS(){
        this.query += "=";
        this.addSpace();
        return this;
    }
}

