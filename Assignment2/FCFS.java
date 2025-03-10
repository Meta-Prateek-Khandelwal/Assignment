package Assignment2;
import java.util.Scanner;
import java.util.PriorityQueue;

class FCFS{

    // create a array
    static int[] CT ;
    static int[] TAT;
    static int[] AT ;
    static int[] BT;
    static int[] WT;

    // methods

    // complation time method
    static int[] complationTime(PriorityQueue<int[]> pq){
        CT = new int[n];
        BT = new int[n];
        AT = new int[n];
        int i = 0;

        while(!pq.isEmpty()){
            int[] pair = pq.poll();

            int arrival = pair[0];
            AT[i] = arrival;
            int burst = pair[1];
            BT[i] = burst;

            if(i == 0){
                CT[i] = arrival + burst;
            }else if(CT[i-1] >= arrival){
                CT[i] = CT[i-1] + burst;
            }else{
                CT[i] = arrival + burst;
            }
            i++;
        }

        return CT;
    }
    
    // trun around time method
    static int[] trunArroundTime(int[] CT, int[] AT){
        TAT = new int[n];
        
        // turn arround time = complation time - arrival time
        for(int i = 0; i < n; i++){
            TAT[i] = CT[i] - AT[i];
        }

        return TAT;
    }

    // waiting time method
    static int[] waitingTime(int[] TAT, int[] AT){
        WT = new int[n];
        // Waiting time = turn Around time - burst time
        for(int i = 0; i < n; i++){
            WT[i] = TAT[i] - BT[i];
        } 
        return WT;     
    }

    // avrage waiting time method
    static float avgWaitingTime(int[] WT){
        int TWT = 0;
        
        for(int x: WT){
            TWT += x;
        }

        float AWT = TWT / n;
        return AWT;
    }

    // maximum waiting time method
    static int maxWaitingTime(int[] WT){
        int MWT = 0;

        for(int x: WT){
            if(x > MWT) MWT = x;
        }
        
        return MWT;
    }

    // size of priority queue
    static int n ;

    // main method
    public static void main(String[] args){

        // use priority queue is use
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        pq.offer(new int[]{0,2});
        pq.offer(new int[]{1,2});
        pq.offer(new int[]{5,3});
        pq.offer(new int[]{6,4});
        
        n = pq.size();
        
        // funtion calling
        int[] cTime = complationTime(pq);
        int[] tATime = trunArroundTime(CT, AT);
        int[] wTime = waitingTime(TAT, AT);
        float awt = avgWaitingTime(WT);
        int mwt = maxWaitingTime(WT);
        
        System.out.println("CT"+" "+"TAT"+" "+"WT");
        for(int i = 0; i < n; i++){
            System.out.println(cTime[i] +"  "+ tATime[i]+"  "+wTime[i]);
        }
        
        System.out.println("Avrage Waiting Time: "+ awt);
        System.out.println("Maximum Waiting Time: "+ mwt);
    }
}