
import java.util.*;

public class CS26115_Countup_Reginio extends Thread {
    
    private long startTime;
    private int[] inputTime;
    private boolean isRunning;
    
    public CS26115_Countup_Reginio(int hr, int min, int sec) {
        inputTime = new int[]{hr,min,sec};
        startThread();
    }
    
    private void startThread() {
        startTime = System.currentTimeMillis();
        isRunning = true;
        start();
    }
    
    public void stopThread() {
        isRunning = false;
    }
    
    public void run() {
        System.out.println("The countup is starting");
        while(isRunning) {
            if (Arrays.equals(getTime(), inputTime)) {
                stopThread();
            }
            
            System.out.println(getTime()[0] + ":" + getTime()[1]
                                + ":" + getTime()[2]);
        }
        System.out.println("The countup is done");
    }
    
    public int[] getTime() {
        long milliTime = System.currentTimeMillis() - startTime;
        int[] out = new int[]{0,0,0};
        
        out[0] = (int)(milliTime / 3600000);
        out[1] = (int)(milliTime / 60000) % 60;
        out[2] = (int)(milliTime / 1000) % 60;
        
//        System.out.println(getTime()[0] + ":" + getTime()[1]
//                                + ":" + getTime()[2]);
        return out;
    }
}