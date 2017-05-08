import processing.core.PApplet;

import java.time.LocalDate;

class TweetPlotter extends Plotter {

    public TweetPlotter(PApplet p, TweetSet set) {
        super(p, set);    // Using super says: Call the plotter constructor. Don't need initialization b/c Plotter has it.
    }

    public void plot(LocalDate startDate, LocalDate endDate, int startX, int startY, int endY, float widthPerDay) {
        // Write method for plotting the RatingSet data on graph
        //p.line(....);
        //set.....;
    }
}