package com.mmall.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

public class VectorExample3 {

    // java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1) { // foreach
        for(Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    //但是删除倒数第二个元素没有问题
    private static void test2(Vector<Integer> v1) { // iterator
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(2)) {
                v1.remove(i);
            }
        }
    }

    // success
    private static void test3(Vector<Integer> v1) { // for
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    /**
     * java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 2
     * 但是删除最后一个元素没有问题
     * @param v1
     */
    private static void test5(Vector<Integer> v1) { // for
        int size = v1.size();
        for (int i = 0; i < size; i++) {
           Integer j = v1.get(i);
            if (j.equals(3)) {
                v1.remove(j);
            }
        }
    }

    /**
     * 没有问题
     * @param v1
     */
    private static void test7(Vector<Integer> v1) { // for
        for (int i = 0; i < v1.size(); i++) {
            Integer j = v1.get(i);
            if (j.equals(1)) {
                v1.remove(i);
            }
        }
    }
    public static void main(String[] args) {

        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test7(vector);
    }

    /**
     * 这样没有问题
     * @param v1
     */
    private static void test4(Vector<Integer> v1) { // iterator
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(1)) {
                iterator.remove();
            }
        }
        System.out.println(v1);
    }
}
