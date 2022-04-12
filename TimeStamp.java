package schedulers;

public class TimeStamp {
    private float startTime;
    private float endTime;
    private String processName;

    public TimeStamp(float startTime, float endTime, String processName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.processName = processName;
    }
    
    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
