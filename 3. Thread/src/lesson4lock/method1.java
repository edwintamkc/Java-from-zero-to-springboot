package lesson4lock;

public class method1 {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Producer(container).start();
        new Consumer(container).start();
    }
}

class Chicken{
    int id;
    public Chicken(int id){
        this.id = id;
    }
}

class Producer extends Thread{
    SynContainer container;
    public Producer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("整左第" + i + "隻雞");
        }
    }
}

class Consumer extends Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            container.pop();
            System.out.println("買左第" + i + "隻雞");
        }
    }
}

class SynContainer{
    //容器大小 = 10
    private Chicken[] chickens = new Chicken[10];
    private int count = 0; // for chicken's index

    public synchronized void push(Chicken chicken){
        // 容器已滿，通知consumer，並且自己開始wait()
        while (count == chickens.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 容器仲有位置，放入
        chickens[count++] = chicken;
        // 通知Consumer攞雞
        this.notifyAll();
    }

    public synchronized Chicken pop(){
        // 判斷可唔可以買，唔得就等
        while (count == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 可以買
        Chicken returnChicken = chickens[--count];
        // 已經買完一隻，有一個位空出嚟，通知Producer生產
        this.notifyAll();
        return returnChicken; // 帶走隻雞
    }
}