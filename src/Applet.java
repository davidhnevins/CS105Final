/*
David Nevins
5/8/17
Final Project - Comparing data
*/

import processing.core.PApplet;

import javax.swing.*;

public class Applet extends PApplet {


    public void settings() {
        size(500, 500);
    }

    public void draw() {
        // Test data start
        RatingLoader testRatingLoader = new RatingLoader(this);    // Create loader
        RatingSet testRatingSet = testRatingLoader.load("data/approval.csv");    // Load rating data and create set
        RatingPlotter testRatingPlotter = new RatingPlotter(this, testRatingSet);

        TrendLoader testTrendLoader = new TrendLoader(this);
        TrendSet testTrendSet = testTrendLoader.load("data/FakeNews.csv");
        TrendPlotter testTrendPlotter = new TrendPlotter(this, testTrendSet);

        TweetLoader testTweetLoader = new TweetLoader(this);
        TweetSet testTweetSet = testTweetLoader.load("data/condensed_2017.json");
        TweetPlotter testTweetPlotter = new TweetPlotter(this, testTweetSet);

        Plotter[] plotters = {testRatingPlotter, testTrendPlotter, testTweetPlotter};
        Graph testGraph = new Graph(this, plotters);        // 'this' allows to draw on canvas

        testGraph.plot();
        // Test data end

        JFrame frame = new JFrame("MyForm");
        frame.setContentPane(new UserSettingsForm(testGraph).getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        noLoop();
    }

/*
// Loader Tests -

 // Rating Helper Objects/Classes:
 RatingLoader ratingTestLoader = new RatingLoader(this);
 Tests ratingTest = new Tests();

 // Ratings - loading from valid file and checking if format and contents of data are correct
 // Need to use file directory for data b/c using 'java.io.File' to check if file exists in loader classes
 ratingTest.whenComparingRatingDataFormatToExpected_MatchesExpected(ratingTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/ApprovalTest.csv");

 // Ratings - loading data with invalid number of contents
 ratingTest.whenRatingHasInsufficientContents_ThrowsRuntimeException(ratingTestLoader,"/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/ApprovalInsufficientContents.csv");

 // Ratings - loading data with incorrect value type (not an int)
 ratingTest.whenRatingValueIsNotAnInt_ThrowsNumberFormatException(ratingTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/ApprovalNotNum.csv");

 // Ratings - loading from invalid/non-existent file
 // Attempting to load file that does not exist (line below)
 ratingTest.whenInvalidRatingFilePath_ThrowsFileNotFoundException(ratingTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/invalid_approval_test.csv");

 // Ratings - loading data with not enough/no commas
 ratingTest.whenNotEnoughCommasInRating_ThrowsException(ratingTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/ApprovalCommas.csv");

 //-----------------------------

 // Trend Helper Objects/Classes:
 TrendLoader trendTestLoader = new TrendLoader(this);
 Tests trendTest = new Tests();

 // Trend - loading from valid file and checking if format and contents of data are correct
 trendTest.whenComparingTrendDataFormatToExpected_MatchesExpected(trendTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/TrendTest.csv");

 // Trend - loading data from invalid/non-existent file
 trendTest.whenInvalidTrendFilePath_ThrowsFileNotFoundException(trendTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/invalid_trend_test.csv");

 // Trend - loading data with fewer number of contents than expected/needed
 trendTest.whenTrendHasInsufficientContents_ThrowsRuntimeException(trendTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/TrendInsufficientContents.csv");

 // Trend - loading data with incorrect value type (not an int)
 trendTest.whenTrendValueIsNotAnInt_ThrowsNumberFormatException(trendTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/TrendNotNum.csv");

 //-----------------------------

 // Tweet Helper Objects/Classes:
 TweetLoader tweetTestLoader = new TweetLoader(this);
 Tests tweetTest = new Tests();

 // Tweet - loading from valid file and checking if format and contents of data are correct - ** FALING AS OF NOW [DATA IS LOADING PROPERLY BUT NEED HELP W/ TEST] **
 tweetTest.whenComparingTweetDataFormatToExpected_MatchesExpected(tweetTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/TweetTest.json");

 // Tweet - loading data with invalid date and checking if erorr is thrown when date is incorrect
 tweetTest.whenLoadingTweetWithInvalidDate_ThrowsRuntimeException(tweetTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/TweetInvalidDate.json");

 // Tweet - loading data from invalid/non-existent file
 tweetTest.whenInvalidTweetFilePath_ThrowsFileNotFoundException(tweetTestLoader, "/Users/davidnevins/Documents/Processing/Final Project/Final-Project-CS-105/FakeNews_to_Ratings/Data/Test_Data/invalid_trend_test.json");
*/
}