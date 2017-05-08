// Creates individual rating data point
// Contains information about a single rating data point (e.g. date, value, approval/disapproval etc.)

import java.time.LocalDate;

class Rating implements DataPoint {    // 'Implements' DataPoint instead of extend because DataPoint is interf
    private LocalDate date;    // Stores date as string
    private String type;    //  Type of rating: approval, disapproval, unsure
    private int value;

    public Rating(LocalDate date, String type, int value) {
        // Initialize instance variables using 'this.'
        this.date = date;
        this.type = type;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    // Supports printing of the class for testing
    public String toString() {
        String rating = date + "\n" + type + ": " + value;
        return rating;
    }
}