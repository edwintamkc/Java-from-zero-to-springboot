package lesson3synchronization;

public class UnsafeBank {
    public static void main(String[] args) {
        Account acc = new Account(100, "pocket money");

        Drawing you = new Drawing(acc, 50,"You");
        Drawing otherPpl = new Drawing(acc, 100,"Other ppl");
        you.start();
        otherPpl.start();
    }
}

class Account{
    public int money;
    public String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread{
    Account account;
    int drawingMoney;
    int yourMoney;

    public Drawing(Account account, int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println("Your account's money is not enough!");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money -= drawingMoney;
            yourMoney += drawingMoney;
            System.out.println(account.name + " has " + account.money);
            System.out.println(this.getName() + " has " + yourMoney);
        }
    }
}
