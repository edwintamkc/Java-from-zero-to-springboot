import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7, ()->{
            System.out.println("Summon the dragon by 7 dragon balls!");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                System.out.println("Get dragon ball " + Thread.currentThread().getName());

                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i+1)).start();
        }
    }
}
