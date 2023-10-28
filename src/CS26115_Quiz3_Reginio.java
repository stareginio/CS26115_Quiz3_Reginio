
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
        clockBtn.addActionListener(this);
        
        countdownBtn.setFocusPainted(false);
        countdownBtn.addActionListener(this);
        
        countupBtn.setFocusPainted(false);
        countupBtn.addActionListener(this);
        
        intlBtn.setFocusPainted(false);
        intlBtn.addActionListener(this);
        
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
        CS26115_Quiz3_Reginio quizThree = new CS26115_Quiz3_Reginio();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clockBtn) {
            System.out.println("Clock Button was pressed");
        }
        else if (e.getSource() == countdownBtn) {
            System.out.println("Countdown Button was pressed");
        }
        else if (e.getSource() == countupBtn) {
            System.out.println("Countup Button was pressed");
            CS26115_CountupInterface_Reginio countup =
                    new CS26115_CountupInterface_Reginio();
            setVisible(false);
        }
        else if (e.getSource() == intlBtn) {
            System.out.println("Button 4 was pressed");
        }
    }
}
