
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class CS26115_Quiz3_DgtlAnlgClockInterface_Reginio extends JFrame
        implements ActionListener {
    
    private final String buttonName;
    private final JPanel mainPnl;
    private final JSpinner hrSpn, minSpn, secSpn;
    private final JButton startBtn;
    private final int maxHr;
    private JPanel clockPnl;
    private javax.swing.Timer timer;
    private int[] inputTime = { 0 ,0, 0 };
    private long startTime, endTime;
    private boolean isRunning = false;
    
    public CS26115_Quiz3_DgtlAnlgClockInterface_Reginio(String buttonName) {
        this.buttonName = buttonName;
        maxHr = buttonName.equals("clock") ? 23 : 99;
        
        setTitle(
                "CS26115_"
                    + buttonName.substring(0,1).toUpperCase()
                    + buttonName.substring(1).toLowerCase()
                    + "_Reginio"
        );
        
        mainPnl = new JPanel();
        JPanel namePnl = new JPanel();
        JPanel spinnerPnl = new JPanel();
        JPanel buttonPnl = new JPanel();
        
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
        namePnl.setBorder(new EmptyBorder(0,30,20,30));
        
        // -- create name label ----------
        JLabel nameLbl = new JLabel(buttonName.toUpperCase());
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
        spinnerPnl.setBorder(new EmptyBorder(20,40,20,20));
        
        // -- create spinners ----------
        // start value, minimum value, maximum value, step value
        SpinnerModel spnModel =
                new SpinnerNumberModel(0,0,maxHr,1);
        hrSpn = new JSpinner(spnModel);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        minSpn = new JSpinner(spnModel);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        secSpn = new JSpinner(spnModel);
        
        // adjust height
        hrSpn.setPreferredSize(new Dimension(hrSpn.getWidth(), 25));
        
        // -- apply zero-padding ----------
        hrSpn.setEditor(new JSpinner.NumberEditor(hrSpn, "00"));
        minSpn.setEditor(new JSpinner.NumberEditor(minSpn, "00"));
        secSpn.setEditor(new JSpinner.NumberEditor(secSpn, "00"));
        
        // -- create labels ----------
        JLabel hrLbl = new JLabel("hr");
        JLabel minLbl = new JLabel("min");
        JLabel secLbl = new JLabel("sec");
        
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
        buttonPnl.setBorder(new EmptyBorder(10,30,10,30));
        
        // -- create start button ----------
        startBtn = new JButton("Start");
        
        // -- add an action listener to the button ----------
        addActionListener();
        
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
        // == Remove previous clock panel if exists ============================
        if (clockPnl != null) {
            mainPnl.remove(clockPnl);
        }
        
        // == Create new clock panel ==============================
        clockPnl = new CS26115_Quiz3_DgtlAnlgClockPanel_Reginio(hr, min, sec);
        
        clockPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
        clockPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        clockPnl.setPreferredSize(new Dimension(0,430));
        
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
        CS26115_Quiz3_Time_Reginio t = new CS26115_Quiz3_Time_Reginio();
        
        // == Check if countup is running ==============================
        if (isRunning) {
            int[] currentTime = t.getCurrentTime(
                    buttonName, startTime, endTime
            );
            
//            System.out.println(
//                    "inputTime: " + String.format("%02d", inputTime[0])
//                    + ":" + String.format("%02d", inputTime[0])
//                    + ":" + String.format("%02d", inputTime[2])
//            );

            System.out.println(
                    String.format("%02d", currentTime[0])
                    + ":" + String.format("%02d", currentTime[1])
                    + ":" + String.format("%02d", currentTime[2])
            );
            
            // Update the clock panel
            getClockPanel(currentTime[0], currentTime[1], currentTime[2]);
            
            // Check if countup should be finished
            // For countdown, stop if 00:00:00 is reached
            // For countup, stop if the input time is reached
            // Note: no stop for clock
            if ((buttonName.equals("countup")
                    && Arrays.equals(currentTime, inputTime))
                    || (buttonName.equals("countdown")
                    && Arrays.equals(currentTime, new int[] {0,0,0}))) {
                System.out.println("-- STOP -----");
                timer.stop();
                isRunning = false;
                
                Border stopBorder =
                        BorderFactory.createLineBorder(Color.red, 6);
                clockPnl.setPreferredSize(new Dimension(60,430));
                clockPnl.setBorder(stopBorder);
            }
        }
        
        // == Check if start button is clicked ==============================
        if (e.getSource() == startBtn) {
            int hr = (Integer) hrSpn.getValue();
            int min = (Integer) minSpn.getValue();
            int sec = (Integer) secSpn.getValue();
            
            inputTime = new int[] { hr, min, sec };
            
//            System.out.println("Start button was pressed");
//            System.out.println("hrSpn: " + hr);
//            System.out.println("minSpn: " + min);
//            System.out.println("secSpn: " + sec);
            
            // Check if input is invalid
            if (Arrays.equals(inputTime, new int[] {0,0,0})) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please provide valid values for the hours (00 to "
                                + maxHr + "), minutes (00 to 59),"
                                + " and seconds (00 to 59).",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE
                );
                
                // Reset clocks
                getClockPanel(0,0,0);
            } else {
                // Check for previous timer if exists
                if (timer != null) {
                    timer.stop();
                }
                
                // Create timer
                timer = new javax.swing.Timer(1000, this);
                timer.setRepeats(true);
                timer.setInitialDelay(1);
                
                // Get start time
                if (buttonName.equals("countup")) {
                    startTime = System.currentTimeMillis();
                } else {    // clock and countdown
                    startTime = t.getStartTimeMillis(inputTime, buttonName);
                    
                    if (buttonName.equals("countdown")) {
                        endTime = t.getEndTime(startTime);
                    }
                }
                
                // Start timer
                System.out.println("-- START -----");
                isRunning = true;
                timer.start();
            }
        }
    }
    
    private void addActionListener() {
        startBtn.addActionListener(this);
    }
}