
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.border.*;

public class CS26115_DgtlAnlgClockInterface_Reginio extends JFrame implements ActionListener {
    
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
    
    public CS26115_DgtlAnlgClockInterface_Reginio(String buttonName) {
        setTitle("CS26115_Countup_Reginio");
        
        this.buttonName = buttonName;
        maxHr = buttonName.equals("clock") ? 23 : 99;
        
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
        namePnl.setBorder(new EmptyBorder(0,30,10,30));
        
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
        spinnerPnl.setBorder(new EmptyBorder(20,30,20,30));
        
        // -- create spinners ----------
        // start value, minimum value, maximum value, step value
        SpinnerModel spnModel = new SpinnerNumberModel(0,0,maxHr,1);
        hrSpn = new JSpinner(spnModel);
//        JFormattedTextField ftf =
//                ((JSpinner.NumberEditor) hrSpn.getEditor()).getTextField();
//        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        minSpn = new JSpinner(spnModel);
//        ftf = ((JSpinner.NumberEditor) minSpn.getEditor()).getTextField();
//        ((NumberFormatter) ftf.getFormatter()).setAllowsInvalid(false);
        
        spnModel = new SpinnerNumberModel(0,0,59,1);
        secSpn = new JSpinner(spnModel);
//        ftf =((JSpinner.NumberEditor) secSpn.getEditor()).getTextField();
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
        
        addActionListeners();
        
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
        clockPnl = new CS26115_DgtlAnlgClockPanel_Reginio(hr, min, sec);
        
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
        // == Check if countup is running ==============================
        if (isRunning) {
            int[] currentTime = getCurrentTime();
            
//            System.out.println(
//                    "inputTime: " + String.format("%02d", inputTime[0])
//                    + ":" + String.format("%02d", inputTime[0])
//                    + ":" + String.format("%02d", inputTime[2])
//            );

            System.out.println(
                    String.format("%02d", getCurrentTime()[0])
                    + ":" + String.format("%02d", getCurrentTime()[1])
                    + ":" + String.format("%02d", getCurrentTime()[2])
            );
            
            getClockPanel(currentTime[0], currentTime[1], currentTime[2]);
            
            // Check if countup should be finished
            // For countdown, stop if 00:00:00 is reached
            // For countup, stop if the input time is reached
            // Note: no stop for clock
            if ((buttonName.equals("countup")
                    && Arrays.equals(getCurrentTime(), inputTime))
                    || (buttonName.equals("countdown")
                    && Arrays.equals(getCurrentTime(), new int[] { 0,0,0 }))) {
                System.out.println("-- STOP -----");
                timer.stop();
                isRunning = false;
                
                Border stopBorder = BorderFactory.createLineBorder(Color.red, 6);
                clockPnl.setPreferredSize(new Dimension(60,420));
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
            if (Arrays.equals(inputTime, new int[] { 0,0,0 })) {
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
                    startTime = getStartTimeMillis();
                }
                
                // Start timer
                System.out.println("-- START -----");
                isRunning = true;
                timer.start();
            }
        }
    }
    
    public int[] getCurrentTime() {
        int[] time = new int[]{ 0, 0, 0 };
        long milliTime = System.currentTimeMillis() - startTime;
        
        if (buttonName.equals("countdown")) {
//            milliTime = startTime - (System.currentTimeMillis() - startTime);
            milliTime = endTime - System.currentTimeMillis();
        }
        
        time[0] = (int)(milliTime / 3600000);
        time[1] = (int)(milliTime / 60000) % 60;
        time[2] = (int)(milliTime / 1000) % 60;
        
        // Reset display to 00:00:00 when time reaches 24:00:00
        if (buttonName.equals("clock") && time[0] >= 24) {
            time[0] = (int) (time[0] - 24 * Math.floor(time[0]/24));
        }
        
        return time;
    }
    
    public long getStartTimeMillis() {
        long time = TimeUnit.HOURS.toMillis(inputTime[0]);
        time += TimeUnit.MINUTES.toMillis(inputTime[1]);
        time += TimeUnit.SECONDS.toMillis(inputTime[2]);
        
        if (buttonName.equals("countdown")) {
            // add 1000 for 1 second delay
            endTime = System.currentTimeMillis() + time + 1000;
        }
        time = System.currentTimeMillis() - time;
        
        return time;
    }
    
    private void addActionListeners() {
        startBtn.addActionListener(this);
    }
}