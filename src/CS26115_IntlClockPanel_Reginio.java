
import java.awt.*;
import javax.swing.*;

public class CS26115_IntlClockPanel_Reginio extends JPanel {
    
    private final String clockName;
    
    // Constructor
    public CS26115_IntlClockPanel_Reginio(String clockName) {
        this.clockName = clockName;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        
        // == Variables ==========
        int startingPoint, digitWidth, colonLength, dist;
        
        // == Time ==========
        
        
        // == Analog clock ==========
        CS26115_AnalogClock_Reginio ac = new CS26115_AnalogClock_Reginio(g2);
        
        ac.paintBase(getWidth(), getHeight(), 0, 0);
//        ac.paintHands(getWidth(), hr, min, sec);
        ac.paintRedCircle(getWidth(), getHeight(), 0, 0);
        
    }
}
