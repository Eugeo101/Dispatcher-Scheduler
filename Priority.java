package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class Priority extends Process{
    private float remainingTime;
    
    static List<Priority> SortByPriority(List<Priority> processes){
        Comparator<Priority> priorityComparator = Comparator.comparing(Priority::getPriority).thenComparing(Priority::getArrivalTime);
        Collections.sort(processes, priorityComparator);
        return processes;
    }
    
    static Vector<TimeStamp> priority(Process processes[], int wt[], boolean preemptive){
        Vector<TimeStamp> timeline= new Vector<TimeStamp>();
        int s = processes.length;
        float time = 0.0f;
        int ready = 0;
        Priority processArray[] = new Priority[s];
        for(int i = 0;i < s; i++) processArray[i] = new Priority(processes[i].getProcessNumber(),processes[i].getArrivalTime(),processes[i].getBurstTime(), processes[i].getPriority());
        List<Priority> p = new ArrayList<>();
        Priority current, next;
        while(true){
            
            //Get all processes that arrived and ready to excute
            for(int i = ready; i < s; i++){
                if(processArray[i].getArrivalTime() <= time){
                    p.add(processArray[i]);
                    ready++;
                }else break;
            }
            
            //Sort process based on priority
            SortByPriority(p);
            
            if(p.size() > 0){ //Not idle
                current = p.get(0);
                if(preemptive){ //Preemtive
                    next = ready < s ? processArray[ready] : current; //Get next process that didn't arrive
                    if(next.getArrivalTime() < (current.remainingTime + time) && next.getPriority() < current.getPriority()){
                        float timeLeft = current.remainingTime + time - next.getArrivalTime();
                        Priority backToQueue = new Priority(current.getProcessNumber(), current.getArrivalTime(), timeLeft, current.getPriority());
                        p.add(backToQueue); // Re-add process to queue
                        timeline.add(new TimeStamp(time, next.getArrivalTime(), current.getProcessNumber()));
                        time  = next.getArrivalTime();
                    }else{ //Process will finish without interupts      
                        timeline.add(new TimeStamp(time, time + current.remainingTime, current.getProcessNumber()));
                        time += current.remainingTime;
                        current.setEndTime(time);
                        current.setWaitingTime(current.getEndTime() - current.getArrivalTime() - current.getBurstTime());
                        current.setTurnArroundTime(time - current.getArrivalTime());
                    }
                    p.remove(0);
                }else{ //Non-preemtive
                    timeline.add(new TimeStamp(time, time + current.remainingTime, current.getProcessNumber()));
                    current.setWaitingTime(time - current.getArrivalTime());
                    time += current.remainingTime;
                    current.setTurnArroundTime(time - current.getArrivalTime());
                    p.remove(0);
                }
            }else{ //idle
                timeline.add(new TimeStamp(time, processArray[ready].getArrivalTime(), "IDLE"));
                time += processArray[ready].getArrivalTime() - time;
            }
            if(ready == s && p.isEmpty()) break;
        }
        return timeline;
    }
    
    static void priority(Process Processes[], int wt[]){
        priority(Processes, wt, false);
    }

    public Priority(String processNumber, float arrivalTime, float burst, float priority) {
        super(processNumber, arrivalTime, burst, priority);
        this.remainingTime = burst;
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
        
        
        Priority my_proc[] = vecToArr(my_process); // 2 to array
        Vector<TimeStamp> vector1 = priority(my_proc, 2.0F); //3rd RR quantium = input float
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
