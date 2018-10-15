package com.rongly.high.concurrency.test;

/**
 * @Author: lvrongzhuan
 * @Description:枚举方式创建单例
 * @Date: 2018/10/13 10:48
 * @Version: 1.0
 * modified by:
 */
public class EnumSingleton {
    private EnumSingleton() {
    }
    public static EnumSingleton getEnumSingletonInstance(){
        return SingletonEnum.a.getInstance();
    }
    private enum SingletonEnum{
        a;
       private EnumSingleton enumSingleton;
       //JVM保证只会执行一次
        SingletonEnum() {
            System.out.println("枚举内构造方法执行");
            enumSingleton = new EnumSingleton();
        }
        public EnumSingleton getInstance() {
            return enumSingleton;
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                EnumSingleton enumSingleton = EnumSingleton.getEnumSingletonInstance();
                System.out.println(Thread.currentThread().getName()+enumSingleton.hashCode());
            }).start();
        }
    }
}
