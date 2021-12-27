package lesson3synchronization;

public class deadlock {
    public static void main(String[] args) {
        Makeup girl1 = new Makeup(0,"girl1");
        Makeup girl2 = new Makeup(1,"girl2");

        girl1.start();
        girl2.start();
    }
}

class Lipstick{

}

class Mirror{

}

class Makeup extends Thread{
    // static，確保每種資源只有一個
    public static Lipstick lipstick = new Lipstick();
    public static Mirror mirror = new Mirror();

    public int choice; // 0 = 攞唇膏先， 1 = 攞鏡先
    public String girlname;

    public Makeup(int choice, String name) {
        this.choice = choice;
        this.girlname = name;
    }

    private void makeup() throws InterruptedException {
        if(choice == 0){
            synchronized (lipstick){
                System.out.println(this.girlname + " get lipstick");
                Thread.sleep(1000);
                synchronized (mirror){
                    System.out.println(this.girlname + " get mirror");
                }
            }
        } else{
            synchronized (mirror) {
                System.out.println(this.girlname + " get mirror");
                Thread.sleep(2000);
                synchronized (lipstick) {
                    System.out.println(this.girlname + " get lipstick");
                }
            }
        }
    }

    @Override
    public void start() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
