package lesson2method;

public class sleepMethod{
    private static int sec = 10;

    public static void main(String[] args) throws InterruptedException {
        sleepMethod.countDown();
    }

    public static void countDown() throws InterruptedException {
        while(sec >= 0) {
            Thread.sleep(1000);
            System.out.println(sec--);
        }
    }
}
