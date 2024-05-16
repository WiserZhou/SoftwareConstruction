package Lecture3;

import java.math.BigInteger;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> subjects = new ArrayList<>();
        subjects.add("6.045");
        subjects.add("6.031");
        subjects.add("6.036");
        MyIterator.dropCourse6((ArrayList<String>) subjects);
        for(String str:subjects){
            System.out.println(str);
        }

        Iterator<String> iter =  subjects.iterator();
        while(iter.hasNext()){
            String subject = iter.next();
            if(subject.startsWith("6.")){
                iter.remove();
            }
        }

        List<String> list = new ArrayList<>();
        list.add("ab");
        List<String> list2 = Collections.unmodifiableList(list);
        list2.add("c");


    }
}