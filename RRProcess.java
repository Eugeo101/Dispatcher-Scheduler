package schedulers;

import java.util.*;

public class RRProcess extends Process implements Comparable<RRProcess> {
    
    private float remainingBurstTime;

    public RRProcess(String num, float arrival, float burst, float my_priiorty) {
        super(num, arrival, burst, my_priiorty);
        remainingBurstTime = burst;
    }

    public float getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public void setRemainingBurstTime(float remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }
    
    @Override
    public int compareTo(RRProcess o) {
        if (getArrivalTime() > o.getArrivalTime())
            return 1;
        else if (getArrivalTime() < o.getArrivalTime())
            return -1;
        else
            return 0;
    }
    
    static RRProcess[] vecToArr(Vector<Process> v){
        RRProcess arr[] = new RRProcess[v.size()];
        for (int i = 0; i < v.size(); i++) {
            arr[i] = new RRProcess(v.get(i).getProcessNumber(), v.get(i).getArrivalTime(), v.get(i).getBurstTime(), v.get(i).getPriority());
        }
        return arr;
    }
    
    static Vector<TimeStamp> RRSchedule(RRProcess processes[], float quantum)
    {
        Arrays.sort(processes); //IDLE P1 0-1 IDLE P2-10 
        printArray(processes);
        Vector<TimeStamp> result_arr = new Vector<TimeStamp>();
        int n = processes.length;
        float t;
        if (processes[0].getArrivalTime() != 0){
            t = processes[0].getArrivalTime();
            result_arr.add(new TimeStamp(0, processes[0].getArrivalTime(), "IDLE"));
        }
        else{
            t = 0;
        }
        while(true)
        {
            boolean done = true;
      
            for (int i = 0 ; i < n; i++)
            {
                // min = 1 => IDLE Burst=min 
                if (processes[i].getRemainingBurstTime() > 0 && processes[i].getArrivalTime() <= t)// idle arival=0 burst = min
                {
                    done = false;
      
                    if (processes[i].getRemainingBurstTime() > quantum)
                    {
                        t += quantum;
                        result_arr.add(new TimeStamp(t - quantum, t, processes[i].getProcessNumber()));
      
                        processes[i].setRemainingBurstTime(processes[i].getRemainingBurstTime() - quantum);
                    }
      
                    else
                    {
                        t = t + processes[i].getRemainingBurstTime();
                        
                        result_arr.add(new TimeStamp(t - processes[i].getRemainingBurstTime(), t, processes[i].getProcessNumber()));
                        
                        processes[i].setEndTime(t);
      
                        processes[i].setWaitingTime(t - processes[i].getArrivalTime() - processes[i].getBurstTime());
                        
                        processes[i].setTurnArroundTime(t - processes[i].getArrivalTime()); // 2 - 10
      
                        processes[i].setRemainingBurstTime(0);
                    }
                }
                if(processes[(i + 1) % n].getArrivalTime() > t){
                    if (reaminingProcess(processes, i) != -1){
                        i = reaminingProcess(processes, i) - 1;
                        continue;
                    }
                    else {
                        result_arr.add(new TimeStamp(t, processes[i+1].getArrivalTime(), "IDLE"));
                        t = processes[i+1].getArrivalTime();
                    }
                }
            }
      
            if (done == true)
              return result_arr;
        }
    }
    
    public static int reaminingProcess(RRProcess[] processes, int i){
        for (int j = 0; j < i + 1; j++) {
            if (processes[j].remainingBurstTime > 0){
                return j;
            } 
        }
        return -1;
    }
    
    public static void main(String args[]){
//        RRProcess [] my_process = new RRProcess[5]; // ()
//        my_process[0] = new RRProcess("P5", 3, 2, 0);
//        my_process[1] = new RRProcess("P1", 0, 2, 0);
//        my_process[2] = new RRProcess("P3", 2, 1, 0);
//        my_process[3] = new RRProcess("P4", 2, 3, 0);
//        my_process[4] = new RRProcess("P2", 0, 4, 0);
        Vector<Process> my_process = new Vector<Process>(); //1 vector
        
//        my_process.add(new Process("P5", 3, 2, 0));
//        my_process.add(new Process("P1", 1, 2, 0));
//        my_process.add(new Process("P3", 2, 1, 0));
//        my_process.add(new Process("P4", 2, 3, 0));
//        my_process.add(new Process("P2", 1, 4, 0));
        my_process.add(new Process("P1", 1, 4, 0));
        my_process.add(new Process("P1_2", 3, 4, 0));
        my_process.add(new Process("P2", 10, 4, 0));
        
        
        RRProcess my_proc[] = vecToArr(my_process); // 2 to array
        Vector<TimeStamp> vector1 = RRSchedule(my_proc, 2.0F); //3rd RR quantium = input float
        printArray(vector1);
        System.out.println("avg Waiting Time:\t" + avgWaitingTime(my_proc));
        System.out.println("avg Turn Around Time:\t" + avgTurnAroundTime(my_proc));
        
//        //FCFS
//        Vector<TimeStamp> vector2 = RRSchedule(my_proc, maximum(my_proc)); //3rd FCFC quantum = maximum(my_proc)
//        printArray(vector2);
//        System.out.println("avg Waiting Time:\t" + avgWaitingTime(my_proc)); //4th avg waiting  vs avg turnaround
//        System.out.println("avg Turn Around Time:\t" + avgTurnAroundTime(my_proc));
    }
}