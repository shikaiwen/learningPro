/**
 * Created by Administrator on 2016/7/24.
 */
public class TestOutOfMem {


    public static void main(String[] args) {
        test();

        System.out.println("123");
    }

    static void test(){

        test();

    }
}
