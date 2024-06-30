package Lecture3;

class ClassAdapter2 extends adapter implements adapter2 {

    @Override
    public void ope2() {
        System.out.println("ope2");
    }
}

interface adapter2 {
    public void ope1();

    public void ope2();
}

class adapter {
    public void ope1() {
        System.out.println("ope1");
    }
}

public class ClassAdapter {
    public static void main(String[] args) {
        adapter2 adapter = new ClassAdapter2();
        adapter.ope2();
        adapter.ope1();
    }
}