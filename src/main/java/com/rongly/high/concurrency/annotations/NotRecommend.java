package com.rongly.high.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: lvrongzhuan
 * @Description: 不推荐的写法
 * @Date: 2018/10/12 16:19
 * @Version: 1.0
 * modified by:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)//编译的时候就丢弃 这里只是标志作用 并不要在运行时使用
public @interface NotRecommend {
}
