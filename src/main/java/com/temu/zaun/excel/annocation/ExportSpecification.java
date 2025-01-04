package com.temu.zaun.excel.annocation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExportSpecification {
    String bizCode();

    String desc() default "";

    String appKey();
}
