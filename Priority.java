
package osass;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class Priority extends Process implements Comparable<Priority>{
    private float remainingTime;
    
    static List<Priority> SortByPriority(List<Priority> processes){
        Comparator<Priority> priorityComparator = Comparator.comparing(Priority::getPriority).thenComparing(Priority::getArrivalTime);
        Collections.sort(processes, priorityComparator);
        return processes;
    }

    static Vector<TimeStamp> priority(Process processes[], boolean preemptive){
        Vector<TimeStamp> timeline= new Vector<TimeStamp>();
        int s = processes.length;
        float time = 0.0f;
        int ready = 0;
        Priority processArray[] = new Priority[s];
        for(int i = 0;i < s; i++) processArray[i] = new Priority(processes[i].getProcessNumber(),processes[i].getArrivalTime(),processes[i].getBurstTime(), processes[i].getPriority());
        Arrays.sort(processArray);
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
            if(ready == s && p.isEmpty()) {
                for(int i = 0; i < processes.length; i++){
                    processes[i].setWaitingTime(processArray[i].getWaitingTime());                    
                    processes[i].setTurnArroundTime(processArray[i].getTurnArroundTime());

                }
                break;
            };
        }
        return timeline;
    }
    
    static void priority(Process Processes[]){
        priority(Processes, false);
    }

    public Priority(String processNumber, float arrivalTime, float burst, float priority) {
        super(processNumber, arrivalTime, burst, priority);
        this.remainingTime = burst;
    }
    
    static Priority[] vecToArr(Vector<Process> v){
        Priority arr[] = new Priority[v.size()];
        for (int i = 0; i < v.size(); i++) {
            arr[i] = new Priority(v.get(i).getProcessNumber(), v.get(i).getArrivalTime(), v.get(i).getBurstTime(), v.get(i).getPriority());
        }
        return arr;
    }

    @Override
    public int compareTo(Priority p) {
        return (int)(this.getArrivalTime() - p.getArrivalTime());
    }
    
     
}

