package lesson2method;

public class stopMethod implements Runnable{
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("I am thread " + i++);
        }
    }

    public void stop(){
        flag = false;
    }

    public static void main(String[] args) {
        stopMethod testThread = new stopMethod();
        new Thread(testThread).start();

        for (int i = 0; i < 1000; i++) {
            if(i == 900){
                testThread.stop();
                System.out.println("The thread is stop!");
            }
            System.out.println("I am main " + i);
        }
    }
}
