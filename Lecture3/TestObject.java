package Lecture3;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestObject {
    public static void main(String[] args) {
        // 局部变量personRef（一个对象引用）存储在栈中，
        // 而Person对象及其属性值存储在堆中
        Person personRef = new Person("Alice", 30);

        // 访问并修改对象的属性，证明属性存储在堆中
        System.out.println(personRef.name); // 输出:Alice
        personRef.age = 31;
        System.out.println(personRef.age); // 输出:31
        System.out.println(personRef.hashCode());

        // 创建另一个Person对象引用，指向同一个堆中的对象
        Person anotherRef = personRef;
        System.out.println(anotherRef.hashCode());
        anotherRef.name = "Bob"; // 通过anotherRef修改name属性，也会影响到personRef所指向的对象
        System.out.println(personRef.name); // 输出:Bob，证明两者指向堆中同一对象
        System.out.println(anotherRef.hashCode());

        String str = "a";
        System.out.println(str.hashCode());
        str = str.concat("b");
        System.out.println(str.hashCode());

        StringBuilder str1 = new StringBuilder("a");
        System.out.println(str1.hashCode());
        str1.append("b");
        System.out.println(str1.hashCode());

        String s = " Hello ";
        s += " World ";
        s.trim();
        System.out.println(s);

        List<Integer> myData = Arrays.asList(-5, -3, -2);
        System.out.println(sumAbsolute(myData));
        System.out.println(sum(myData));

        final StringBuilder sb = new StringBuilder("abc");
        sb.append("d");
//        sb = new StringBuilder("e");
        System.out.println(sb);



    }

    public static int sum(List<Integer> list) {
        int sum = 0;
        for (int x : list) {
            sum += x;
        }
        return sum;
    }

    public static int sumAbsolute(List<Integer> list) {
        list.replaceAll(Math::abs);
        return sum(list);
    }
}

class Person {
    String name; // 引用类型属性
    int age;    // 基本类型属性

    // 构造方法
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}