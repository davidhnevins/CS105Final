// Load and make a list of trends
class TrendSet extends DataSet {
    private final Trend[] trends;    // Array of trends

    public TrendSet(Trend[] trends) {
        // Initialize trends using 'this.' (this.trends = trends)
        super(trends);
        this.trends = trends;
    }

    // Supports printing of RatingSet for testing
    public String toString() {
        String TrendSet = trends[0].toString();
        return TrendSet;
    }

    // Returns all trend data
    public Trend[] getAll() {
        return trends;
    }

  /*
   // Calculates and returns average search trend frequency (average of values)
   public int average() {
   }
   
   // *** There will likely be more methods as the project progresses ***
   */
}