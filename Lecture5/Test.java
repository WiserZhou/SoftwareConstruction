package Lecture5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    /**
     * @return a list of 24 inspiring tweets, one per hour today
     */

    public static List<Tweet> tweetEveryHourToday() {
        List<Tweet> list = new ArrayList<Tweet>();
        Date date = new Date();
        for (int i = 0; i < 24; i++) {
            date.setHours(i);
            list.add(new Tweet("rbmllr", "keep it up! you can do it", date)); // 这里保存的是对象的引用，最后所有的tweet都是同一个Date对象
        }
        return list;
    }

    public static void main(String[] args) {
        List<Tweet> list = tweetEveryHourToday();
        for (Tweet tweet : list) {
            System.out.println(tweet.getTimestamp());
        }

    }

}
