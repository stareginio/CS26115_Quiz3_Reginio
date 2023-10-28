
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CS26115_CountupClocks_Reginio extends JPanel {
    
    // Constructor
    CS26115_CountupClocks_Reginio() { }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        // == Variables ==========
        int[] triangleX;
        int[] triangleY;
        int rectX, rectY, rectWidth, rectHeight;
        Area segment, colon;
        
        // == Digital clock ========== 
        // -- Create shapes for the seven-segment display ----------
        rectX = 30;
        rectY = 30;
        rectWidth = 8;
        rectHeight = 30;
        
        // ~~ create the base shape ~~~~~~~~~~
        Rectangle2D.Double rect = new Rectangle2D.Double(
                rectX, rectY, rectWidth, rectHeight
        );
        segment = new Area(rect);
        
        // ~~ subtract the upper left corner ~~~~~~~~~~
        triangleX = new int[] { rectX, rectX, (rectX + rectWidth/2) };
        triangleY = new int[] { (rectX + rectWidth/2), rectY, rectY };
        Polygon triangle = new Polygon(triangleX, triangleY, 3);
        
        segment.subtract(new Area(triangle));
        
        // ~~ subtract the upper right corner ~~~~~~~~~~
        triangle = new Polygon(triangleX, triangleY, 3);
        
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(90), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(rectWidth/2, -rectWidth/2);
        
        Shape transformedShape = at.createTransformedShape(triangle);
        segment.subtract(new Area(transformedShape));
        
        // ~~ subtract the bottom right corner ~~~~~~~~~~
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(180), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(0, -rectHeight);

        transformedShape = at.createTransformedShape(triangle);
        segment.subtract(new Area(transformedShape));
        
        // ~~ subtract the bottom left corner ~~~~~~~~~~
        triangle = new Polygon(triangleX, triangleY, 3);
        
        at = new AffineTransform();
        at.rotate(Math.toRadians(270), (rectX + rectWidth/2), ((rectY+rectHeight)/2));
        at.translate(-(rectHeight - rectWidth/2), -rectWidth/2);

        transformedShape = at.createTransformedShape(triangle);
        segment.subtract(new Area(transformedShape));
        
        // -- Create the segment ----------        
        g2.setColor(Color.black);
        g2.fill(segment);
        
        // for debugging
//        g2.setColor(Color.red);
//        g2.fill(new Area(transformedShape));
        
        // -- Draw the digital clock ----------
        
        // == Analog clock ==========
        
        // reference for drawing circle at center:
        // https://stackoverflow.com/questions/19386951/how-to-draw-a-circle-with-given-x-and-y-coordinates-as-the-middle-spot-of-the-ci
    }
}