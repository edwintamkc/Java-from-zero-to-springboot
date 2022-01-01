public class MyRunnable {
    public static void main(String[] args) {
        new Thread(new MyThread2()).start();
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
}