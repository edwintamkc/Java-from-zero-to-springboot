package lesson1;

// create thread, method 1 : inherits Thread class and overwrite run()
// new a object of test01, then call start() method to run
public class test01 extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 200; i++){
            System.out.println("I am thread + " + i);
        }
    }

    public static void main(String[] args) {
        // main thread

        test01 test = new test01();

        test.start();
        for(int i = 0; i < 2000; i++){
            System.out.println("I am main + " + i);
        }

    }
}
