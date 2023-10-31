
import java.text.*;
import java.util.*;
import java.util.concurrent.*;

public class CS26115_Time_Reginio {
    
    private long endTime;
    private int[][] timeZones;
    
    // Empty constructor
    public CS26115_Time_Reginio() { }
    
    // Constructor for international
    public CS26115_Time_Reginio(String[] clockNames) {
        timeZones = new int[4][3];
        
        // Get each timezone
        for (int i=0; i < clockNames.length; i++) {
            timeZones[i] = getTimeZones(clockNames[i]);
            
            System.out.println("timeZones[" + i + "]: " + timeZones[i][0]
                                    + ":" + timeZones[i][1]
                                    + ":" + timeZones[i][2]);
        }
    }
    
    public int[] getCurrentTime(String buttonName,
            long startTime, long endTime) {
        int[] time = new int[]{ 0, 0, 0 };
        long milliTime = buttonName.equals("countdown")
                ? endTime - System.currentTimeMillis()
                : System.currentTimeMillis() - startTime;
        
//        System.out.println("startTime: " + startTime);
//        System.out.println("milliTime: " + milliTime);
        
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
    
    private static int[] getTimeZones(String name) {
        String timeZoneId = "UTC";
        
        switch (name) {
            case "MANILA" -> timeZoneId = "Asia/Hong_Kong";
            case "TOKYO" -> timeZoneId = "Asia/Tokyo";
            case "NEW YORK" -> timeZoneId = "America/New_York";
            case "LONDON" -> timeZoneId = "Europe/London";
            default -> { }
        }
        
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        
        df.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        String[] timeArr = df.format(date).split(":");
        
        System.out.println("timeArr for " + name + ": " + timeArr[0]
                + ":" + timeArr[1]
                + ":" + timeArr[2]
        );
        
//        timeZone = TimeUnit.HOURS.toMillis(Long.parseLong(timeArr[0]));
//        timeZone += TimeUnit.MINUTES.toMillis(Long.parseLong(timeArr[1]));
//        timeZone += TimeUnit.SECONDS.toMillis(Long.parseLong(timeArr[2]));
        
//        time = System.currentTimeMillis() - time;
        
        return new int[] {
            Integer.parseInt(timeArr[0]),
            Integer.parseInt(timeArr[1]),
            Integer.parseInt(timeArr[2])
        };
    }
    
    public int[] getTimeZonebyName(String name) {
        int[] timeZone = new int[2];
        
        switch (name) {
            case "MANILA" -> timeZone = timeZones[0];
            case "TOKYO" -> timeZone = timeZones[1];
            case "NEW YORK" -> timeZone = timeZones[2];
            case "LONDON" -> timeZone = timeZones[3];
            default -> { }
        }
        
        System.out.println("timeZone[0]: " + timeZone[0]);
        System.out.println("timeZone[1]: " + timeZone[1]);
        System.out.println("timeZone[2]: " + timeZone[2]);
        
        return timeZone;
    }
}
