package com.ud2_at1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name();
    String type();
    int length() default -1;
    boolean nullable() default true;
    boolean primaryKey() default false;
    boolean autoIncrement() default false;
    boolean unique() default false;
    boolean foreignKey() default false;
    String references() default "";
}