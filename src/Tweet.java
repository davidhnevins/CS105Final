import java.time.LocalDate;

// Makes individual Tweet data point
class Tweet implements DataPoint {
    private LocalDate date;    // Stores date of tweet
    private String text;    // Word we are looking at

    public Tweet(LocalDate date, String text) {
        this.date = date;
        this.text = text;
    }

    // Get date of a given tweet
    public LocalDate getDate() {
        return date;
    }

    // Get contents of a given tweet
    public String getText() {
        return text;
    }

    // Get type of data
    public String getType() {
        return "Tweet";
    }

    // Here because I don't know how to write methods that dont have to be in all classes that implement interface
    public int getValue() {
        return 0;
    }

    // Supports printing of the class to allow for testing
    public String toString() {
        String tweet = date + "\n" + text;
        return tweet;
    }
}