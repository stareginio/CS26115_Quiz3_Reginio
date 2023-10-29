
import java.awt.*;
import java.awt.geom.*;

public class CS26115_SevenSegmentDisplay_Reginio {
    
    Graphics2D g2;
//    AffineTransform oldTransform;
    
    public CS26115_SevenSegmentDisplay_Reginio(Graphics2D g2) {
        this.g2 = g2;
//        oldTransform = g2.getTransform();
    }
    
    public void drawDigit() {
        // == Variables ====================
        int[] triangleX;
        int[] triangleY;
        int rectX, rectY, rectWidth, rectHeight, segmentDist;
        Area colon, verticalSegment, horizontalSegment;
        
        // == Create the upper left segment ====================
        // -- Create the base shape ----------
        rectX = 30;     // NTS: modify x value for the other digits
        rectY = 30;
        rectWidth = 8;
        rectHeight = 30;
        segmentDist = 2;
        
        Rectangle2D.Double rect = new Rectangle2D.Double(
                rectX, rectY, rectWidth, rectHeight
        );
        
        verticalSegment = new Area(rect);
        
        // -- Subtract the upper left corner ----------
        triangleX = new int[] { rectX, rectX, (rectX + rectWidth/2) };
        triangleY = new int[] { (rectX + rectWidth/2), rectY, rectY };
        Polygon triangle = new Polygon(triangleX, triangleY, 3);
        
        verticalSegment.subtract(new Area(triangle));
        
        // -- Subtract the upper right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(90), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(rectWidth/2, -rectWidth/2);
        
        Shape transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(180), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(0, -rectHeight);

        transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom left corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(270), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
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
        at.rotate(Math.toRadians(270), (rectX + rectWidth/2) , rectY);
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
        
        // for debugging
//        g2.setColor(Color.red);
//        g2.fill(new Area(transformedShape));
    }
}
