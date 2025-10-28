package com.ud2_at1.models.generic;

public class Database  {
    // This is needed as we might have one or more database in our program.
    private String name;
    private String charset;
    private String collationType;

    public Database(String name, String charset, String collationType){
        this.name = name;
        this.charset = charset;
        this.collationType = collationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCollationType() {
        return collationType;
    }

    public void setCollationType(String collationType) {
        this.collationType = collationType;
    }

    
}
