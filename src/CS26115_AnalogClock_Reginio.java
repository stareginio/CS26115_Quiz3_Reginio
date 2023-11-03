
import java.awt.*;
import java.awt.geom.*;

public class CS26115_AnalogClock_Reginio {

    private int timeMarkX, timeMarkY;
    private Graphics2D g2;
    
    public CS26115_AnalogClock_Reginio(Graphics2D g2) {
        this.g2 = g2;
    }
    
    public void paintBase(int panelWidth, int panelHeight,
            int digitWidth, int dist) {
        // == Variables ==========
        int outerRadius, innerRadius,
                circleCenterX, circleCenterY,
                hourMarkWidth, hourMarkHeight,
                minuteMarkWidth, minuteMarkHeight,
                timeMarkDist;
        Area analogClockBase, timeMarks;
        Ellipse2D.Double circle;
        Rectangle2D.Double hourMark, minuteMark;
        Shape transformedShape;
        AffineTransform at;
        
        // == Create the base shape ==========
        outerRadius = panelWidth * 3/4;
        circleCenterX = panelWidth/2 - outerRadius/2;
        circleCenterY = panelHeight/2 - outerRadius/2 + digitWidth + dist;
        
        // -- black circle ----------
        circle = new Ellipse2D.Double(
                circleCenterX, circleCenterY, outerRadius, outerRadius
        );
        analogClockBase = new Area(circle);
        
        // -- white circle ----------
        innerRadius = outerRadius - dist*4;
        circle = new Ellipse2D.Double(
                circleCenterX + dist*2,
                circleCenterY + dist*2,
                innerRadius, innerRadius
        );
        analogClockBase.subtract(new Area(circle));
        
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
                panelWidth/2 - hourMarkWidth/2,
                circleCenterY + timeMarkDist,
                hourMarkWidth, hourMarkHeight
        );
        timeMarks = new Area(hourMark);
        
        // -- minute mark ----------
        // 1st mark
        minuteMark = new Rectangle2D.Double(
                panelWidth/2 - hourMarkWidth/2,
                circleCenterY + timeMarkDist,
                minuteMarkWidth, 
                minuteMarkHeight
        );
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(6.1), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
        // 2nd mark
        at.rotate(Math.toRadians(6.1), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
        // 3rd mark
        at.rotate(Math.toRadians(6.1), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
        // 4th mark
        at.rotate(Math.toRadians(6.1), timeMarkX, timeMarkY);
        transformedShape = at.createTransformedShape(minuteMark);
        timeMarks.add(new Area(transformedShape));
        
        // -- 12 o' clock ----------
        analogClockBase.add(timeMarks);
        
        // -- 1 to 11 o' clock ----------
        at = new AffineTransform();
        for (int i=1; i < 12; i++) {
            at.rotate(Math.toRadians(30), timeMarkX+0.1, timeMarkY-0.25);
            transformedShape = at.createTransformedShape(timeMarks);
            timeMarks.add(new Area(transformedShape));
        }
        
        analogClockBase.add(timeMarks);
        
        // == Create the analog clock without the hands ==========
        g2.setColor(Color.black);
        g2.fill(analogClockBase);
    }
    
    public void paintHands(int panelWidth, int hr, int min, int sec) {
        // == Variables ==========
        int handWidth, handHeight, tailWidth, tailHeight;
        Area hourHand, minuteHand, secondHand;
        Rectangle2D.Double rect;
        Shape transformedShape;
        AffineTransform at;
        
        // == Hour hand ==========
        handWidth = 13;
        handHeight = panelWidth/4;
        
        rect = new Rectangle2D.Double(
                timeMarkX - handWidth/2,
                timeMarkY - handHeight * 3/4,
                handWidth, 
                handHeight
        );
        hourHand = new Area(rect);
        
        at = new AffineTransform();
        at.rotate(
                Math.toRadians(hr*30 + Math.ceil(min/2)), 
                timeMarkX, 
                timeMarkY
        );
        
        transformedShape = at.createTransformedShape(hourHand);        
        hourHand = new Area(transformedShape);
        
        g2.setColor(Color.black);
        g2.fill(hourHand);
        
        // == Minute hand ==========
        handWidth = 7;
        handHeight = panelWidth*4/11;
        
        rect = new Rectangle2D.Double(
                timeMarkX - handWidth/2,
                timeMarkY - handHeight * 9/11 - 1,
                handWidth, 
                handHeight
        );
        minuteHand = new Area(rect);
        
        at = new AffineTransform();
        at.rotate(
                Math.toRadians(min*6 + Math.ceil(sec/10)), 
                timeMarkX, 
                timeMarkY
        );
        
        transformedShape = at.createTransformedShape(minuteHand);        
        minuteHand = new Area(transformedShape);
        
        g2.setColor(Color.black);
        g2.fill(minuteHand);

        // == Second hand ==========
        handWidth = 1;
        handHeight = panelWidth*4/11;
        tailWidth = 5;
        tailHeight = handHeight/5;
        
        // -- hand ----------
        rect = new Rectangle2D.Double(
                timeMarkX - handWidth/2,
                timeMarkY - handHeight * 9/11 + 5,
                handWidth, 
                handHeight
        );
        
        secondHand = new Area(rect);
        
        // -- tail ----------
        rect = new Rectangle2D.Double(
                timeMarkX - tailWidth/2,
                timeMarkY + handHeight/13,
                tailWidth, 
                tailHeight
        );
        
        secondHand.add(new Area(rect));
        
        // -- rotation ----------
        at = new AffineTransform();
        at.rotate(
                Math.toRadians(sec*6), 
                timeMarkX+0.25,
                timeMarkY
        );
        
        transformedShape = at.createTransformedShape(secondHand);        
        secondHand = new Area(transformedShape);
        
        g2.setColor(Color.red);
        g2.fill(secondHand);
    }
    
    public void paintRedCircle(int panelWidth, int panelHeight,
            int digitWidth, int dist) {
        // == Variables ==========
        int middleRadius;
        Ellipse2D.Double circle;
        Area analogClockMiddle;
        
        // == Create the red circle ==========
        middleRadius = 6;
        circle = new Ellipse2D.Double(
                panelWidth/2 - middleRadius/2,
                panelHeight/2 - middleRadius/2 + digitWidth + dist,
                middleRadius, 
                middleRadius
        );
        analogClockMiddle = new Area(circle);
        
        g2.setColor(Color.red);
        g2.fill(analogClockMiddle);
    }
}