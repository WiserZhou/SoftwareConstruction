package Lecture3;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        List<String> subjects = new ArrayList<>();
        subjects.add("6.045");
        subjects.add("6.031");
        subjects.add("6.036");
        MyIterator.dropCourse6((ArrayList<String>) subjects);


    }
}