
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CS26115_Quiz3_Reginio extends JFrame implements ActionListener {
    
    private JButton clockBtn, countdownBtn, countupBtn, intlBtn;
    
    public CS26115_Quiz3_Reginio() {
        setTitle("CS26115_Quiz3_Reginio");
        
        JPanel mainPnl = new JPanel();
        JPanel namePnl = new JPanel();
        JPanel buttonPnl = new JPanel();
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // == Main Panel ==============================
        mainPnl.setLayout(new GridBagLayout());
        
        // for debugging
//        mainPnl.setBackground(Color.pink);
//        namePnl.setBackground(Color.green);
//        buttonPnl.setBackground(Color.blue);
        
        // == Name Panel ==============================
        namePnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        namePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePnl.setBorder(new EmptyBorder(0,30,10,30));
        
        // -- create name label ----------
        JLabel nameLbl = new JLabel("Digital Clock and Analog Clock");
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
        
        // -- add components to name panel ----------
        namePnl.add(nameLbl);
        
        // -- add name panel to main panel ----------
        gc.gridx = 0;
        gc.gridy = 0;
        mainPnl.add(namePnl, gc);
        
        // == Button Panel ==============================
        // 4 rows, 1 column
        GridLayout gridLayout = new GridLayout(4,1);
        gridLayout.setVgap(25);
        
        buttonPnl.setLayout(gridLayout);
        buttonPnl.setBorder(new EmptyBorder(20,30,20,30));
        
        // -- create buttons ----------
        clockBtn = new JButton("Clock");
        countdownBtn = new JButton("Count Down");
        countupBtn = new JButton("Count Up");
        intlBtn = new JButton("International");
        
        clockBtn.setFocusPainted(false);
        countdownBtn.setFocusPainted(false);        
        countupBtn.setFocusPainted(false);        
        intlBtn.setFocusPainted(false);
        
        addActionListeners();
        
        // -- add components to button panel ----------
        buttonPnl.add(clockBtn);
        buttonPnl.add(countdownBtn);
        buttonPnl.add(countupBtn);
        buttonPnl.add(intlBtn);
        
        // -- add button panel to main panel ----------
        gc.ipadx = 80;
        gc.ipady = 40;
        gc.gridx = 0;
        gc.gridy = 1;
        
        mainPnl.add(buttonPnl, gc);
        
        // == Frame ==============================
        add(mainPnl);
        
        setSize(350, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Start the program
        new CS26115_Quiz3_Reginio();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clockBtn) {
//            System.out.println("Clock Button was pressed");
            new CS26115_DgtlAnlgClockInterface_Reginio("clock");
            setVisible(false);
        }
        else if (e.getSource() == countdownBtn) {
//            System.out.println("Countdown Button was pressed");
            new CS26115_DgtlAnlgClockInterface_Reginio("countdown");
            setVisible(false);
        }
        else if (e.getSource() == countupBtn) {
//            System.out.println("Countup Button was pressed");
            new CS26115_DgtlAnlgClockInterface_Reginio("countup");
            setVisible(false);
        }
        else if (e.getSource() == intlBtn) {
//            System.out.println("International Button was pressed");
            new CS26115_IntlInterface_Reginio();
            setVisible(false);
        }
    }
    
    private void addActionListeners() {
        clockBtn.addActionListener(this);
        countdownBtn.addActionListener(this);
        countupBtn.addActionListener(this);
        intlBtn.addActionListener(this);
    }
}

// References:
// https://stackoverflow.com/questions/972194/zero-padding-a-spinner-in-java
// https://stackoverflow.com/questions/9526041/how-to-program-for-a-stopwatch
// https://stackoverflow.com/questions/19727449/java-how-to-update-a-panel-every-second
// https://stackoverflow.com/questions/3858920/stop-a-swing-timer-from-inside-the-action-listener
// https://stackoverflow.com/questions/6980376/convert-from-days-to-milliseconds
// https://www.javaprogrammingforums.com/loops-control-statements/28948-making-countdown-timer-using-currenttimemillis-method.html
// https://stackoverflow.com/questions/24093414/how-can-i-know-time-in-a-specific-country-if-i-am-in-a-differenet-country
// https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/