// Stores array of ratings with special (some aggregate) operations to apply 
// Class that contains array of ratings
// Stores an array of ratings in an instance variable so you can have methods on that class that apply business logic to turn a collection of data points into relevant information

class RatingSet extends DataSet {
    private final Rating[] ratings;    // Stores array of ratings

    public RatingSet(Rating[] ratings) {
        // Initialize ratings using 'this.' (this.ratings = rating)
        super(ratings);
        this.ratings = ratings;
    }

    // Supports printing of RatingSet for testing
    public String toString() {
        // for (int i = 0; i < ratings.length
        String RatingSet = ratings[0].toString();
        return RatingSet;
    }

    // Returns all ratings
    public Rating[] getAll() {
        return ratings;
    }

  /*        Potential methods
   // Special operations to be applied to the data [there will be more]
   public Rating[] getRatings() {
   return ratings;
   }
   // Returns list of approving ratings
   public Rating[] getApprovals() {
   }
   // Returns list of disapproval ratings
   public Rating[] getDisapprovals() {
   }
   public Rating getMaxRating() {
   }
   public Rating getMinRating() {
   }
   */
}