package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * 同步线程安全容器在特定的场景下也存在线程不安全的情况
 * 比如多个线程进行不同的操作
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
