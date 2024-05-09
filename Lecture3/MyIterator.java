package Lecture3;

import java.util.ArrayList;
import java.util.List;

/**
 * A Lecture3.MyIterator is a mutable object that iterates over
 * the elements of a List<String>, from first to last.
 * This is just an example to show how an iterator works.
 * In practice, you should use the List's own iterator
 * object, returned by its iterator() method.
 */
public class MyIterator {

    private final List<String> list;
    private int index;
    // list[index] is the next element that will be returned
    //   by next()
    // index == list.size() means no more elements to return

    /**
     * Make an iterator.
     *
     * @param list list to iterate over
     */
    public MyIterator(List<String> list) {
        this.list = list;
        this.index = 0;
    }

    /**
     * Test whether the iterator has more elements to return.
     *
     * @return true if next() will return another element,
     * false if all elements have been returned
     */
    public boolean hasNext() {
        return index < list.size();
    }

    /**
     * Get the next element of the list.
     * Requires: hasNext() returns true.
     * Modifies: this iterator to advance it to the element
     * following the returned element.
     *
     * @return next element of the list
     */
    public String next() {
        final String element = list.get(index);
        ++index;
        return element;
    }

    /**
     * Drop all subjects that are from Course 6.
     * Modifies subjects list by removing subjects that start with "6."
     *
     * @param subjects list of MIT subject numbers
     */
    public static void dropCourse6(ArrayList<String> subjects) {
        MyIterator iter = new MyIterator(subjects);
        while (iter.hasNext()) {
            String subject = iter.next();
            if (subject.startsWith("6.")) {
                subjects.remove(subject);
            }
        }
    }

}