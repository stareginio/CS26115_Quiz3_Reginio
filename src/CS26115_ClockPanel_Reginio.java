
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CS26115_ClockPanel_Reginio extends JPanel {
    
    int hr, min, sec;
    
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
                new CS26115_SevenSegmentDisplay_Reginio(
                        hr, min, sec, g2
                );
        
        // -- Draw the 1st pair of digits ----------
        ssd.drawDigit(startingPoint);
        ssd.drawDigit(startingPoint + digitWidth + dist);
        
        // -- Draw the 1st colon ----------
        ssd.drawColon(startingPoint + digitWidth*2 + dist*2);
        
        // -- Draw the 2nd pair of digits ----------
        ssd.drawDigit(startingPoint + digitWidth*2 + colonLength + dist*3);
        ssd.drawDigit(startingPoint + digitWidth*3 + colonLength + dist*4);
        
        // -- Draw the 2nd colon ----------
        ssd.drawColon(startingPoint + digitWidth*4 + colonLength + dist*5);
        
        // -- Draw the 3rd pair of digits ----------
        ssd.drawDigit(startingPoint + digitWidth*4 + colonLength*2 + dist*6);
        ssd.drawDigit(startingPoint + digitWidth*5 + colonLength*2 + dist*7);
        
        // == Analog clock ==========
        
        // reference for drawing circle at center:
        // https://stackoverflow.com/questions/19386951/how-to-draw-a-circle-with-given-x-and-y-coordinates-as-the-middle-spot-of-the-ci
    }
}