
import java.awt.*;
import javax.swing.*;

public class CS26115_Quiz3_IntlClockPanel_Reginio extends JPanel {
    
    private final int[] time;
    
    // Constructor
    public CS26115_Quiz3_IntlClockPanel_Reginio(String name, int[] time) {
        this.time = time;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        
        // == Analog clock ==========
        CS26115_Quiz3_AnalogClock_Reginio ac = new CS26115_Quiz3_AnalogClock_Reginio(g2);
        
        ac.paintBase(getWidth(), getHeight(), 0, 0);
        ac.paintHands(getWidth(), time[0], time[1], time[2]);
        ac.paintRedCircle(getWidth(), getHeight(), 0, 0);
        
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
