package lesson2method;

public class daemonMethod {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);  // default is false

        thread.start();

        Thread thread2 = new Thread(you);
        thread2.start();
    }

}
class God implements Runnable{
    @Override
    public void run() {
        while(true) {
            System.out.println("God is helping you!");
        }
    }
}
class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            System.out.println("Your life is wonderful!");
        }
        System.out.println("You gg -.-");
    }
}