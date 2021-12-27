package lesson1;

public class test03 implements Runnable{

    private String winner = null;
    @Override
    public void run() {
        for(int i = 0;i <= 100; i++){
            boolean flag = checkWinner(i);
            if(flag)
                break;

            System.out.println(Thread.currentThread().getName() + " run " + i + " step");

        }
    }

    private boolean checkWinner(int step){
        if(winner != null){
            return true;
        }
        if(step >= 100){
            winner = Thread.currentThread().getName();
            System.out.println("The winner is " + winner);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        test03 testthread = new test03();
        new Thread(testthread, "rabbit").start();
        new Thread(testthread, "gui").start();
    }
}
