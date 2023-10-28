
import java.util.*;

public class CS26115_Countup_Reginio extends Thread {
    
    private long startTime;
    private int[] inputTime;
    private volatile boolean isRunning;
    
    public CS26115_Countup_Reginio() {
        setName("countup");
//        System.out.println("Set thread name to: " + getName());;
    }
    
    public void startThread() {
        startTime = System.currentTimeMillis();
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
            while (isRunning) {
                if (Arrays.equals(getOutputTime(), inputTime)) {
                    stopThread();
                }

                System.out.println(getOutputTime()[0] + ":" + getOutputTime()[1]
                                    + ":" + getOutputTime()[2]);
            }
        } catch (Exception e) {
            stopThread();
        }
        
        System.out.println("The countup is done");
    }
    
    public int[] getOutputTime() {
        long milliTime = System.currentTimeMillis() - startTime;
        int[] out = new int[]{ 0, 0, 0 };
        
        out[0] = (int)(milliTime / 3600000);
        out[1] = (int)(milliTime / 60000) % 60;
        out[2] = (int)(milliTime / 1000) % 60;
        
//        System.out.println(getTime()[0] + ":" + getTime()[1]
//                                + ":" + getTime()[2]);
        return out;
    }
    
    public void getInputTime(int hr, int min, int sec) {
        inputTime = new int[]{ hr, min, sec };
    }
}