import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MyCallable {
    public static void main(String[] args) {
        FutureTask task = new FutureTask(new MyThread());
        new Thread(task).start();
    }
}

class MyThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "Call!";
    }
}