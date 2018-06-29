package com.lily.kotlin.singleton.java;

/**
 * @Author rape flower
 * @Date 2018-06-29 14:20
 * @Describe 单例模式：Java方式
 * <p>内部类式</p>
 */
public class InnerSingleton {

    private InnerSingleton() {

    }

    private static class Holder {
        private static InnerSingleton instance = new InnerSingleton();
    }

    public static InnerSingleton getInstance() {
        return Holder.instance;
    }
}
