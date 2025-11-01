package com.ud2_at1.services.database.query_builder.enums;

import com.mysql.cj.xdevapi.Table;

public enum DDLKeyword {
    CREATE("CREATE"),
    DROP("DROP"),
    ALTER("ALTER"),
    TABLE("TABLE"),
    DATABASE("DATABASE"),
    INDEX("INDEX"),
    CONSTRAINT("CONSTRAINT"),
    ADD("ADD"),
    COLUMN("COLUMN"),
    RENAME("RENAME"),
    PRIMARY("PRIMARY"),
    FOREIGN("FOREIGN"),
    KEY("KEY"),
    UNIQUE("UNIQUE"),
    REFERENCES("REFERENCES"),
    CHECK("CHECK"),
    USE("USE"),
    DATABASES("DATABASES"),
    IDENTIFIED("IDENTIFIED"),
    GRANT("GRANT"),
    FLUSH("FLUSH"),
    NULL("NULL"),
    DEFAULT("DEFAULT")
    ;


    private final String keyword;

    DDLKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}