package lesson2method;

public class priorityMethod {
    public static void main(String[] args) {
        // main's default priority
        System.out.println(Thread.currentThread().getName() + "\t\t"+Thread.currentThread().getPriority());

        test t = new test();
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(t);
        Thread thread3 = new Thread(t);
        Thread thread4 = new Thread(t);

        thread1.setPriority(7);
        thread1.start();
        thread2.setPriority(5);
        thread2.start();
        thread3.setPriority(9);
        thread3.start();
        thread4.setPriority(1);
        thread4.start();

    }
}

class test implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t"+Thread.currentThread().getPriority());
    }
}