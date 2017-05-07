import java.time.LocalDate;

// DataSet is the base class for Rating/Trend/TweetSet, allowing us to put generic version of methods
class DataSet {    // By having all set classes extend DataSet, you don't have to add methods to plotter base class every time you want to add something
    private final DataPoint[] points;    // final ensures immutability

    public DataSet(DataPoint[] points) {
        this.points = points;
    }

    // Returns array of data points
    public DataPoint[] getDataPoints() {
        return points;
    }

    // Returns all dates
    public LocalDate[] getDates() {
        LocalDate[] dates = new LocalDate[points.length];
        for (int i = 0; i < points.length; i++) {
            dates[i] = points[i].getDate();
        }
        return dates;
    }

    // Returns min date
    public LocalDate getMinDate() {    // Loop through plotters and their dates getting max and min out of all dates
        LocalDate min = points[0].getDate();    // Initialize min date to first date in points
        for (int i = 0; i < points.length; i++) {
            if (points[i].getDate().compareTo(min) < 0) {
                min = points[i].getDate();
            }
        }
        return min;
    }

    // Returns max date
    public LocalDate getMaxDate() {
        LocalDate max = points[0].getDate();
        for (int i = 0; i < points.length; i++) {
            if (points[i].getDate().compareTo(max) > 0) {    // '> 0" means max is before points[i] date
                max = points[i].getDate();
            }
        }
        return max;
    }

  /*
  // Returns a list of Tweets between two dates
   public Tweet[] getBetween(String date, String endDate) {
   // Loops through 'tweets' array checking if the tweet is in between the indicated date
   // If tweet is in between indicated date add that tweet to the list
   }
   */
}