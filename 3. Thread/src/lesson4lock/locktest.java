package lesson4lock;

import java.util.concurrent.locks.ReentrantLock;

public class locktest {
    public static void main(String[] args) {
        BuyTicket buyticket = new BuyTicket();
        new Thread(buyticket,"thread1").start();
        new Thread(buyticket,"thread2").start();
        new Thread(buyticket,"thread3").start();
    }
}

class BuyTicket implements Runnable{
    private int ticket = 10;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try{
            while(true){
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + " buy ticket " + ticket--);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        } finally{
            lock.unlock();
        }
    }
}
