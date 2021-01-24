package lesson2method;

public class yeildMethod {
    public static void main(String[] args) {
        myYield yeildTest = new myYield();

        new Thread(yeildTest, "A").start();
        new Thread(yeildTest,"B").start();
    }
}

class myYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start working");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " finish!");

    }
}
