import processing.core.PApplet;

class TrendPlotter extends Plotter {

    public TrendPlotter(PApplet p, TrendSet set) {
        super(p, set);    // Using super says: Call the plotter constructor. Don't need initialization b/c Plotter has it.
    }

    public void plot() {    // No parameter. Allows subclass to support a plot method.
        // Write method for plotting the RatingSet data on graph
        //p.line(....);
        //set.....;
    }
}