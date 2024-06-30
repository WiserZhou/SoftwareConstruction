package Lecture3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Family family = new Family();
        family.people.add(new Person("as", 1));
        family.people.add(new Person("as2", 2));
        System.out.println(family.people);
        int familySize = family.people.size();
        System.out.println(familySize);
    }

}

class Family {
    // the people in the family
    public Set<Person> people;


    Family(){
        people = new HashSet<>();
    }

    /**
     * @return a list containing all the members
     * of the family, with no duplicates.
     */
    public List<Person> getMembers() {
        return new ArrayList<>(people);
    }
}
