
import java.awt.*;
import java.awt.geom.*;

public class CS26115_SevenSegmentDisplay_Reginio {
    
    Graphics2D g2;
    
    public CS26115_SevenSegmentDisplay_Reginio(Graphics2D g2) {
        this.g2 = g2;
    }
    
    public void drawDigit(int x) {
        // == Variables ====================
        int[] triangleX;
        int[] triangleY;
        Color[] segmentColor;
        int rectWidth, rectHeight, segmentDist;
        Area verticalSegment, horizontalSegment;
        
        // == Create the upper left segment ====================
//        segmentColor = getSegmentColors();
        int y = 30;
        rectWidth = 8;
        rectHeight = 30;
        segmentDist = 2;
        
        // -- Create the base shape ----------        
        Rectangle2D.Double rect = new Rectangle2D.Double(
                x, y, rectWidth, rectHeight
        );
        
        verticalSegment = new Area(rect);
        
        // -- Subtract the upper left corner ----------
        triangleX = new int[] { x, x, (x + rectWidth/2) };
        triangleY = new int[] { (y + rectWidth/2), y, y };
        Polygon triangle = new Polygon(triangleX, triangleY, 3);
        
        verticalSegment.subtract(new Area(triangle));
        
        // -- Subtract the upper right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(90), (x + rectWidth/2), ((y+rectHeight)/2));
        at.translate(rectWidth/2, -rectWidth/2);
        
        Shape transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(180), (x + rectWidth/2), ((y+rectHeight)/2));
        at.translate(0, -rectHeight);

        transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom left corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(270), (x + rectWidth/2), ((y+rectHeight)/2));
        at.translate(-(rectHeight - rectWidth/2), -rectWidth/2);

        transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Create the segment ----------
        g2.setColor(Color.black);       // NTS: change depending on current time
        g2.fill(verticalSegment);
        
        // == Create the lower left segment ====================================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist);
        transformedShape = at.createTransformedShape(verticalSegment);
        
        g2.setColor(Color.black);       // NTS: change depending on current time
        g2.fill(transformedShape);
        
        // == Create the upper right segment ===================================
        at = new AffineTransform();
        at.translate(rectHeight + segmentDist*2, 0);
        transformedShape = at.createTransformedShape(verticalSegment);
        
        g2.setColor(Color.black);       // NTS: change depending on current time
        g2.fill(transformedShape);
        
        // == Create the upper right segment ===================================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist);
        transformedShape = at.createTransformedShape(transformedShape);
        
        g2.setColor(Color.black);       // NTS: change depending on current time
        g2.fill(transformedShape);
        
        // == Create the top segment ====================
        horizontalSegment = verticalSegment;
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(270), (x + rectWidth/2) , y);
        at.translate(segmentDist, segmentDist);
        transformedShape = at.createTransformedShape(horizontalSegment);
        
        g2.setColor(Color.black);       // NTS: change depending on current time
        g2.fill(transformedShape);

        // == Create the middle segment ====================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist + segmentDist/2);
        transformedShape = at.createTransformedShape(transformedShape);
        
        g2.setColor(Color.black);       // NTS: change depending on current time
        g2.fill(transformedShape);
        
        // == Create the bottom segment ====================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist);
        transformedShape = at.createTransformedShape(transformedShape);
        
        g2.setColor(Color.black);       // NTS: change depending on current time
        g2.fill(transformedShape);
    }
    
    public void drawColon(int x) {
        // == Variables ====================
        int y, length, dist;
        Rectangle2D.Double upperDot;
        
        // == Create the upper dot ====================
        y = 45;         // should be greater than rectHeight
        length = 8;     // should be same with rectWidth
        upperDot = new Rectangle2D.Double(x, y, length, length);
        
        g2.setColor(Color.black);
        g2.fill(upperDot);
        
        // == Create the lower dot ====================
        AffineTransform at = new AffineTransform();
        at.translate(0, 25);
        Shape lowerDot = at.createTransformedShape(upperDot);
        
        g2.setColor(Color.black);
        g2.fill(lowerDot);
    }
}
