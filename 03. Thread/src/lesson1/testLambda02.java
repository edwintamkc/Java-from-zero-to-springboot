package lesson1;

public class testLambda02 {

    public static void main(String[] args) {

        //ILove love = (int a) -> System.out.println("i love u " + a);
        //ILove love = (a) -> System.out.println("i love u " + a); //唔要return type
        ILove love = a -> System.out.println("i love u " + a); //連括號都唔要

        love.love(3000);


    }
}

interface ILove{
    void love(int a);
}
