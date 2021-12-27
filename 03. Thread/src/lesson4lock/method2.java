package lesson4lock;

public class method2 {
    public static void main(String[] args) {
        Show show = new Show();
        new Actor(show).start();
        new Audience(show).start();
    }
}

class Actor extends Thread{
    private Show show;
    public Actor(Show show){
        this.show = show;
    }

    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            if(i%2 == 0){
                this.show.play("American show");
            } else if(i % 5 ==0){
                this.show.play("Another show 1");
            } else{
                this.show.play("Another show 2");
            }
        }
    }
}

class Audience extends Thread{
    private Show show;
    public Audience(Show show){
        this.show = show;
    }

    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            this.show.watch();
        }
    }
}

class Show{
    String showName;
    boolean flag = true; // ture = 正在錄製， false = 已經錄好

    // show
    public synchronized void play(String showName){
        if(!flag){ //已經錄好，actor已經wait()，等觀眾睇
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.showName = showName;
        System.out.println("Actors: " + showName);
        this.flag = !this.flag; // 已經錄好節目，改變信號
        this.notifyAll(); // 通知觀眾睇
    }

    // watch
    public synchronized void watch(){
        if(flag){ //正在錄製，觀眾應該wait()，等actor錄
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Audience watch " + showName);
        this.flag = !flag;
        this.notifyAll(); // 通知演員錄

    }
}