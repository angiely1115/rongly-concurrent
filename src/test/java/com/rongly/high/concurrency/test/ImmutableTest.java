package com.rongly.high.concurrency.test;

import com.vip.vjtools.vjkit.collection.ListUtil;
import com.vip.vjtools.vjkit.collection.MapUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: 线程安全策略不可变测试
 * @Date: 2018/10/13 11:37
 * @Version: 1.0
 * modified by:
 */
public class ImmutableTest {
    private static final Integer a = 1;

    private static final int c = 1;

    private static final String b = "雷峰塔";

    private static final Map<Integer,String> MAP = MapUtil.newHashMap();
    static {
        MAP.put(1,"1");
        MAP.put(2,"2");
        MAP.put(3,"3");
    }

    /**
     * 测试不可变之final
     */
    @Test
    public void testImmutableFinal(){
        /*a = 2;
        b = "3";
        a = a+1;
        c = 1;
        MAP = new HashMap<>();
        final定义变量不可变
        */
        MAP.put(3,"6");//但是修饰的值是可以修改
        System.out.println(MAP);
    }

    /**
     * 不可以修改的map 当正常的map转成了一个不可以修改map之后则对map修改的操作均不能操作了
     * 同list操作也一样
     */
    @Test
    public void testImmuTableUnModifiedMap(){
       Map<String,String> stringMap = MapUtil.newHashMap();
        stringMap.put("1","1");
        stringMap.put("2","2");
        stringMap.put("3","3");
        stringMap = MapUtil.unmodifiableMap(stringMap);
        stringMap.put("4","4");
        System.out.println(stringMap);
        ListUtil.unmodifiableList(ListUtil.newArrayList());
    }
}
