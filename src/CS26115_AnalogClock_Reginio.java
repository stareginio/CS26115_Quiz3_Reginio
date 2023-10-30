
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
                circleCenterX, circleCenterY, timeMarkX, timeMarkY,
                hourMarkWidth, hourMarkHeight, minuteMarkWidth, minuteMarkHeight,
                timeMarkDist;
        Area analogClockBase, analogClockMiddle, timeMarks;
        Rectangle2D.Double hourMark, minuteMark;
        Shape transformedShape;
        AffineTransform at;
        
        // == Create the base shape ==========
        outerRadius = panelWidth - panelWidth/4;
        circleCenterX = panelWidth/2 - outerRadius/2;
        circleCenterY = panelHeight/2 - outerRadius/2 + digitWidth + dist;
        
        // -- Black circle ----------
        Ellipse2D.Double circle = new Ellipse2D.Double(
                circleCenterX, circleCenterY, outerRadius, outerRadius
        );
        analogClockBase = new Area(circle);
        
        // -- White circle ----------
        innerRadius = outerRadius - dist*4;
        circle = new Ellipse2D.Double(
                circleCenterX + dist*2, circleCenterY + dist*2,
                innerRadius, innerRadius
        );
        analogClockBase.subtract(new Area(circle));
        
        // -- Small red circle ----------
        middleRadius = 10;
        circle = new Ellipse2D.Double(
                panelWidth/2 - middleRadius/2,
                panelHeight/2 - middleRadius/2 + digitWidth + dist,
                middleRadius, middleRadius
        );
        analogClockMiddle = new Area(circle);
        
        // == Create the time marks ==========
        hourMarkWidth = 5;
        hourMarkHeight = 16;
        
        minuteMarkWidth = 3;
        minuteMarkHeight = 10;
        
        timeMarkDist = 20;
        timeMarkX = panelWidth/2;
        timeMarkY = panelHeight/2 + digitWidth + dist;
        
        // -- Hour mark ----------
        hourMark = new Rectangle2D.Double(
                panelWidth/2 - hourMarkWidth/2, circleCenterY + timeMarkDist,
                hourMarkWidth, hourMarkHeight
        );
        timeMarks = new Area(hourMark);
        
        // -- Minute mark ----------
        // 1st mark
        minuteMark = new Rectangle2D.Double(
                panelWidth/2 - hourMarkWidth/2, circleCenterY + timeMarkDist,
                minuteMarkWidth, minuteMarkHeight
        );
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(6), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
        // 2nd mark
        at.rotate(Math.toRadians(6), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
        // 3rd mark
        at.rotate(Math.toRadians(6), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
        // 4th mark
        at.rotate(Math.toRadians(6), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
//        // -- 12 o' clock ----------
        analogClockBase.add(timeMarks);
        
        // -- 1 to 11 o' clock ----------
        at = new AffineTransform();
        for (int i=1; i < 12; i++) {
            at.rotate(Math.toRadians(30), timeMarkX, timeMarkY);
            transformedShape = at.createTransformedShape(timeMarks);
            timeMarks.add(new Area(transformedShape));
        }
        
        analogClockBase.add(timeMarks);

        // for debugging
//        g2.setColor(Color.red);
//        g2.fill(timeMarks);
        
        // == Create the analog clock without the hands ==========
        g2.setColor(Color.black);
        g2.fill(analogClockBase);
        
        g2.setColor(Color.red);
        g2.fill(analogClockMiddle);
    }
    
    public void paintHands() {
        
    }
}
