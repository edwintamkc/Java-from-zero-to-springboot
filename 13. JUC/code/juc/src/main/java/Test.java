public class Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                ticket.sell();
            }
        }, "C").start();
    }
}

class Ticket {
    private int tickets = 500;

    public synchronized void sell(){
        if(tickets > 0){
            System.out.println("Ticket " + (tickets--) + " is sold by " + Thread.currentThread().getName() + ". Ticket left: " + tickets);
        }
    }
}
