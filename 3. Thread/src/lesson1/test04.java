package lesson1;

import java.util.concurrent.*;

public class test04 implements Callable<Boolean> {
    private static int num = 0;
    @Override
    public Boolean call() throws Exception {
        System.out.println(++num);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test04 t1 = new test04();
        test04 t2 = new test04();
        test04 t3 = new test04();

        // create service
        ExecutorService ser = Executors.newFixedThreadPool(3);

        // send request
        Future<Boolean> request1 = ser.submit(t1);
        Future<Boolean> request2 = ser.submit(t2);
        Future<Boolean> request3 = ser.submit(t3);

        // get result
        Boolean result1 = request1.get();
        Boolean result2 = request2.get();
        Boolean result3 = request3.get();

        // close service
        ser.shutdown();
    }
}
