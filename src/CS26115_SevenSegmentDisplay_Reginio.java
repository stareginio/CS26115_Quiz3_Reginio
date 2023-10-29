
import java.awt.*;
import java.awt.geom.*;

public class CS26115_SevenSegmentDisplay_Reginio {
    
    Graphics2D g2;
    
    public CS26115_SevenSegmentDisplay_Reginio(Graphics2D g2) {
        this.g2 = g2;
    }
    
    public void drawDigit() {
        // == Variables ==========
        int[] triangleX;
        int[] triangleY;
        int rectX, rectY, rectWidth, rectHeight;
        Area colon, upperLeftSegment, upperRightSegment, lowerLeftSegment,
                lowerRightSegment, topSegment, middleSegment, lowerSegment;
        
        // == Create the upper left segment ==========
        // -- Create the base shape ----------
        rectX = 30;
        rectY = 30;
        rectWidth = 8;
        rectHeight = 30;
        
        Rectangle2D.Double rect = new Rectangle2D.Double(
                rectX, rectY, rectWidth, rectHeight
        );
        
        upperLeftSegment = new Area(rect);
        
        // -- Subtract the upper left corner ----------
        triangleX = new int[] { rectX, rectX, (rectX + rectWidth/2) };
        triangleY = new int[] { (rectX + rectWidth/2), rectY, rectY };
        Polygon triangle = new Polygon(triangleX, triangleY, 3);
        
        upperLeftSegment.subtract(new Area(triangle));
        
        // -- Subtract the upper right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(90), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(rectWidth/2, -rectWidth/2);
        
        Shape transformedShape = at.createTransformedShape(triangle);
        upperLeftSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(180), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(0, -rectHeight);

        transformedShape = at.createTransformedShape(triangle);
        upperLeftSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom left corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(270), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(-(rectHeight - rectWidth/2), -rectWidth/2);

        transformedShape = at.createTransformedShape(triangle);
        upperLeftSegment.subtract(new Area(transformedShape));
        
        // -- Create the segment ----------
        g2.setColor(Color.black);
        g2.fill(upperLeftSegment);
        
        // == Create the lower left segment ==========
        lowerLeftSegment = upperLeftSegment;
        
        at = new AffineTransform();
        at.translate(0, rectHeight + 2);
        lowerLeftSegment.transform(at);
        
        g2.fill(lowerLeftSegment);
        
        // for debugging
//        g2.setColor(Color.red);
//        g2.fill(new Area(transformedShape));
    }
}
