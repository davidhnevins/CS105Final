import java.time.format.DateTimeFormatter;

class Tests {
    // RatingLoader tests -

    public void whenComparingRatingDataFormatToExpected_MatchesExpected(RatingLoader loader, String fileName) {
        RatingSet testSet = loader.load(fileName);    // Load test data
        Rating[] ratings = testSet.getAll();    // Store all ratings in new array for ease of use
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");    // Reformat data into month/day/year format and compare it accordingly
        for (int i = 0; i < ratings.length; i++) {
            // Approval rating comes first then disapproval
            if (ratings[i].getDate().format(formatter).equals("04/11/2017") && ratings[i].getType().equals("Approving") && ratings[i].getValue() == 40) {    // If all approval data is correct add 1
                i++;    // Add 1 to i if data for approving is correct, then check disapproving data
                if (ratings[i].getDate().format(formatter).equals("04/11/2017") && ratings[i].getType().equals("Disapproving") && ratings[i].getValue() == 55.0) {    // Checking all aspects of Rating
                    System.out.println("whenComparingRatingDataFormatToExpected_MatchesExpected\nTest passed!");
                    return;
                } //<>// //<>// //<>//
            }
            System.out.println(ratings[i].getDate());
            System.out.println("whenComparingRatingDataFormatToExpected_MatchesExpected\nTest FAILED!!!");    // If data is incorrect
            return;
        }
    }

    public void whenInvalidRatingFilePath_ThrowsFileNotFoundException(RatingLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenInvalidRatingFilePath_ThrowsFileNotFoundException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenInvalidRatingFilePath_ThrowsFileNotFoundException\nTest passed!");
        }
    }

    public void whenRatingHasInsufficientContents_ThrowsRuntimeException(RatingLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenRatingHasInsufficientContents_ThrowsRuntimeException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenRatingHasInsufficientContents_ThrowsRuntimeException\nTest passed!");
        }
    }

    public void whenRatingValueIsNotAnInt_ThrowsNumberFormatException(RatingLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenRatingValueIsNotAnInt_ThrowsNumberFormatException\nTest FAILED!!!");
        } catch (NumberFormatException e) {
            System.out.println("whenRatingValueIsNotAnInt_ThrowsNumberFormatException\nTest passed!");
        }
    }

    public void whenNotEnoughCommasInRating_ThrowsException(RatingLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenNotEnoughCommasInRating_ThrowsException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenNotEnoughCommasInRating_ThrowsException\nTest passed!");
        }
    }

    // TrendLoader tests -

    public void whenComparingTrendDataFormatToExpected_MatchesExpected(TrendLoader loader, String fileName) {
        TrendSet testSet = loader.load(fileName);    // Load test data
        Trend[] trends = testSet.getAll();    // Store all ratings in new array for ease of use
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

        if (trends[0].getDate().format(formatter).equals("01/17/2017") && trends[0].getValue() == 49) {
            System.out.println("whenComparingTrendDataFormatToExpected_MatchesExpected\nTest passed!");
            return;
        }
        System.out.println("whenComparingTrendDataFormatToExpected_MatchesExpected\nTest FAILED!!!");
        return;
    }

    public void whenInvalidTrendFilePath_ThrowsFileNotFoundException(TrendLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenInvalidTrendFilePath_ThrowsFileNotFoundException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenInvalidTrendFilePath_ThrowsFileNotFoundException\nTest passed!");
        }
    }

    public void whenTrendHasInsufficientContents_ThrowsRuntimeException(TrendLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenTrendHasInsufficientContents_ThrowsRuntimeException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenTrendHasInsufficientContents_ThrowsRuntimeException\nTest passed!");
        }
    }

    public void whenTrendValueIsNotAnInt_ThrowsNumberFormatException(TrendLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenTrendValueIsNotAnInt_ThrowsNumberFormatException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenTrendValueIsNotAnInt_ThrowsNumberFormatException\nTest passed!");
        }
    }

    // TweetLoader tests -

    public void whenComparingTweetDataFormatToExpected_MatchesExpected(TweetLoader loader, String fileName) {
        TweetSet testSet = loader.load(fileName);    // Load test data //<>// //<>// //<>//
        Tweet[] tweets = testSet.getAll();    // Store all ratings in new array for ease of use
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

        if (tweets[0].getDate().format(formatter).equals("04/19/2017") && tweets[0].getText().equals("#BuyAmericanHireAmerican\ud83c\uddfa\ud83c\uddf8 https://t.co/JPEXQTVR1E")) {
            System.out.println("whenComparingTweetDataFormatToExpected_MatchesExpected\nTest passed!");
            return;
        }
        System.out.println("whenComparingTweetDataFormatToExpected_MatchesExpected\nTest FAILED!!!");
        return;
    }

    public void whenLoadingTweetWithInvalidDate_ThrowsRuntimeException(TweetLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenLoadingTweetWithInvalidDate_ThrowsRuntimeException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenLoadingTweetWithInvalidDate_ThrowsRuntimeException\nTest passed!");
        }
    }

    public void whenInvalidTweetFilePath_ThrowsFileNotFoundException(TweetLoader loader, String fileName) {
        try {
            loader.load(fileName);
            System.out.println("whenInvalidTweetFilePath_ThrowsFileNotFoundException\nTest FAILED!!!");
        } catch (RuntimeException e) {
            System.out.println("whenInvalidTweetFilePath_ThrowsFileNotFoundException\nTest passed!");
        }
    }
}