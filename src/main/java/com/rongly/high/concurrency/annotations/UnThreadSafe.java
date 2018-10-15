package com.rongly.high.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: lvrongzhuan
 * @Description: 线程不安全标志注解
 * @Date: 2018/10/12 16:13
 * @Version: 1.0
 * modified by:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)//编译的时候就丢弃 这里只是标志作用 并不要在运行时使用
public @interface UnThreadSafe {
    String value() default "";
}
