package com.roocon.thread.t6;

public class DemoM {


    /**
     *
     * 锁从入：同一个线程 拿到同一把锁，是可从入得  避免了死锁问题
     *  一个线程 访问a  a调用b   a b 都是锁方法。 这样不会死锁。因为有锁从入
     *
     *      sync
     *      lock
     *
     *轻量级
     *  自旋锁：
     *      旋cpu 时间分片
     *
     *  当一个线程拿到对象头的信息，到桟针中，另外一个线程如果想获取的话，
     *   那么他就不停的 自旋，等待第一个线程执行完毕，才能执行。。。自旋等待
     *
     *  死锁：
     *
     *
     */
}
