import processing.core.PApplet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class RatingPlotter extends Plotter {

    public RatingPlotter(PApplet p, RatingSet set) {
        super(p, set);    // Using super says: Call the plotter constructor. Don't need initialization b/c Plotter has it.
    }

    /*
    // startDate: the min date on the x axis of graph
    // endDate: the max date on the x axis of graph
    // startX: the left most plottable x value (based on padding/axes location)
    // endX: the right most plottable x value
    // startY: the bottom most plottable Y value
    // endY: the top most plottable Y value
    public void plot(LocalDate startDate, LocalDate endDate, int startX, int endX, int startY, int endY) {
        get x increment (pixels between plot points on x axis) based on endX - startX and number of days between startDate and endDate
        loop through dates in set, starting at startDate, ending at endDate {
            compute x and y values to draw a point at
            if last x,y values exist, draw a line between new point and last point
            store last x,y values for plotted point
        }
    }
     */

    // Plots points on graph
    public void plot(LocalDate startDate, LocalDate endDate, int startX, int startY, int endY, float widthPerDay) {
        // Start plotting approval line
        int ySpan = endY - startY;
        long rangeOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        float lastX = 0;
        float lastY = 0;

        int lineStroke = 2;
        int pointStroke = 5;

        Rating testPoint = (Rating) ((RatingSet) set).getApprovals()[((RatingSet) set).getApprovals().length - 1];    // Last point in getApprovals
        float testX = startX + (widthPerDay * ChronoUnit.DAYS.between(startDate, testPoint.getDate()));
        long testDays = ChronoUnit.DAYS.between(startDate, testPoint.getDate());
        float testY = endY - ((float) ((testPoint.getValue() / 100.0) * ySpan));
        p.strokeWeight(3);
        p.stroke(0, 129, 255);
        p.point(testX, endY);

        DataPoint[] points = ((RatingSet) set).getApprovals();       // Cast RatingSet to getApprovals

        for (int i = 0; i < points.length; i++) {
            Rating rating = (Rating) points[i];      // Need to
            if (rating.getDate().isBefore(startDate) || rating.getDate().isAfter(endDate)) {        // Checks if date is in range
                continue;
            }       // Otherwise plot
            float x = startX + (widthPerDay * ChronoUnit.DAYS.between(startDate, rating.getDate()));        // X-position
            float y = endY - ((float) ((rating.getValue() / 100.0) * ySpan));      // Y-position
            p.strokeWeight(lineStroke);
            if (lastX != 0 && lastY != 0) {     // Only plot line when there is a previous point
                p.line(lastX, lastY, x, y);
            }
            p.strokeWeight(pointStroke);
            p.point(x, y);
            lastX = x;
            lastY = y;
        }
        // End plotting approval line

        // Start plotting disapproval line
        float lastXDisapproval = 0;
        float lastYDisapproval = 0;
        p.stroke(255, 76, 59);
        DataPoint[] disapprovalPoints = ((RatingSet) set).getDisapprovals();       // Cast RatingSet to getApprovals
        for (int i = 0; i < disapprovalPoints.length; i++) {
            Rating disapprovalRating = (Rating) disapprovalPoints[i];      // Need to
            if (disapprovalRating.getDate().isBefore(startDate) || disapprovalRating.getDate().isAfter(endDate)) {        // Checks if date is in range
                continue;
            }       // Otherwise plot
            float x = startX + (widthPerDay * ChronoUnit.DAYS.between(startDate, disapprovalRating.getDate()));        // X-position
            float y = endY - ((float) ((disapprovalRating.getValue() / 100.0) * ySpan));      // Y-position
            p.strokeWeight(lineStroke);
            if (lastXDisapproval != 0 && lastYDisapproval != 0) {     // Only plot line when there is a previous point
                p.line(lastXDisapproval, lastYDisapproval, x, y);
            }
            // Draw point for each DataPoint
            p.strokeWeight(pointStroke);
            p.point(x, y);
            lastXDisapproval = x;
            lastYDisapproval = y;
        }
    }
}