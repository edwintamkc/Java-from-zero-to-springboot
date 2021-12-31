import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "C").start();
    }
}

class Ticket2 {
    private int tickets = 500;

    Lock lock = new ReentrantLock();

    public void sell(){

        lock.lock();

        try {
            if (tickets > 0) {
                System.out.println("Ticket " + (tickets--) + " is sold by " + Thread.currentThread().getName() + ". Ticket left: " + tickets);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
