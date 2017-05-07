import java.time.LocalDate;

// Makes an individual data point, Trend
class Trend implements DataPoint {
    private LocalDate date;
    private String word;    // Word that we are looking at the trend of
    private int value;

    public Trend(LocalDate date, String word, int value) {
        this.date = date;
        this.word = word;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWord() {
        return word;
    }

    public int getValue() {
        return value;
    }

    // Supports printing of the class to allow for testing
    public String toString() {
        String trend = date + "\n" + word + ": " + value;
        return trend;
    }
}