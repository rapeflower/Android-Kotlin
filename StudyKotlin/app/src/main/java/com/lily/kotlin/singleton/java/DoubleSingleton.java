package com.lily.kotlin.singleton.java;

/**
 * @Author rape flower
 * @Date 2018-06-29 13:34
 * @Describe 单例模式：Java方式
 * <p>双重检测式：判断空、加锁</p>
 */
public class DoubleSingleton {

    private DoubleSingleton() {

    }

    private static volatile DoubleSingleton instance;

    public static DoubleSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleSingleton.class) {
                if (instance == null) {
                    instance = new DoubleSingleton();
                }
            }
        }
        return instance;
    }
}
