import processing.core.PApplet;

import java.time.LocalDate;

abstract class Plotter {    // Abstract class b/c Plotter is only extended
    protected final PApplet p;   // Protected allows for ONLY subclass to see variable
    protected final DataSet set;

    public Plotter(PApplet p, DataSet set) {    // Constructor initializes PApplet
        this.p = p;
        this.set = set;
    }

    public DataSet getDataSet() {
        return set;
    }

    public abstract void plot(LocalDate startDate, LocalDate endDate, int startX, int startY, int endY, float widthPerDay);  // Abstract means no body has to be declared to the method.
    // If someone doesn't override plot, then the code won't even compile b/c abstract method, it must be overriden.
}