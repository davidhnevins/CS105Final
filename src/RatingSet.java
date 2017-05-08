// Stores array of ratings with special (some aggregate) operations to apply 
// Class that contains array of ratings
// Stores an array of ratings in an instance variable so you can have methods on that class that apply business logic to turn a collection of data points into relevant information

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    // Returns all approval ratings
    public Rating[] getApprovals() {
        List<Rating> approvals = new ArrayList<Rating>();       // List = resizable array
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i].getType().equals("Approving")) {
                approvals.add(ratings[i]);
            }
        }
        Collections.sort(approvals, new Comparator<Rating>() {      // Re-order approval ratings so earliest date is first
            @Override
            public int compare(Rating rating1, Rating rating2) {

                return rating1.getDate().compareTo(rating2.getDate());
            }
        });
        Rating[] result = new Rating[approvals.size()];
        result = approvals.toArray(result);     // Convert list to array
        return result;
    }

    // Returns all disapproval ratings
    public Rating[] getDisapprovals() {
        List<Rating> disapprovals = new ArrayList<Rating>();       // List = resizable array
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i].getType().equals("Disapproving")) {
                disapprovals.add(ratings[i]);
            }
        }
        Collections.sort(disapprovals, new Comparator<Rating>() {      // Re-order approval ratings so earliest date is first
            @Override
            public int compare(Rating rating1, Rating rating2) {

                return rating1.getDate().compareTo(rating2.getDate());
            }
        });
        Rating[] result = new Rating[disapprovals.size()];
        result = disapprovals.toArray(result);     // Convert list to array
        return result;
    }

  /*        Potential methods
   // Special operations to be applied to the data [there will be more]
   public Rating[] getRatings() {
   return ratings;
   }
   public Rating getMaxRating() {
   }
   public Rating getMinRating() {
   }
   */
}