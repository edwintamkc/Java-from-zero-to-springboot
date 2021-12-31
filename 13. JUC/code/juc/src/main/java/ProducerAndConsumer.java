import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        Chicken chicken = new Chicken();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    chicken.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer2").start();
    }
}

// synchronized version
class Chicken{
    private int quantity = 0;

    public synchronized void increment() throws InterruptedException {
        while(quantity != 0) { // there are some chicken left, no need to produce, keep waiting
            this.wait();
        }
        quantity++; // quantity = 0 now, need to produce 1 chicken for consumer
        System.out.println(Thread.currentThread().getName() + " produced 1 chicken. Quantity => " + quantity);
        notifyAll(); // notify consumer it is done
    }

    public synchronized void decrement() throws InterruptedException {
        while(quantity == 0) { // no chicken, keep waiting
            this.wait();
        }
        quantity--; // quantity > 0 now, consumer could buy 1 chicken
        System.out.println(Thread.currentThread().getName() + " consumed 1 chicken. Quantity => " + quantity);
        notifyAll(); // notify producer
    }
}

// lock version
class Chicken2{
    private int quantity = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public  void increment() throws InterruptedException {
        lock.lock();
        try {
            while(quantity != 0) {
                condition.await();
            }
            quantity++;
            System.out.println(Thread.currentThread().getName() + " produced 1 chicken. Quantity => " + quantity);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while(quantity == 0) {
                condition.await();
            }
            quantity--;
            System.out.println(Thread.currentThread().getName() + " consumed 1 chicken. Quantity => " + quantity);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}