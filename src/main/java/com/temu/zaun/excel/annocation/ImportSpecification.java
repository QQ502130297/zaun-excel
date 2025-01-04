package com.temu.zaun.excel.annocation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportSpecification {
    String bizCode();

    String desc() default "";

    String appKey();
}
