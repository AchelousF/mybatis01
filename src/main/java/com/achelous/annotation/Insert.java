package com.achelous.annotation;

import java.lang.annotation.*;

/**
 * @Auther: fanJiang
 * @Date: Create in 16:24 2018/4/6
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Insert {

    String value();
}
