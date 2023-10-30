
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
        CS26115_DigitalClock_Reginio dc =
                new CS26115_DigitalClock_Reginio(g2);
        
        // -- Create the 1st pair of digits ----------
        dc.paintDigit(startingPoint, getSegmentColors(hr, "tens"));
        dc.paintDigit(startingPoint + digitWidth + dist,
                        getSegmentColors(hr, "ones"));
        
        // -- Create the 1st colon ----------
        dc.paintColon(startingPoint + digitWidth*2 + dist*2);
        
        // -- Create the 2nd pair of digits ----------
        dc.paintDigit(startingPoint + digitWidth*2 + colonLength + dist*3,
                        getSegmentColors(min, "tens"));
        dc.paintDigit(startingPoint + digitWidth*3 + colonLength + dist*4,
                        getSegmentColors(min, "ones"));
        
        // -- Create the 2nd colon ----------
        dc.paintColon(startingPoint + digitWidth*4 + colonLength + dist*5);
        
        // -- Create the 3rd pair of digits ----------
        dc.paintDigit(startingPoint + digitWidth*4 + colonLength*2 + dist*6,
                        getSegmentColors(sec, "tens"));
        dc.paintDigit(startingPoint + digitWidth*5 + colonLength*2 + dist*7,
                        getSegmentColors(sec, "ones"));
        
        // == Analog clock ==========
        CS26115_AnalogClock_Reginio ac = new CS26115_AnalogClock_Reginio(g2);
        
        ac.paintBase(getWidth(), getHeight(), digitWidth, dist);
        ac.paintHands(getWidth());
        ac.paintRedCircle(getWidth(), getHeight(), digitWidth, dist);
    }
    
    public Color[] getSegmentColors(int time, String digit) {
        Color[] colors = {};
        
        // == Get the given digit from the integer ==========
        if (digit.equals("tens")) {
            digit = Integer.toString(time % 100 / 10);
        } else if (digit.equals("ones")) {
            digit = Integer.toString(time % 10);
        }
        
//        System.out.println("getSegmentColors() ---- digit: " + digit);
        
        // == Get the colors for the digit ==========
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