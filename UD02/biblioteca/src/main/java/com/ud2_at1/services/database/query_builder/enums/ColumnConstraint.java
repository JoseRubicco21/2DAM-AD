package com.ud2_at1.services.database.query_builder.enums;

public enum ColumnConstraint {
    NOT_NULL("NOT NULL"),
    AUTO_INCREMENT("AUTO_INCREMENT"),
    DEFAULT("DEFAULT"),
    NULL("NULL"),
    TRUE("TRUE"),
    FALSE("FALSE");

    private final String constraint;

    ColumnConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getConstraint() {
        return constraint;
    }
}