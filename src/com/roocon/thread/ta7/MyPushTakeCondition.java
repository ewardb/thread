package com.roocon.thread.ta7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyPushTakeCondition {
    public static void main(String[] args) {

        Resource1 resource = new Resource1();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        MyPush1 myPush = new MyPush1(resource);
        for(int i = 0 ; i< 20 ; i++){
            executorService.execute(myPush);
        }
        MyTake1 myTake = new MyTake1(resource);
        ExecutorService executorService1 = Executors.newFixedThreadPool(15);
        for(int i = 0 ; i<15 ; i++){
            executorService1.execute(myTake);
        }
    }


}
class  MyPush1 implements Runnable{

    private Resource1 resource;

    public MyPush1(Resource1 resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while(true){
            resource.put();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class  MyTake1 implements Runnable{
    private Resource1 resource;

    public  MyTake1(Resource1 resource){
        this.resource = resource;
    }
    @Override
    public void run() {
        while(true){
            resource.sub();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Resource1 {

    private int cont;

    private int max =10;

    private ReentrantLock lock = new ReentrantLock();

    Condition p = lock.newCondition();
    Condition t = lock.newCondition();

    public void put(){
        lock.lock();
        try {
            while (cont >= max){
//                TimeUnit.SECONDS.sleep(1);
                p.await();
            }
            cont ++;
        }catch (Exception e){

        }
        System.out.println(Thread.currentThread().getName() +" 生产了"+ cont);
        t.signalAll();
        lock.unlock();
    }

    public void sub(){
        lock.lock();
        try {
            while (cont <=0){
//                TimeUnit.SECONDS.sleep(1);
                t.await();
            }
            cont --;
        }catch (Exception e){}
        System.out.println(Thread.currentThread().getName() + " 消费了 "+cont);
        p.signalAll();
        lock.unlock();
    }

}