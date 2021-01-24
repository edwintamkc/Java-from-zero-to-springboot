package lesson2method;

public class statusMethod {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Finish!!!!");
        });

        Thread.State state = thread.getState();
        System.out.println(state);
        thread.start();
        System.out.println(state);

        while(state != Thread.State.TERMINATED){
            Thread.sleep(500);
            state = thread.getState();
            System.out.println(state);
        }
    }
}



