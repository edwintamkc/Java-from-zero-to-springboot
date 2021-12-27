package lesson4lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testPool {
    public static void main(String[] args) {
        // 1. 創建線程池thread pool
        ExecutorService ser = Executors.newFixedThreadPool(10);

        // 2. 執行
        ser.execute(new myThread());
        ser.execute(new myThread());
        ser.execute(new myThread());
        ser.execute(new myThread());
        ser.execute(new myThread());

        // 3. 關閉線程池
        ser.shutdownNow();
    }
}

class myThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}