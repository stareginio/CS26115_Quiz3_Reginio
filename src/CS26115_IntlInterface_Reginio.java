
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.border.*;

public class CS26115_IntlInterface_Reginio extends JFrame implements ActionListener {
    
    private final JPanel mainPnl;
    private JPanel clockPnl;
    private javax.swing.Timer timer;
    
    public CS26115_IntlInterface_Reginio() {
        setTitle("CS26115_Countup_Reginio");
                
        mainPnl = new JPanel();
        JPanel namePnl = new JPanel();
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // == Main Panel ==============================
        mainPnl.setLayout(new GridBagLayout());
        
        // for debugging
//        mainPnl.setBackground(Color.pink);
//        namePnl.setBackground(Color.green);
//        spinnerPnl.setBackground(Color.lightGray);
//        buttonPnl.setBackground(Color.blue);
        
        // == Name Panel ==============================
        namePnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        namePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePnl.setBorder(new EmptyBorder(0,30,10,30));
        
        // -- create name label ----------
        JLabel nameLbl = new JLabel("INTERNATIONAL");
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
        
        // -- add components to name panel ----------
        namePnl.add(nameLbl);
        
        // -- add name panel to main panel ----------
        gc.gridx = 0;
        gc.gridy = 0;
        
        mainPnl.add(namePnl, gc);
        
        // == Clock Panel ==============================       
        // create timer to update the clock panel repeatedly
        timer = new javax.swing.Timer(1000, this);
        timer.setRepeats(true);
        timer.setInitialDelay(1);
        timer.start();
        
        getClockPanel();
        
        // == Frame ==============================
        add(mainPnl);
        
        setSize(800, 780);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void getClockPanel() {
        // == Variables ==============================
        String[] clockNames = {"MANILA", "TOKYO", "NEW YORK", "LONDON"};
        JLabel clockLbl;
        
        // == Remove previous clock panel if exists ============================
        if (clockPnl != null) {
            mainPnl.remove(clockPnl);
        }
        
        // == Create the clock panels ==============================
        for (int i=0; clockNames.length < i; i++) {
            clockPnl = new CS26115_IntlClock_Reginio(clockNames[i]);
            
            clockPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
            clockPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
            clockPnl.setPreferredSize(new Dimension(350,350));

            clockLbl = new JLabel(clockNames[i]);
            clockLbl.setHorizontalAlignment(JLabel.CENTER);

            clockPnl.add(clockLbl);

            GridBagConstraints gc = new GridBagConstraints();
            gc.fill = GridBagConstraints.HORIZONTAL;
            
            if (i < 2) {
                gc.gridx = i;
                gc.gridy = 1;
            } else {
                gc.gridx = i-2;
                gc.gridy = 2;
            }

            mainPnl.add(clockPnl, gc);
        }
        
        mainPnl.revalidate();
        mainPnl.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {       
        getClockPanel();
    }
}