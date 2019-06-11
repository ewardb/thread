package com.roocon.thread.t4;

public class DemoM {


    /**
     *
     *
     * 任何对象都可以作为锁，那么锁信息又存在对象的什么地方呢？
     * 存在对象头中
     *
     * 对象头中的信息
     * Mark Word  （包含锁信息）
     *      *  线程id
     *      *  epoch
     *      *  对象的分代年龄信息
     *      *  是否是偏向锁
     *      *  所标志位
     * Class Metadata Address
     * Array Length
     *
     *
     *
     *
     * https://www.jianshu.com/p/36eedeb3f912
     * 偏向锁 --
     *      每次获取锁和释放锁会浪费资源
     *      很多情况下，竞争锁不是由多个线程，而是由一个线程在使用。
     *      试用场景、：只有一个线程在访问同步代码块的场景
     *


     * 轻量级锁  桟针
     *      自旋
     *      多个线程可以同时
     * 重量级锁
     *      sync
     *
     *
     *
     *
     *
     * 匿名内部类！   ok
     * 泛型！FutureTask
     *
     * interupt
     * stop的区别
     * Markword
     *
     *
     *
     *
     *
     *
     * 活跃性问题：
     * 死锁：
     * 饥饿：	高优先级吞噬所有低优先级的CPU时间片
     * 	线程被永久堵塞在一个等待进入同步块的状态
     * 	等待的线程永远不被唤醒
     * 如何尽量避免饥饿问题
     * 	设置合理的优先级
     * 	使用锁来代替synchronized
     *
     *
     * 活锁：
     */
}
