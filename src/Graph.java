import processing.core.PApplet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Graph {
    private final PApplet p;    // Private final to preserve immutability
    private final Plotter[] plotters;
    private final int width;    // Allows for user to set canvas size
    private final int height;


    public Graph(PApplet p, Plotter[] plotters, int width, int height) {
        this.p = p;
        this.plotters = plotters;
        this.width = width;
        this.height = height;
    }

    public void plot() {    // Loops through Plotter[] calling '.plot' on each, which plots graph of the given data
        p.getSurface().setSize(width, height);    // Changes canvas size
        int padding = 30;
        int startX = padding;    // Upper left corner
        int startY = padding;
        // Width and height from the starting point
        int width = p.width - padding;    // Width of graph is the width of the canvas minus padding to account for space between edge of canvas and start of graph
        int height = p.height - padding;

        // Draw axes start
        p.line(startX, startY, startX, height);    // Y-axis
        p.line(startX, height, width, height);     // X-axis
        // Need to get data from plotters array and plot tick marks, dates, etc.

        // Draw tick marks and label y-axis start
        int numForLabel = 10;
        for (int y = height; y > startY; y -= height / 10) {    // Draw tick marks on Y-axis
            int x = 30;    // Constant b/c Y-axis
            String numLabel = Integer.toString(numForLabel);
            p.line(x, y, x - 5, y);
            p.fill(0);
            p.textAlign(p.RIGHT, p.CENTER);    // Align text with tick marks
            p.text(numLabel, x - 10, y);
            numForLabel += 10;
            // Draw axes end
        }

        // Draw tick marks and label x-axis start
        // For max and min use the dates that fall within all sets of data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d");    // Format for date
        DataSet combinedSet = getCombinedDataSet();
        LocalDate startDate = combinedSet.getMinDate();    // Gets min date of all data sets
        LocalDate endDate = combinedSet.getMaxDate();    // Gets max date of all data sets
        long timeFrame = ChronoUnit.DAYS.between(startDate, endDate);
        int numOfTicks = 20;
        int incrementOfTicks = (int) timeFrame / numOfTicks;    // '/numOfTicks' because I want that number of ticks on the x-axis
        float startYTickPosition = height - 5;    // height is the starting y-position of the x-axis and of the first point of y-axis (declared and initialized above)
        float endYTickPosition = height + 5;    // '+ or - 5' creates 5 pixel line on each side of axis (10 px total)
        float xTickPosition = startX;    // startX is the x-position of the y-axis and of the first point of x-axis
        p.textAlign(p.CENTER, p.TOP);
        for (int i = 0; i < numOfTicks; i++) {
            String date = startDate.plusDays(incrementOfTicks * i).format(formatter);    // Increase date accordingly and format date
            System.out.println(startDate);
            p.line(xTickPosition, startYTickPosition, xTickPosition, endYTickPosition);    // Draw tick

            if (i % 2 == 0) {    // Write date on every other tick
                p.text(date, xTickPosition, endYTickPosition);
            }

            xTickPosition += width / numOfTicks;    // Spacing out ticks accordingly. Width (size of graph) / numOfTicks
        }

        // Loop through plotters and call plot on each of them to graph the given data
        for (int i = 0; i < plotters.length; i++) {
            plotters[i].plot();
        }
    }

    // Need to get data, use start and end to get dates and therefore determine # tick marks necessary for axes

    // Returns combined array of DataPoints
    public DataSet getCombinedDataSet() {
        int lengthOfDataPoints = 0;
        for (int i = 0; i < plotters.length; i++) {    // Loop through to get combined length
            lengthOfDataPoints += plotters[i].getDataSet().getDataPoints().length;
        }

        DataPoint[] combined = new DataPoint[lengthOfDataPoints];    // Array to store combined data
        int startingIndex = 0;    // Starts at 0 to copy first data set to beginning of combined array
        for (int i = 0; i < plotters.length; i++) {    // Loop through
            System.arraycopy(plotters[i].getDataSet().getDataPoints(), 0, combined, startingIndex, plotters[i].getDataSet().getDataPoints().length);    // Copying  data points into combined array
            startingIndex += plotters[i].getDataSet().getDataPoints().length;    // Change the startingIndex where data will be stored
        }
        DataSet combinedSet = new DataSet(combined);
        return combinedSet;
    }
}