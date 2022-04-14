package schedulers;
import java.util.*;

public class SJF {
    static Vector<TimeStamp> sjf_p(Process myProcess[])
    {
        Vector<TimeStamp> result_arr = new Vector<TimeStamp>();
        int s = myProcess.length;
        float burTime[] = new float[s];
        for (int i = 0; i < s; i++)
            burTime[i] = myProcess[i].getBurstTime();
      
        int complete = 0;
        float t = 0;
        float minbt = Integer.MAX_VALUE;
        int leastIndex = 0;
        float finish_time;
        boolean flag = false;
        int prevLeastIndex;
        float wt_time, ta_time;
      
        while (complete != s) {
            prevLeastIndex = leastIndex;
                for (int j = 0; j < s; j++){
                    if ((myProcess[j].getArrivalTime() <= t) &&
                      (burTime[j] < minbt) && burTime[j] > 0) {
                        minbt = burTime[j];
                        leastIndex = j;
                        flag = true;
                    }
                }
            }
      
            if (flag == false) {
                t++;
                result_arr.add(new TimeStamp(0,t,"IDLE"));
                continue;
            }
            if (leastIndex != prevLeastIndex){
                result_arr.add(new TimeStamp(0,t,myProcess[prevLeastIndex].getProcessNumber()));
            }
            burTime[leastIndex]--;
            minbt = burTime[leastIndex];
            
            if (minbt == 0)
                minbt = Integer.MAX_VALUE;
      
            if (burTime[leastIndex] == 0) {
                complete++;
                flag = false;
      
                finish_time = t + 1;
                if (complete == s){
                    result_arr.add(new TimeStamp(0,finish_time,myProcess[leastIndex].getProcessNumber()));
                }
                wt_time = finish_time - myProcess[leastIndex].getBurstTime() - myProcess[leastIndex].getArrivalTime();
                myProcess[leastIndex].setWaitingTime(wt_time);
                ta_time = finish_time - myProcess[leastIndex].getArrivalTime();
                myProcess[leastIndex].setTurnArroundTime(ta_time);
            }
            t++;
        }
        for(int i = 0; i < s; i++){
            if(result_arr.get(i).getP().equals(result_arr.get(i+1).getP())){
                result_arr.remove(i);
            }
        }
        return result_arr;
    }
    
    
    static Vector<TimeStamp> sjf_np(Process myProcess[])
    {
        Vector<TimeStamp> result_arr = new Vector<TimeStamp>();
        int s = myProcess.length;
        float burTime[] = new float[s];
        for (int i = 0; i < s; i++)
            burTime[i] = myProcess[i].getBurstTime();
      
        int complete = 0;
        float t = 0;
        float minbt = Integer.MAX_VALUE;
        int leastIndex = 0;
        float finish_time;
        boolean flag = false;
        int prevLeastIndex;
        float wt_time, ta_time;
      
        while (complete != s) {
            prevLeastIndex = leastIndex;
            if(minbt == Integer.MAX_VALUE){
                for (int j = 0; j < s; j++){
                    if ((myProcess[j].getArrivalTime() <= t) &&
                      (burTime[j] < minbt) && burTime[j] > 0) {
                        minbt = burTime[j];
                        leastIndex = j;
                        flag = true;
                    }
                }
            }
      
            if (flag == false) {
                t++;
                result_arr.add(new TimeStamp(0,t,"IDLE"));
                continue;
            }
            if (leastIndex != prevLeastIndex){
                result_arr.add(new TimeStamp(0,t,myProcess[prevLeastIndex].getProcessNumber()));
            }
            burTime[leastIndex]--;
            minbt = burTime[leastIndex];
            
            if (minbt == 0)
                minbt = Integer.MAX_VALUE;
      
            if (burTime[leastIndex] == 0) {
                complete++;
                flag = false;
      
                finish_time = t + 1;
                if (complete == s){
                    result_arr.add(new TimeStamp(0,finish_time,myProcess[leastIndex].getProcessNumber()));
                }
                wt_time = finish_time - myProcess[leastIndex].getBurstTime() - myProcess[leastIndex].getArrivalTime();
                myProcess[leastIndex].setWaitingTime(wt_time);
                ta_time = finish_time - myProcess[leastIndex].getArrivalTime();
                myProcess[leastIndex].setTurnArroundTime(ta_time);
            }
            t++;
        }
        for(int i = 0; i < s; i++){
            if(result_arr.get(i).getP().equals(result_arr.get(i+1).getP())){
                result_arr.remove(i);
            }
        }
        return result_arr;
    }
}
