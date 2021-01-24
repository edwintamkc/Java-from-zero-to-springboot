package lesson1;

public class test02 implements Runnable{
    public static void main(String[] args) {
        test02 testThread = new test02();

        //Thread thread = new Thread(testThread);
        //thread.start();
        new Thread(testThread).start();

        for(int i = 0; i < 1000; i++){
            System.out.println("I am coding " + i);
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < 500; i++){
            System.out.println("I am sleeping " + i);
        }
    }
}
