import java.util.Scanner;
import java.util.PriorityQueue;

class FCFS {

    // create a array
    static int[] complationTimeArray;
    static int[] turnArroundTimeArray;
    static int[] arrivalTimeArray;
    static int[] burstTimeArray;
    static int[] waitingTimeArray;

    /**
     * complation time method
     * 
     * @param accept priortyqueue a FIFO queue, the first tasks added are the first
     *               retrieved.
     * @return the completion time array .
     */
    static int[] complationTimeMethod(PriorityQueue<int[]> pq) {
        complationTimeArray = new int[n];
        turnArroundTimeArray = new int[n];
        arrivalTimeArray = new int[n];
        int i = 0;

        while (!pq.isEmpty()) {
            int[] pair = pq.poll();

            int arrival = pair[0];
            arrivalTimeArray[i] = arrival;
            int burst = pair[1];
            turnArroundTimeArray[i] = burst;

            if (i == 0) {
                complationTimeArray[i] = arrival + burst;
            } else if (complationTimeArray[i - 1] >= arrival) {
                complationTimeArray[i] = complationTimeArray[i - 1] + burst;
            } else {
                complationTimeArray[i] = arrival + burst;
            }
            i++;
        }

        return complationTimeArray;
    }

    /**
     * turn arround time = complation time - arrival time
     * @param the first array
     * @param the second array
     * @return turn arround time in array format.
     */

    static int[] trunArroundTimeMethod(int[] CT, int[] AT) {
        turnArroundTimeArray = new int[n];

        for (int i = 0; i < n; i++) {
            turnArroundTimeArray[i] = CT[i] - AT[i];
        }

        return turnArroundTimeArray;
    }

    /**
     * Waiting time = turn Around time - burst time
     * @param the first array turn arround array
     * @param the second array arrival time
     * @return waiting time in array format.
     */
    static int[] waitingTimeMethod(int[] TAT, int[] AT) {
        waitingTimeArray = new int[n];
        
        for (int i = 0; i < n; i++) {
            waitingTimeArray[i] = TAT[i] - turnArroundTimeArray[i];
        }
        return waitingTimeArray;
    }

    /**
     * turn arround time = complation time - arrival time
     * @param waiting time in array format
     * @return avrage waiting time.
     */
    static float avgWaitingTimeMethod(int[] waitingTimeArray) {
        int totalWaitingTime = 0;

        for (int waitTime : waitingTimeArray) {
            totalWaitingTime += waitTime;
        }

        float avrageWaitingTime = totalWaitingTime / n;
        return avrageWaitingTime;
    }

    /**
     * maximum waiting time 
     * @param waiting time array accept 
     * @return max waiting time.
     */
    static int maxWaitingTimeMethod(int[] waitingTimeArray) {
        int maxWaitingTime = 0;

        for (int waitTime : waitingTimeArray) {
            if (waitTime > maxWaitingTime){
                maxWaitingTime = waitTime;
            }
        }

        return maxWaitingTime;
    }

    // size of priority queue
    static int n;

    // main method
    public static void main(String[] args) {

        // use priority queue is use
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[] { 0, 2 });
        pq.offer(new int[] { 1, 2 });
        pq.offer(new int[] { 5, 3 });
        pq.offer(new int[] { 6, 4 });

        n = pq.size();

        // funtion calling
        int[] completionTimeArray = complationTimeMethod(pq);
        int[] turnArroundTimeArray = trunArroundTimeMethod(complationTimeArray, arrivalTimeArray);
        int[] waitingTimeArray = waitingTimeMethod(turnArroundTimeArray, arrivalTimeArray);
        float avgWaitingTime = avgWaitingTimeMethod(waitingTimeArray);
        int maxWaitingTime = maxWaitingTimeMethod(waitingTimeArray);

        System.out.println("CT" + " " + "TAT" + " " + "WT");
        for (int i = 0; i < n; i++) {
            System.out.println(completionTimeArray[i] + "  " + turnArroundTimeArray[i] + "  " + waitingTimeArray[i]);
        }

        System.out.println("Avrage Waiting Time: " + avgWaitingTime);
        System.out.println("Maximum Waiting Time: " + maxWaitingTime);
    }
}