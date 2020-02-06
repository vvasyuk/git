package com.tryout.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)     //has runtime visibility
@Target(ElementType.TYPE)               //Annotates a type, such as a class, interfaces, annotation types, or enum declarations
public @interface MyAnnotation {
    String name();
    String adr() default "street";
}
