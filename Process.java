package schedulers;

import java.util.*;

public class Process {
    private String processNumber;
    private float arrivalTime;
    private float burstTime;
    private float priority;
    private float endTime;
    
    private float waitingTime;
    private float turnArroundTime;
    
    Process(String num, float arrival, float burst, float my_priiorty){
        this.processNumber = num;
        this.arrivalTime = arrival;
        this.burstTime = burst;
        this.priority = my_priiorty;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(float arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public float getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(float burstTime) {
        this.burstTime = burstTime;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }
    
    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }
    
    public float getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(float waitingTime) {
        this.waitingTime = waitingTime;
    }

    public float getTurnArroundTime() {
        return turnArroundTime;
    }

    public void setTurnArroundTime(float turnArroundTime) {
        this.turnArroundTime = turnArroundTime;
    }
    
    static float maximum(Process arr[]){
        float maximum = arr[0].getBurstTime();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].getBurstTime() >= maximum)
                maximum = arr[i].getBurstTime();
        }
        return maximum;
    }
    
    static float avgWaitingTime(Process arr[]){
        float sum = arr[0].getWaitingTime();
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i].getWaitingTime();
        }
        return sum / arr.length;
    }
    
    static float avgTurnAroundTime(Process arr[]){
        float sum = arr[0].getTurnArroundTime();
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i].getTurnArroundTime();
        }
        return sum / arr.length;
    }

    static void printArray(Process arr[]){
        int n = arr.length;
        System.out.print("Process Infromation");
        for (int i = 0; i < n; i++){
        // Process Infromation
            System.out.print("\n\n" + arr[i].getProcessNumber() + "\nArival Time " + arr[i].getArrivalTime() + "\nBurst Time " + arr[i].getBurstTime() + "\n");
        }
        System.out.println();
    }
    
    static void printArray(Vector<TimeStamp> vec){
        int n = vec.size();
        System.out.print("Process Infromation");
        for (int i = 0; i < n; i++){ //vec[i]
            // Process Infromation
            System.out.print("\n\n" + vec.get(i).getProcessName()+ "\nStart Time " + vec.get(i).getStartTime() + "\nEnd Time " + vec.get(i).getEndTime() + "\n");
        }
        System.out.println();
        
    }
}