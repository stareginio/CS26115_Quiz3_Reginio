
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CS26115_IntlInterface_Reginio extends JFrame implements ActionListener {
    
    private final JPanel mainPnl;
    private final javax.swing.Timer timer;
    private final String[] clockNames;
    private JPanel[] clockPanels;
    
    public CS26115_IntlInterface_Reginio() {
        setTitle("CS26115_International_Reginio");
                
        mainPnl = new JPanel();
        JPanel namePnl = new JPanel();
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // == Main Panel ==============================
        mainPnl.setLayout(new GridBagLayout());
        
        // for debugging
//        mainPnl.setBackground(Color.pink);
//        namePnl.setBackground(Color.green);
        
        // == Name Panel ==============================
        namePnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        namePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePnl.setBorder(new EmptyBorder(20,0,0,0));
        
        // -- create name label ----------
        JLabel nameLbl = new JLabel("INTERNATIONAL");
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
        
        // -- add components to name panel ----------
        namePnl.add(nameLbl);
        
        // -- add name panel to main panel ----------
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        
        mainPnl.add(namePnl, gc);
        
        // == Clock Panels ==============================  
        // set timezone names
        clockNames = new String[] {"MANILA", "TOKYO", "NEW YORK", "LONDON"};
        
        // create timer to update the clock panel repeatedly
        timer = new javax.swing.Timer(1000, this);
        timer.setRepeats(true);
        timer.setInitialDelay(1);
        
        // start the timer
        timer.start();
        
        // == Frame ==============================
        add(mainPnl);
        
        setSize(800, 820);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void getClockPanels() {
        // == Variables ==============================
        JLabel clockLbl;
        CS26115_Time_Reginio t = new CS26115_Time_Reginio(clockNames);
        
        // == Create the clock panels ==============================
        if (clockPanels != null) {
            mainPnl.remove(clockPanels[0]);
            mainPnl.remove(clockPanels[1]);
            mainPnl.remove(clockPanels[2]);
            mainPnl.remove(clockPanels[3]);
        }
        
        clockPanels = new JPanel[4];
        
        for (int i=0; i < clockNames.length; i++) {
            // -- Analog Clock ----------            
            clockPanels[i] = new CS26115_IntlClockPanel_Reginio(
                    clockNames[i],
                    t.getTimeZonebyName(clockNames[i])
            );
            
            clockPanels[i].setPreferredSize(new Dimension(
                    mainPnl.getWidth()*11/24, mainPnl.getHeight()*5/12
            ));

            GridBagConstraints gc = new GridBagConstraints();
            gc.fill = GridBagConstraints.HORIZONTAL;
            
            if (i < 2) {
                gc.gridx = i;
                gc.gridy = 1;
            } else {
                gc.gridx = i-2;
                gc.gridy = 3;
            }

            mainPnl.add(clockPanels[i], gc);
            
            // -- Label ----------
            clockLbl = new JLabel(clockNames[i]);
            clockLbl.setHorizontalAlignment(JLabel.CENTER);
            clockLbl.setBorder(new EmptyBorder(0,120,20,120));
            
            // for debugging
//            clockPanels[i].setBackground(Color.lightGray);
//            clockLbl.setBackground(Color.gray);
//            clockLbl.setOpaque(true);
            
            gc.gridy++;
            mainPnl.add(clockLbl, gc);
        }
        
        mainPnl.revalidate();
        mainPnl.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        getClockPanels();
    }
}