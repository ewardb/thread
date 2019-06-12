package com.roocon.thread.ta7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyPushTake {

    public static void main(String[] args) {
        Resource resource = new Resource();
        MyTake myTake = new MyTake(resource);
        ExecutorService executorService1 = Executors.newFixedThreadPool(8);
        for(int i = 0 ; i<8 ; i++){
            executorService1.execute(myTake);
        }



        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyPush myPush = new MyPush(resource);
        for(int i = 0 ; i< 5 ; i++){
            executorService.execute(myPush);
        }
        executorService.shutdown();
        executorService1.shutdown();

    }
}

class  MyPush implements Runnable{

    private Resource resource;

    public MyPush(Resource resource){
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

class  MyTake implements Runnable{
    private Resource resource;

    public  MyTake(Resource resource){
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

class Resource {

    private int cont;

    private int max =10;

    public synchronized void put(){
        try {
            while (cont >= max){
//                TimeUnit.SECONDS.sleep(1);
                wait();
            }
            cont ++;
        }catch (Exception e){

        }
        System.out.println(Thread.currentThread().getName() +" 生产了"+ cont);
        notifyAll();
    }

    public synchronized void sub(){
        try {
            while (cont <=0){
//                TimeUnit.SECONDS.sleep(1);
                wait();
            }
            cont --;
        }catch (Exception e){}
        System.out.println(Thread.currentThread().getName() + " 消费了 "+cont);
        notifyAll();
    }

}