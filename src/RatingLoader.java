import processing.core.PApplet;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Loads a rating set from a file, parces the data, stores it in an array, and returns a new RatingSet of that array
class RatingLoader {
    private PApplet p;    // Allows for use of Processing methods

    public RatingLoader(PApplet p) {    // Allows for use of Processing methods
        this.p = p;
    }

    // Load method - Parses data, stores in an array, returns new RatingSet of that array
    public RatingSet load(String fileName) {
        // Check if file exists
        File f = new File(fileName);
        if (!f.exists()) {
            throw new RuntimeException("File does not exist: " + fileName);    // Using runtimeexception because its not throwable, don't have to catch it
        }

        String[] lines = p.loadStrings(fileName);
        if (lines.length < 2) {      // Testing if lines has any data more than the header line
            throw new RuntimeException("No rating data found in " + fileName);
        }
        Rating[] approvalRatings = new Rating[lines.length - 1];    // Set length of ratings array to length of data loaded times two because it holds approval & disapproval. Minus two to account for subtraction of header line.
        Rating[] disapprovalRatings = new Rating[lines.length - 1];

        // Loop to load approving/disapproving arrays
        for (int i = 1; i < lines.length; i++) {    // Start loop at 1 to skip heading line in .csv
            String[] contents = p.split(lines[i], ',');    // Parse data
            if (contents.length != 6) {    // Checking if expected number of contents is met
                throw new RuntimeException("Unexpected content parts - expected 6: " + lines[i]);
            }

            String date = contents[0];    // Hold date as a string variable
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("M/d/yyyy");    // Expected date format. Necessary to have date as type Date

            // Load approvals into array
            try {
                LocalDate d = LocalDate.parse(date, sdf);
                Rating approveRating = new Rating(d, "Approving", Integer.parseInt(contents[2]));    // Create individual ratings...second item in array is disapproval rating
                approvalRatings[i - 1] = approveRating;    // i-1 because loop starts at 1
            } catch (NumberFormatException e) {    // If the rating is not an integer throw exception
                throw new NumberFormatException("Approving value should be an integer but was not: " + contents[2]);
            } catch (DateTimeParseException e) {    // If parse exception, print out error
                throw new RuntimeException("Invalid rating date " + date);
            }

            // Load disapprovals into array
            try {
                LocalDate d = LocalDate.parse(date, sdf);
                Rating disapproveRating = new Rating(d, "Disapproving", Integer.parseInt(contents[3]));    // Third item in array is disapproval rating
                disapprovalRatings[i - 1] = disapproveRating;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Approving value should be an integer but was not: " + contents[2]);
            } catch (DateTimeParseException e) { // If parse exception, print out error
                throw new RuntimeException("Invalid rating date " + date);
            }
        }
        Rating[] combined = new Rating[approvalRatings.length + disapprovalRatings.length];
        System.arraycopy(approvalRatings, 0, combined, 0, approvalRatings.length);    // Copying approvalRatings into combined ratings array
        System.arraycopy(disapprovalRatings, 0, combined, approvalRatings.length, disapprovalRatings.length);    // Copying disapprovalRatings into combined ratings array after approvalRatings
        RatingSet loadSet = new RatingSet(combined);    // Combined contains approval & disapproval ratings
        return loadSet;
    }
}