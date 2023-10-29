
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
        int startingPoint, digitWidth, colonLength, dist,
                outerRadius, innerRadius, middleRadius,
                circleCenterX, circleCenterY,
                hourMarkWidth, hourMarkHeight, hourMarkDist;
        Area analogClockBase, analogClockMiddle, hourMark;
        
        // == Digital clock ==========
        startingPoint = 25;
        digitWidth = 42;
        colonLength = 8;
        dist = 6;           // distance between digits and colons
        CS26115_DigitalClock_Reginio dc =
                new CS26115_DigitalClock_Reginio(g2);
        
        // -- Draw the 1st pair of digits ----------
        dc.drawDigit(startingPoint, getSegmentColors(hr, "tens"));
        dc.drawDigit(startingPoint + digitWidth + dist,
                        getSegmentColors(hr, "ones"));
        
        // -- Draw the 1st colon ----------
        dc.drawColon(startingPoint + digitWidth*2 + dist*2);
        
        // -- Draw the 2nd pair of digits ----------
        dc.drawDigit(startingPoint + digitWidth*2 + colonLength + dist*3,
                        getSegmentColors(min, "tens"));
        dc.drawDigit(startingPoint + digitWidth*3 + colonLength + dist*4,
                        getSegmentColors(min, "ones"));
        
        // -- Draw the 2nd colon ----------
        dc.drawColon(startingPoint + digitWidth*4 + colonLength + dist*5);
        
        // -- Draw the 3rd pair of digits ----------
        dc.drawDigit(startingPoint + digitWidth*4 + colonLength*2 + dist*6,
                        getSegmentColors(sec, "tens"));
        dc.drawDigit(startingPoint + digitWidth*5 + colonLength*2 + dist*7,
                        getSegmentColors(sec, "ones"));
        
        // == Analog clock ==========
        outerRadius = getWidth() - getWidth()/4;
        circleCenterX = getWidth()/2 - outerRadius/2;
        circleCenterY = getHeight()/2 - outerRadius/2 + digitWidth + dist;
        
        // -- Create the base shape ----------
        // black circle
        Ellipse2D.Double circle = new Ellipse2D.Double(
                circleCenterX, circleCenterY, outerRadius, outerRadius
        );
        analogClockBase = new Area(circle);
        
        // white circle
        innerRadius = outerRadius - dist*4;
        circle = new Ellipse2D.Double(
                circleCenterX + dist*2, circleCenterY + dist*2,
                innerRadius, innerRadius
        );
        analogClockBase.subtract(new Area(circle));
        
        // small red circle
        middleRadius = 10;
        circle = new Ellipse2D.Double(
                getWidth()/2 - middleRadius/2,
                getHeight()/2 - middleRadius/2 + digitWidth + dist,
                middleRadius, middleRadius
        );
        analogClockMiddle = new Area(circle);
        
        // -- Create the hour marks ----------
        hourMarkWidth = 7;
        hourMarkHeight = 16;
        hourMarkDist = 20;
        
        Rectangle2D.Double rect = new Rectangle2D.Double(
                getWidth()/2 - hourMarkWidth/2, circleCenterY + hourMarkDist,
                hourMarkWidth, hourMarkHeight
        );
        hourMark = new Area(rect);
        
        analogClockBase.add(hourMark);
        
        // -- Create the hands ----------
        // NTS: call method here
        
        // -- Create the analog clock ----------
        g2.setColor(Color.black);
        g2.fill(analogClockBase);
        
        g2.setColor(Color.red);
        g2.fill(analogClockMiddle);
        
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
        
//        System.out.println("getSegmentColors() ---- digit: " + digit);
        
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