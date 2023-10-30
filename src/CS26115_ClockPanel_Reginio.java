
import java.awt.*;
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
        digitWidth = 42;
        colonLength = 8;
        dist = 6;           // distance between digits and colons
        startingPoint = (getWidth() - (digitWidth*6 + colonLength*2 + dist*7)) / 2;
        CS26115_DigitalClock_Reginio dc =
                new CS26115_DigitalClock_Reginio(g2);
        
        // -- Create the 1st pair of digits ----------
        dc.paintDigit(startingPoint, hr, "tens");
        dc.paintDigit(startingPoint + digitWidth + dist, hr, "ones");
        
        // -- Create the 1st colon ----------
        dc.paintColon(startingPoint + digitWidth*2 + dist*2);
        
        // -- Create the 2nd pair of digits ----------
        dc.paintDigit(startingPoint + digitWidth*2 + colonLength + dist*3,
                        min, "tens");
        dc.paintDigit(startingPoint + digitWidth*3 + colonLength + dist*4,
                        min, "ones");
        
        // -- Create the 2nd colon ----------
        dc.paintColon(startingPoint + digitWidth*4 + colonLength + dist*5);
        
        // -- Create the 3rd pair of digits ----------
        dc.paintDigit(startingPoint + digitWidth*4 + colonLength*2 + dist*6,
                        sec, "tens");
        dc.paintDigit(startingPoint + digitWidth*5 + colonLength*2 + dist*7,
                        sec, "ones");
        
        // == Analog clock ==========
        CS26115_AnalogClock_Reginio ac = new CS26115_AnalogClock_Reginio(g2);
        
        ac.paintBase(getWidth(), getHeight(), digitWidth, dist);
        ac.paintHands(getWidth(), hr, min, sec);
        ac.paintRedCircle(getWidth(), getHeight(), digitWidth, dist);
    }
}