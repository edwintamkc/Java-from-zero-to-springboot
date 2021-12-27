package lesson2method;

public class joinMethod {
    public static void main(String[] args) throws InterruptedException {
        joinTest jointest = new joinTest();
        Thread thread = new Thread(jointest);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if(i == 500)
                thread.join();
            System.out.println("I am main! " + i);
        }
    }
}

class joinTest implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("VIP is coming!" + i);
        }
    }
}
