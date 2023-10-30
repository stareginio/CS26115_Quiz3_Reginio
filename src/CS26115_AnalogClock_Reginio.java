
import java.awt.*;
import java.awt.geom.*;

public class CS26115_AnalogClock_Reginio {

    private Graphics2D g2;
    
    public CS26115_AnalogClock_Reginio(Graphics2D g2) {
        this.g2 = g2;
    }
    
    public void paintBase(int panelWidth, int panelHeight,
            int digitWidth, int dist) {
        // == Variables ==========
        int outerRadius, innerRadius, middleRadius,
                circleCenterX, circleCenterY,
                hourMarkWidth, hourMarkHeight, hourMarkDist;
        Area analogClockBase, analogClockMiddle, hourMark;
        
        // == Create the base shape ==========
        outerRadius = panelWidth - panelWidth/4;
        circleCenterX = panelWidth/2 - outerRadius/2;
        circleCenterY = panelHeight/2 - outerRadius/2 + digitWidth + dist;
        
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
                panelWidth/2 - middleRadius/2,
                panelHeight/2 - middleRadius/2 + digitWidth + dist,
                middleRadius, middleRadius
        );
        analogClockMiddle = new Area(circle);
        
        // == Create the hour marks ==========
        hourMarkWidth = 7;
        hourMarkHeight = 16;
        hourMarkDist = 20;
        
        Rectangle2D.Double rect = new Rectangle2D.Double(
                panelWidth/2 - hourMarkWidth/2, circleCenterY + hourMarkDist,
                hourMarkWidth, hourMarkHeight
        );
        hourMark = new Area(rect);
        
        analogClockBase.add(hourMark);
        
        // == Create the analog clock without the hands ==========
        g2.setColor(Color.black);
        g2.fill(analogClockBase);
        
        g2.setColor(Color.red);
        g2.fill(analogClockMiddle);
    }
    
    public void paintHands() {
        
    }
}
