
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;

public class CS26115_CountupInterface_Reginio extends JFrame implements ActionListener {
    
    private JPanel mainPnl, clockPnl;
    private JSpinner hrSpn, minSpn, secSpn;
    private JButton startBtn;
    private int[] inputTime = { 0 ,0, 0 };
    private long startTime = System.currentTimeMillis();
    private boolean isRunning = false;
    
    public CS26115_CountupInterface_Reginio() {
        setTitle("CS26115_Countup_Reginio");
        
        mainPnl = new JPanel();
        JPanel namePnl = new JPanel();
        JPanel spinnerPnl = new JPanel();
        JPanel buttonPnl = new JPanel();
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // == Main Panel ==============================
        mainPnl.setLayout(new GridBagLayout());
        
        // for debugging
        mainPnl.setBackground(Color.pink);
        namePnl.setBackground(Color.green);
        spinnerPnl.setBackground(Color.lightGray);
        buttonPnl.setBackground(Color.blue);
        
        // == Name Panel ==============================
        namePnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        namePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePnl.setBorder(new EmptyBorder(5,30,5,30));
        
        // -- create name label ----------
        JLabel nameLbl = new JLabel("COUNTUP");
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
        
        // -- add components to name panel ----------
        namePnl.add(nameLbl);
        
        // -- add name panel to main panel ----------
        gc.gridx = 0;
        gc.gridy = 0;
        
        mainPnl.add(namePnl, gc);
        
        // == Clock Panel ==============================
        // set to default time 00:00:00
        getClockPanel(0,0,0);
        
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
//        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        minSpn = new JSpinner(spnModel);
        ftf = ((JSpinner.NumberEditor) minSpn.getEditor()).getTextField();
//        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        secSpn = new JSpinner(spnModel);
        ftf =((JSpinner.NumberEditor) secSpn.getEditor()).getTextField();
//        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
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
        gc.fill = GridBagConstraints.NONE;
        gc.ipady = 5;
        gc.gridx = 0;
        gc.gridy = 2;
        
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
        gc.ipady = 0;
        gc.gridx = 0;
        gc.gridy = 3;
        
        mainPnl.add(buttonPnl, gc);
        
        // == Frame ==============================
        add(mainPnl);
        
        setSize(450, 680);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void getClockPanel(int hr, int min, int sec) {
        // remove previous clock panel if exists
        if (clockPnl != null) {
            mainPnl.remove(clockPnl);
        }
        
        clockPnl = new CS26115_ClockPanel_Reginio(hr, min, sec);
        
        clockPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        clockPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        clockPnl.setPreferredSize(new Dimension(60,420));
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 1;
        
        mainPnl.add(clockPnl, gc);
        mainPnl.revalidate();
        mainPnl.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) { 
        Timer timer;
        
        // Check if countup is running
        if (isRunning) {
            int[] currentTime = getTime();
            
            // for debugging
//            System.out.println(
//                    "inputTime: " + String.format("%02d", inputTime[0])
//                    + ":" + String.format("%02d", inputTime[0])
//                    + ":" + String.format("%02d", inputTime[2])
//            );

            System.out.println(
                    String.format("%02d", getTime()[0])
                    + ":" + String.format("%02d", getTime()[1])
                    + ":" + String.format("%02d", getTime()[2])
            );
            
            getClockPanel(currentTime[0], currentTime[1], currentTime[2]);

            // Check if countup should be finished
            if (Arrays.equals(getTime(), inputTime)) {
                System.out.println("-- STOP -----");
//                timer.stop();
                ((Timer) e.getSource()).stop();
                isRunning = false;
                
//                20,30,20,30
                Border stopBorder = BorderFactory.createLineBorder(Color.red, 6);
                clockPnl.setPreferredSize(new Dimension(60,420));
                clockPnl.setBorder(stopBorder);
            }
        }
        
        // Check if start button is clicked
        if (e.getSource() == startBtn) {            
            int hr = (Integer) hrSpn.getValue();
            int min = (Integer) minSpn.getValue();
            int sec = (Integer) secSpn.getValue();
            
            inputTime = new int[] { hr, min, sec };
            
            System.out.println("Button was pressed");
            System.out.println("hrSpn: " + hr);
            System.out.println("minSpn: " + min);
            System.out.println("secSpn: " + sec);
            
            // Check if input is invalid
            if (Arrays.equals(inputTime, new int[] { 0, 0, 0 })) {
                JOptionPane.showMessageDialog(
                        null,
                        """
                        Please provide valid values for the hours (00 to 11),
                        minutes (00 to 59), and seconds (00 to 59).""",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE
                );
                
                // Reset clocks
                getClockPanel(0,0,0);
            } else {
                // Create timer
                timer = new Timer(1000, this);
                timer.setRepeats(true);
                timer.setInitialDelay(1);
                
                // Start timer
                System.out.println("-- START -----");
                isRunning = true;
                startTime = System.currentTimeMillis();
                timer.start();
            }
        }
    }
    
    public int[] getTime() {
        long milliTime = System.currentTimeMillis() - startTime;
        int[] out = new int[]{ 0, 0, 0 };
        
        out[0] = (int)(milliTime / 3600000);
        out[1] = (int)(milliTime / 60000) % 60;
        out[2] = (int)(milliTime / 1000) % 60;
        
        return out;
    }
}