
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
        
        // == Digital clock ========== 
        // -- Draw the digits ----------
        CS26115_SevenSegmentDisplay_Reginio ssd =
                new CS26115_SevenSegmentDisplay_Reginio(g2);
        
        ssd.drawDigit();
        
        // NTS: call methods
        
        // -- Draw the digital clock ----------
        
        // == Analog clock ==========
        
        // reference for drawing circle at center:
        // https://stackoverflow.com/questions/19386951/how-to-draw-a-circle-with-given-x-and-y-coordinates-as-the-middle-spot-of-the-ci
    }
}