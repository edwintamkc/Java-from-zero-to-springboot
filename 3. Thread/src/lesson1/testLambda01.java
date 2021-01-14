package lesson1;

public class testLambda01 {
    // 3. static member class
    static class Like2 implements ILike{

        @Override
        public void lambda() {
            System.out.println("I like lambda2!!");
        }
    }


    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 4. local inner class
        class Like3 implements ILike{

            @Override
            public void lambda() {
                System.out.println("I like lambda3!!");
            }
        }

        like = new Like3();
        like.lambda();

        // 5. anonymous inner class (必須藉助interface或者upper class)
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I like lambda4!!");
            }
        };
        like.lambda();

        // 6. 用lambda簡化
        // 由於前面已經知道like係ILike interface，所以可以主省略第五步嘅new ILike()同一堆call function嘅野
        like = () -> System.out.println("I like lambda5!!");
        like.lambda();
    }


}

// 1. create functional interface
interface ILike{
    void lambda();
}
// 2. class implements functional interface
class Like implements ILike{

    @Override
    public void lambda() {
        System.out.println("I like lambda!!");
    }
}