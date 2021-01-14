package lesson1;

public class test05 {
    public static void main(String[] args) {
        You you = new You();
        //you.HappyMarry(); 原本寫法，自己嘅function自己call
        //宜家用一個中介嘅方式送you呢個object入去，再用中介嘅HappyMarry() call
        //WeddingCompany weddingCompany = new WeddingCompany(you);
        //weddingCompany.HappyMarry();

        //再進一步簡化，用lambda expression
        //留意本身係new Thread(sd一個Runnable interface).start();
        new Thread(()-> System.out.println("I am lambda expression")).start();
        new WeddingCompany(new You()).HappyMarry(); //呢一行係第8,9嘅簡化版
    }
}

interface Marry{
    void HappyMarry();
}

class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("I am so happy to marry u~");
    }
}

class WeddingCompany implements Marry{
    private Marry target;

    public WeddingCompany(Marry target){
        this.target = target;
    }

    private void before(){
        System.out.println("Before marry~~Prepare the wedding");
    }

    private void after(){
        System.out.println("After the wedding~~Get the money");
    }

    @Override
    public void HappyMarry() {
        before();
        target.HappyMarry();
        after();
    }
}
