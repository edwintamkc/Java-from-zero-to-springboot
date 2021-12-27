package lesson3synchronization;

public class UnsafeBuyTicket {
    public static void main(String[] args) {
        allTicket allticket = new allTicket();
        new Thread(allticket,"ppl1").start();
        new Thread(allticket,"ppl2").start();
        new Thread(allticket,"ppl3").start();
    }
}

class allTicket implements Runnable{
    private int ticket = 10;
    private boolean flag = true;

    private synchronized void buy() throws InterruptedException {
        if(ticket <= 0){
            flag = false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " buy ticket " + ticket--);
    }

    @Override
    public void run() {
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}