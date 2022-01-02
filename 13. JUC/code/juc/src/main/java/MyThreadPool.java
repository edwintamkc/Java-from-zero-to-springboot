import java.util.concurrent.*;

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

        ExecutorService executor4 = new ThreadPoolExecutor(
                3, 6,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
