package Lecture3;

public class TryCatch {
    public static void main(String[] args) {
        System.out.println(method());
    }

    public static int method() {
        int x = 10;
        try {
            x = 20;
//            System.out.println(1 / 0);
            return x;
        } catch (Exception e) {
            x = 30;
            return x + 1;
        } finally {
            x = 40;
            System.out.println(x);
            return x + 2;
        }
    }
}
