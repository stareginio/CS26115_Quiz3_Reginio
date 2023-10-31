
import java.awt.*;
import java.awt.geom.*;

public class CS26115_DigitalClock_Reginio {
    
    private Graphics2D g2;
    
    public CS26115_DigitalClock_Reginio(Graphics2D g2) {
        this.g2 = g2;
    }
    
    public void paintDigit(int x, int time, String digit) {
        // == Variables ====================
        int[] triangleX;
        int[] triangleY;
        int y, rectWidth, rectHeight, segmentDist;
        Area verticalSegment, horizontalSegment;
        Shape transformedShape;
        Polygon triangle;
        Rectangle2D.Double rect;
        AffineTransform at;
        
        Color colors[] = getSegmentColors(time, digit);
        
        // == Create the upper left segment ====================
        y = 25;
        rectWidth = 8;
        rectHeight = y;
        segmentDist = 2;
        
        // -- Create the base shape ----------        
        rect = new Rectangle2D.Double(
                x, y, rectWidth, rectHeight
        );
        
        verticalSegment = new Area(rect);
        
        // -- Subtract the upper left corner ----------
        triangleX = new int[] { x, x, (x + rectWidth/2) };
        triangleY = new int[] { (y + rectWidth/2), y, y };
        triangle = new Polygon(triangleX, triangleY, 3);
        
        verticalSegment.subtract(new Area(triangle));
        
        // -- Subtract the upper right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(
                Math.toRadians(90),
                (x + rectWidth/2),
                ((y + rectHeight)/2)
        );
        at.translate(rectWidth/2, -rectWidth/2);
        
        transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom right corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(
                Math.toRadians(180),
                (x + rectWidth/2),
                ((y + rectHeight)/2)
        );
        at.translate(0, -rectHeight);

        transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Subtract the bottom left corner ----------
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(
                Math.toRadians(270),
                (x + rectWidth/2),
                ((y + rectHeight)/2)
        );
        at.translate(-(rectHeight - rectWidth/2), -rectWidth/2);

        transformedShape = at.createTransformedShape(triangle);
        verticalSegment.subtract(new Area(transformedShape));
        
        // -- Create the segment ----------
        g2.setColor(colors[0]);
        g2.fill(verticalSegment);
        
        // == Create the lower left segment ====================================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist);
        transformedShape = at.createTransformedShape(verticalSegment);
        
        g2.setColor(colors[1]);
        g2.fill(transformedShape);
        
        // == Create the upper right segment ===================================
        at = new AffineTransform();
        at.translate(rectHeight + segmentDist*2, 0);
        transformedShape = at.createTransformedShape(verticalSegment);
        
        g2.setColor(colors[2]);
        g2.fill(transformedShape);
        
        // == Create the lower right segment ===================================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist);
        transformedShape = at.createTransformedShape(transformedShape);
        
        g2.setColor(colors[3]);
        g2.fill(transformedShape);
        
        // == Create the top segment ====================
        horizontalSegment = verticalSegment;
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(270), (x + rectWidth/2) , y);
        at.translate(segmentDist, segmentDist);
        transformedShape = at.createTransformedShape(horizontalSegment);
        
        g2.setColor(colors[4]);
        g2.fill(transformedShape);

        // == Create the middle segment ====================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist + segmentDist/2);
        transformedShape = at.createTransformedShape(transformedShape);
        
        g2.setColor(colors[5]);
        g2.fill(transformedShape);
        
        // == Create the bottom segment ====================
        at = new AffineTransform();
        at.translate(0, rectHeight + segmentDist);
        transformedShape = at.createTransformedShape(transformedShape);
        
        g2.setColor(colors[6]);
        g2.fill(transformedShape);
    }
    
    public void paintColon(int x) {
        // == Variables ====================
        int y, length;
        Rectangle2D.Double upperDot;
        Shape lowerDot;
        AffineTransform at;
        
        // == Create the upper dot ====================
        y = 35;         // should be greater than rectHeight
        length = 8;     // should be same with rectWidth
        upperDot = new Rectangle2D.Double(x, y, length, length);
        
        g2.setColor(Color.black);
        g2.fill(upperDot);
        
        // == Create the lower dot ====================
        at = new AffineTransform();
        at.translate(0, 25);
        lowerDot = at.createTransformedShape(upperDot);
        
        g2.setColor(Color.black);
        g2.fill(lowerDot);
    }
    
    public Color[] getSegmentColors(int time, String digit) {
        Color[] colors = {};
        
        // == Get the given digit from the integer ==========
        if (digit.equals("tens")) {
            digit = Integer.toString(time % 100 / 10);
        } else if (digit.equals("ones")) {
            digit = Integer.toString(time % 10);
        }
        
//        System.out.println("-- getSegmentColors() ----\ndigit: " + digit);
        
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
