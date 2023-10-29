
import java.util.*;

public class CS26115_Countup_Reginio extends Thread {
    
    private long startTime;
    private int[] inputTime;
    private boolean isRunning;
    
    // NTS: unused class !!!!!!!!!!!
    
    public CS26115_Countup_Reginio(int[] inputTime) {
        this.inputTime = inputTime;
        setName("countup");
//        System.out.println("Set thread name to: " + getName());;
    }
    
    public void startThread() {
//        startTime = System.currentTimeMillis();
        startTime = 0;
        isRunning = true;
        start();
    }
    
    public void stopThread() {
        isRunning = false;
    }
    
    @Override
    public void interrupt() {
        stopThread();
    }
    
    @Override
    public void run() {
        System.out.println("The countup is starting");
        
        try {
            int prevSec = -1;
            while (isRunning) {
                if (getTime()[2] != prevSec) {
                    System.out.println(getTime()[0] + ":" + getTime()[1]
                                    + ":" + getTime()[2]);
                    prevSec = getTime()[2];

                    if (Arrays.equals(getTime(), inputTime)) {
                        stopThread();
                    }
                }
            }
        } catch (Exception e) {
            stopThread();
        }
        
        System.out.println("The countup is done");
    }
    
    public int[] getTime() {
        long milliTime = System.currentTimeMillis() - startTime;
        int[] out = new int[]{ 0, 0, 0 };
        
        out[0] = (int)(milliTime / 3600000);
        out[1] = (int)(milliTime / 60000) % 60;
        out[2] = (int)(milliTime / 1000) % 60;
        
//        System.out.println(getTime()[0] + ":" + getTime()[1]
//                                + ":" + getTime()[2]);
        return out;
    }
}