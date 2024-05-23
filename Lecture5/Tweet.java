package Lecture5;

import java.util.Date;

public class Tweet {
    private final String author;
    private final String text;
    private final Date timestamp;

    public Tweet(String author, String text, Date timestamp) {
        this.author = author;
        this.text = text;
        this.timestamp = timestamp;
    }


    /**
     * @return Twitter user who wrote the tweet
     */

    public String getAuthor() {
        return author;
    }

    /**
     * @return text of the tweet  *
     * <p>
     * public String getText() {return text;}   /** @return date/time when the tweet was sent
     */

    public Date getTimestamp() {
        return timestamp;
    }
}
