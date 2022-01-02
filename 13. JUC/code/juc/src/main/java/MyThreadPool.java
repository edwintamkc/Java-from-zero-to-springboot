import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor2 = Executors.newFixedThreadPool(3);
        ExecutorService executor3 = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is running");
            });
        }
    }
}
