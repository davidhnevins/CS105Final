// Stores array of Tweets with special (some aggregate) operations to apply
class TweetSet extends DataSet {
    Tweet[] tweets;

    public TweetSet(Tweet[] tweets) {
        // Initialize tweets using 'this.'
        super(tweets);
        this.tweets = tweets;
    }

    public Tweet[] getAll() {
        return tweets;
    }

    // Supports printing of RatingSet for testing
    public String toString() {
        String TweetSet = tweets[0].toString();
        return TweetSet;
    }

  /*
  
   // Returns a list of Tweets that contain the indicated word 
   public Tweet[] listOf(String word) {
   // Loops through 'tweets' array checking if the tweet contains the indicated word
   // If tweet includes indicated word add that tweet to the list
   }
   
   // *** There will likely be more methods as the project progresses ***
   */
}