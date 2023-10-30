
import java.util.concurrent.*;

public class CS26115_Time_Reginio {
    
    private long endTime;
    
    // Empty constructor
    public CS26115_Time_Reginio() { }
    
    public int[] getCurrentTime(String buttonName,
            long startTime, long endTime) {
        int[] time = new int[]{ 0, 0, 0 };
        long milliTime = System.currentTimeMillis() - startTime;
        
        if (buttonName.equals("countdown")) {
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
    
    public long getStartTimeMillis(int[] inputTime, String buttonName) {
        long time = TimeUnit.HOURS.toMillis(inputTime[0]);
        time += TimeUnit.MINUTES.toMillis(inputTime[1]);
        time += TimeUnit.SECONDS.toMillis(inputTime[2]);
        
        // add 1000 for 1 second delay
        endTime = System.currentTimeMillis() + time + 1000;
        
        time = System.currentTimeMillis() - time;
        
        return time;
    }
    
    public long getEndTime(long time) {
        return endTime;
    }
}
