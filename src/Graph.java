import processing.core.PApplet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Graph {
    private final PApplet p;    // Private final to preserve immutability
    private final Plotter[] plotters;

    // Constructor to initialize instance variables
    public Graph(PApplet p, Plotter[] plotters) {
        this.p = p;
        this.plotters = plotters;
    }

    public void plot() {    // Loops through Plotter[] calling '.plot' on each, which plots graph of the given data
        p.background(255);
        int padding = 30;
        int startX = padding;    // Upper left corner
        int startY = padding;
        // Width and height from the starting point
        int endX = p.width - padding;    // Width of graph is the width of the canvas minus padding to account for space between edge of canvas and start of graph
        int endY = p.height - padding;
        int width = (endX - startX);
        int height = (endY - startY);

        // Draw axes start
        p.line(startX, startY, startX, endY);    // Y-axis
        p.line(startX, endY, endX, endY);     // X-axis
        // Draw axes end

        // Draw tick marks and label y-axis start
        int numForLabel = 0;
        /*
        for (float y = height; y >= startY; y -= (440 / 10.0)) {    // Draw tick marks on Y-axis
            int x = 30;    // Constant b/c Y-axis
            String numLabel = Integer.toString(numForLabel);
            p.line(x, y, x - 5, y);
            p.fill(0);
            p.textAlign(p.RIGHT, p.CENTER);    // Align text with tick marks
            p.text(numLabel, x - 10, y);
            numForLabel += 10;
            // // Draw tick marks and label y-axis end
        }
        */

        int numberOfYTicks = 10;
        double yIncrement = (float) height / numberOfYTicks;
        for (int i = 0; i <= numberOfYTicks; i++) {
            float x = 30;
            float y = (float) (endY - (i * yIncrement));
            String numLabel = (i * 10) + "";
            p.line(x, y, x - 5, y);
            p.fill(0);
            p.textAlign(p.RIGHT, p.CENTER);    // Align text with tick marks
            p.text(numLabel, x - 10, y);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d");    // Format for date
        DataSet combinedSet = getCombinedDataSet();
        LocalDate startDate = combinedSet.getMinDate();    // Gets min date of all data sets
        LocalDate endDate = combinedSet.getMaxDate();    // Gets max date of all data sets
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        int numberOfXTicks = 20;
        int numberOfDaysPerTick = (int) Math.ceil((float) numberOfDays / numberOfXTicks);     // 108 / 20 = 6
        int numberOfDaysRepresented = numberOfDaysPerTick * numberOfXTicks; // 6 * 20 = 120
        float widthPerDay = (float) width / numberOfDaysRepresented;        // 440 / 120 = 3.6666 | have to divide by number of days on x-axis
        float xIncrement = (float) width / numberOfXTicks;
        for (int i = 0; i <= numberOfXTicks; i++) {
            float y = endY;
            float x = startX + (float) (i * xIncrement);
            p.line(x, y, x, y + 5);
            p.fill(0);
            p.textAlign(p.CENTER, p.CENTER);    // Align text with tick marks
            if (i % 2 == 0) {    // Write date on every other tick
                LocalDate date = startDate.plusDays((long) (numberOfDaysPerTick * i));
                p.text(date.format(formatter), x, y + 10);
            }
        }
/*
        // Draw tick marks and label x-axis start
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d");    // Format for date
        DataSet combinedSet = getCombinedDataSet();
        LocalDate startDate = combinedSet.getMinDate();    // Gets min date of all data sets
        System.out.println(startDate);
        LocalDate endDate = combinedSet.getMaxDate();    // Gets max date of all data sets
        System.out.println(endDate);
        long timeFrame = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println(timeFrame);
        int numOfTicks = 20;
        int dayIncrement = (int) timeFrame / numOfTicks;    // '/numOfTicks' because I want that number of ticks on the x-axis
        float xIncrement = width / numOfTicks;
        float startYTickPosition = endY - 5;    // height is the starting y-position of the x-axis and of the first point of y-axis (declared and initialized above)
        float endYTickPosition = endY + 5;    // '+ or - 5' creates 5 pixel line on each side of axis (10 px total)
        float xTickPosition = startX;    // startX is the x-position of the y-axis and of the first point of x-axis
        p.textAlign(p.CENTER, p.TOP);
        for (int i = 0; i <= numOfTicks; i++) {
            String date = startDate.plusDays(dayIncrement * i).format(formatter);    // Increase date accordingly and format date
            p.line(xTickPosition, startYTickPosition, xTickPosition, endYTickPosition);    // Draw tick

            if (i % 2 == 0) {    // Write date on every other tick
                p.text(date, xTickPosition, endYTickPosition);
            }
            xTickPosition += xIncrement;    // Spacing out ticks accordingly. Width (of graph) / numOfTicks
        }
        */

        // Loop through plotters and call plot on each of them to graph the given data
        for (int i = 0; i < plotters.length; i++) {
            plotters[i].plot(startDate, endDate, startX, startY, endY, widthPerDay);
        }
        // Draw tick marks and label x-axis end
    }

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