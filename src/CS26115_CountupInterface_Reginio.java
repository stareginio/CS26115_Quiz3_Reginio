
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;


public class CS26115_CountupInterface_Reginio extends JFrame implements ActionListener {
    
    private JSpinner hrSpn, minSpn, secSpn;
    private JButton startBtn;
    
    public CS26115_CountupInterface_Reginio() {
        setTitle("CS26115_Countup_Reginio");
        
        JPanel mainPnl = new JPanel();
        JPanel namePnl = new JPanel();
        JPanel dgtlClockPnl = new JPanel();
        JPanel anlgClockPnl = new JPanel();
        JPanel spinnerPnl = new JPanel();
        JPanel buttonPnl = new JPanel();
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // == Main Panel ==============================
        mainPnl.setLayout(new GridBagLayout());
        
        // for debugging
        mainPnl.setBackground(Color.pink);
        namePnl.setBackground(Color.green);
        spinnerPnl.setBackground(Color.gray);
        buttonPnl.setBackground(Color.blue);
        
        // == Name Panel ==============================
        namePnl.setLayout(new FlowLayout());
        namePnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        namePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePnl.setBorder(new EmptyBorder(0,30,10,30));
        
        // -- create name label ----------
        JLabel nameLbl = new JLabel("COUNTUP");
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
        
        // -- add components to name panel ----------
        namePnl.add(nameLbl);
        
        // -- add name panel to main panel ----------
        gc.gridx = 0;
        gc.gridy = 0;
        
        mainPnl.add(namePnl, gc);
        
        // == Spinner Panel ==============================
        // 1 row, 6 columns
        GridLayout gridLayout = new GridLayout(1,6);
        gridLayout.setHgap(10);
        
        spinnerPnl.setLayout(gridLayout);
        spinnerPnl.setBorder(new EmptyBorder(20,30,20,30));
        
        // -- create spinners ----------
        // start value, minimum value, maximum value, step value
        SpinnerModel spnModel = new SpinnerNumberModel(0,0,11,1);
        hrSpn = new JSpinner(spnModel);
        JFormattedTextField ftf =
                ((JSpinner.NumberEditor) hrSpn.getEditor()).getTextField();
        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        minSpn = new JSpinner(spnModel);
        ftf = ((JSpinner.NumberEditor) minSpn.getEditor()).getTextField();
        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        secSpn = new JSpinner(spnModel);
        ftf =((JSpinner.NumberEditor) secSpn.getEditor()).getTextField();
        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
        // -- apply zero-padding ----------
        hrSpn.setEditor(new JSpinner.NumberEditor(hrSpn, "00"));
        minSpn.setEditor(new JSpinner.NumberEditor(minSpn, "00"));
        secSpn.setEditor(new JSpinner.NumberEditor(secSpn, "00"));
        
        // -- create labels ----------
        JLabel hrLbl = new JLabel("hr");
//        hrLbl.setBorder(new EmptyBorder(0,15,0,15));
        
        JLabel minLbl = new JLabel("min");
//        minLbl.setBorder(new EmptyBorder(0,15,0,15));
        
        JLabel secLbl = new JLabel("sec");
//        secLbl.setBorder(new EmptyBorder(0,15,0,15));
        
        // -- add components to spinner panel ----------
        spinnerPnl.add(hrSpn);
        spinnerPnl.add(hrLbl);
        
        spinnerPnl.add(minSpn);
        spinnerPnl.add(minLbl);
        
        spinnerPnl.add(secSpn);
        spinnerPnl.add(secLbl);
        
        // -- add spinner panel to main panel ----------
        gc.ipady = 5;
        gc.gridx = 0;
        gc.gridy = 1;
        
        mainPnl.add(spinnerPnl, gc);
        
        // == Button Panel ==============================
        buttonPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.setBorder(new EmptyBorder(20,30,10,30));
        
        // -- create start button ----------
        startBtn = new JButton("Start");
        startBtn.addActionListener(this);
        
        // -- add components to spinner panel ----------
        buttonPnl.add(startBtn);
        
        // -- add spinner panel to main panel ----------
        gc.gridx = 0;
        gc.gridy = 2;
        
        mainPnl.add(buttonPnl, gc);
        
        // == Frame ==============================
        add(mainPnl);
        
        setSize(450, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) return t;
        }
        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CS26115_Countup_Reginio countup = new CS26115_Countup_Reginio();
        
        if (e.getSource() == startBtn) {
            Thread prevThread = getThreadByName("countup");
            
            // Stop previous countup if exists
            if (prevThread != null && prevThread.isAlive()) {
                prevThread.interrupt();
            }
            
            System.out.println("Button was pressed");
            
            int hr = (Integer) hrSpn.getValue();
            int min = (Integer) minSpn.getValue();
            int sec = (Integer) secSpn.getValue();
            
            System.out.println("hrSpn: " + hr);
            System.out.println("minSpn: " + min);
            System.out.println("secSpn: " + sec);
            
            // Check if input is invalid
            if (hr == 0 && min == hr && sec == hr) {
                JOptionPane.showMessageDialog(null,
                        "Please provide valid values for the hours (00 to 11),"
                                + " minutes (00 to 59),"
                                + " and seconds (00 to 59)",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                
                // NTS: RESET digital and analog clock appearances here (?)
            } else {
                // Start countup
                countup.startThread();
                
                // NTS: CHANGE digital and analog clock appearances here
            }
        }
    }
}
