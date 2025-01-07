package com.example.timesheet.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Timer {

    boolean enabled() default true;
    // INFO, DEBUG, ERROR , TRACE Level.DEBUG
    org.slf4j.event.Level level() default org.slf4j.event.Level.INFO;
}
