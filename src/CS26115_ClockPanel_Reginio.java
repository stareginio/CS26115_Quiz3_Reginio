
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CS26115_ClockPanel_Reginio extends JPanel {
    
    private int hr, min, sec;
    
    // Constructor
    CS26115_ClockPanel_Reginio(int hr, int min, int sec) {
        this.hr = hr;
        this.min = min;
        this.sec = sec;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        // == Variables ==========
        int startingPoint, digitWidth, colonLength, dist;
        
        // == Digital clock ==========
        startingPoint = 25;
        digitWidth = 42;
        colonLength = 8;
        dist = 6;           // distance between digits and colons
        CS26115_SevenSegmentDisplay_Reginio ssd =
                new CS26115_SevenSegmentDisplay_Reginio(g2);
        
        // -- Draw the 1st pair of digits ----------
        ssd.drawDigit(startingPoint, getSegmentColors(hr, "tens"));
        ssd.drawDigit(startingPoint + digitWidth + dist,
                        getSegmentColors(hr, "ones"));
        
        // -- Draw the 1st colon ----------
        ssd.drawColon(startingPoint + digitWidth*2 + dist*2);
        
        // -- Draw the 2nd pair of digits ----------
        ssd.drawDigit(startingPoint + digitWidth*2 + colonLength + dist*3,
                        getSegmentColors(min, "tens"));
        ssd.drawDigit(startingPoint + digitWidth*3 + colonLength + dist*4,
                        getSegmentColors(min, "ones"));
        
        // -- Draw the 2nd colon ----------
        ssd.drawColon(startingPoint + digitWidth*4 + colonLength + dist*5);
        
        // -- Draw the 3rd pair of digits ----------
        ssd.drawDigit(startingPoint + digitWidth*4 + colonLength*2 + dist*6,
                        getSegmentColors(sec, "tens"));
        ssd.drawDigit(startingPoint + digitWidth*5 + colonLength*2 + dist*7,
                        getSegmentColors(sec, "ones"));
        
        // == Analog clock ==========
        
        // reference for drawing circle at center:
        // https://stackoverflow.com/questions/19386951/how-to-draw-a-circle-with-given-x-and-y-coordinates-as-the-middle-spot-of-the-ci
    }
    
    public Color[] getSegmentColors(int time, String digit) {
        Color[] colors = {};
        
        // get the given digit from the integer
        if (digit.equals("tens")) {
            digit = Integer.toString(time % 100 / 10);
        } else if (digit.equals("ones")) {
            digit = Integer.toString(time % 10);
        }
        
        System.out.println("digit: " + digit);
        
        // get color based on the digit
        switch (digit) {
            case "0" -> colors = new Color[] {
                    Color.black, Color.black,
                    Color.black, Color.black,
                    Color.black, Color.lightGray, Color.black
                };
            case "1" -> colors = new Color[] {
                    Color.lightGray, Color.lightGray,
                    Color.black, Color.black,
                    Color.lightGray, Color.lightGray, Color.lightGray
                };
            case "2" -> colors = new Color[] {
                    Color.lightGray, Color.black,
                    Color.black, Color.lightGray,
                    Color.black, Color.black, Color.black
                };
            case "3" -> colors = new Color[] {
                    Color.lightGray, Color.lightGray,
                    Color.black, Color.black,
                    Color.black, Color.black, Color.black
                };
            case "4" -> colors = new Color[] {
                    Color.black, Color.lightGray,
                    Color.black, Color.black,
                    Color.lightGray, Color.black, Color.lightGray
                };
            case "5" -> colors = new Color[] {
                    Color.black, Color.lightGray,
                    Color.lightGray, Color.black,
                    Color.black, Color.black, Color.black
                };
            case "6" -> colors = new Color[] {
                    Color.black, Color.black,
                    Color.lightGray, Color.black,
                    Color.black, Color.black, Color.black
                };
            case "7" -> colors = new Color[] {
                    Color.lightGray, Color.lightGray,
                    Color.black, Color.black,
                    Color.black, Color.lightGray, Color.lightGray
                };
            case "8" -> colors = new Color[] {
                    Color.black, Color.black,
                    Color.black, Color.black,
                    Color.black, Color.black, Color.black
                };
            case "9" -> colors = new Color[] {
                    Color.black, Color.lightGray,
                    Color.black, Color.black,
                    Color.black, Color.black, Color.black
                };
            default -> {
            }
        }
    
        return colors;
    }
}