package Lecture3.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Poem {
    public String title;
    public String author;
    private List<String> lines = new ArrayList<>();
    private Date date;

    // AF: 代表一首诗，包含四个属性：
    // title为诗的题目，
    // author为诗的作者，
    // lines为诗的文本行，
    // date为诗的发表日期
    public Poem(String t, String a, List<String> l, Date d) {
        title = t;
        author = a;
//        lines = l;
        lines = new ArrayList<>(l); // 创建一个新的 ArrayList 对象，包含与 l 相同的元素
//        date = d;
        date = new Date(d.getTime()); //防止表示泄露
    }

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);
        Date d2 = new Date(d.getTime());
        System.out.println(d2);
    }

    public void addOneLine(String newLine) {
        lines.add(newLine);
    }

    public Poem plagiarize(String newAuthor) {
        return new Poem(title, newAuthor, lines, date);//在构造函数的地方修改之后，此处可以不改
    }

    public void addPrefix(String prefix) {
//        for (int i = 0; i < lines.size(); i++) {
//            String newLine = prefix.concat(lines.get(i));
//            lines.remove(i);
//            lines.add(i, newLine);
//        }
//        虽然你试图为每行添加前缀，但是在同一时间你同时移除和添加元素。这种方式会导致
//        ConcurrentModificationException 异常，因为你正在遍历一个集合并且同时修改它。为了安全地
//        在每行的前面添加前缀，你可以使用一个临时列表来保存新的行，然后在循环结束后将其赋值回 lines 列表。
        List<String> newLines = new ArrayList<>();

        for (String line : lines) {
            String newLine = prefix + line;
            newLines.add(newLine);
        }

        lines.clear(); // Clear the original list

        lines.addAll(newLines); // Add all new lines to the original list
    }


    /**
     * @param start 起始行号
     * @param end   结束行号，start<=end
     * @return 从第start行开始到第end行结束的文本行
     */
    public List<String> getSomeLines(int start, int end) {
        List<String> some = new ArrayList<>();
        for (int i = start; i <= end; i++)
            some.add(lines.get(i));
        return some;
    }

    public List<String> getAllLines() {
//        return lines;
        return Collections.unmodifiableList(lines);
    }
}