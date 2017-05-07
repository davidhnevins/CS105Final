import processing.core.PApplet;
import processing.data.JSONArray;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Loads a Tweet set from a file, parces the data, stores it in an array, and returns a new TweetSet of that array
class TweetLoader {
    private PApplet p;

    public TweetLoader(PApplet p) {
        this.p = p;
    }

    public TweetSet load(String fileName) {
        // Loads the data (json file) given the file name
        // Creates Tweet for each tweet and adds it to the tweets array, then creates a TweetSet with that array and returns it
        JSONArray raw;    // JSON array to store loaded Tweet data
        Tweet[] tweets;    // Array to store tweets
        File f = new File(fileName);    // Allows for testing if file exists and stops running of code if it doesn't exist.
        if (!f.exists()) {
            throw new RuntimeException("File does not exist: " + fileName);    // Using runtimeexception because its not throwable, don't have to catch it
        }

        raw = p.loadJSONArray(fileName);    // Loading JSON array into 'raw'
        tweets = new Tweet[raw.size()];    // Set length of tweet array to size of data loaded

        // Loop through array getting JSON object and creating a tweet from that json object by accessing the contents of the json object then add that tweet to the array of tweets

        for (int i = 0; i < raw.size(); i++) {
            // get json object, create tweet from object, add tweet to tweets array
            String date = (raw.getJSONObject(i).getString("created_at"));
            String text = raw.getJSONObject(i).getString("text");

            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy");    // Creating data date format - "Tue Jan 17 19:58:10 +0000 2017"

            try {    // Must have try catch b/c java makes you try catch parse exceptions
                LocalDate d = LocalDate.parse(date, sdf);    // Parse date text from file and turns into date object
                Tweet newTweet = new Tweet(d, text);    // Create new tweet out of loaded data
                tweets[i] = newTweet;    // Fill array with tweets
            } catch (DateTimeParseException e) {    // If parse exception, print out error
                throw new RuntimeException("Invalid Tweet date " + date);
            }
        }
        TweetSet loadSet = new TweetSet(tweets);
        return loadSet; // Create and return TweetSet
    }
}