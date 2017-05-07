import processing.core.PApplet;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Loads a trend set from a file, parces the data, stores it in an array, and returns a new TrendSet of that array
class TrendLoader {
    private PApplet p;

    public TrendLoader(PApplet p) {
        this.p = p;
    }

    // Load method - Parces data, stores in an array, returns new TrendSet of that array
    public TrendSet load(String fileName) {    // Name of file needs to be the word of the search trend
        // Check if file exists
        File f = new File(fileName);
        if (!f.exists()) {
            throw new RuntimeException("File does not exist: " + fileName);    // Using runtimeexception because its not throwable, don't have to catch it
        }

        // Loop through parsing the data, creating a Trend, storing it in the array of trends, then return a new TrendSet with the array of trends
        String[] lines = p.loadStrings(fileName);    // Loads data
        Trend[] trends = new Trend[lines.length];    // Set length of ratings array to length of data loaded

        for (int i = 0; i < lines.length; i++) {
            String[] contents = p.split(lines[i], ',');    // Splitting data up by commas b/c '.csv' format
            if (contents.length != 2) {    // Ensure contents.length is the expected size for everything I want to do with it, otherwise throw exception
                throw new RuntimeException("Unexpected content parts - expected 2: " + lines[i]);
            }

            String date = contents[0];
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-M-d");

            try {    // Throws exception if data is not of the proper type
                LocalDate d = LocalDate.parse(date, sdf);
                Trend searchTrend = new Trend(d, fileName, Integer.parseInt(contents[1]));
                trends[i] = searchTrend;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Approving value should be an integer but was not: " + contents[1]);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Invalid rating date " + date);
            }
        }
        TrendSet loadSet = new TrendSet(trends);    // Create new set from array of trends
        return loadSet;
    }
}