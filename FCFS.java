import java.util.Scanner;
import java.util.PriorityQueue;

class FCFS {

    // create a array
    static int[] completionTimeArray;
    static int[] turnAroundTimeArray;
    static int[] arrivalTimeArray;
    static int[] burstTimeArray;
    static int[] waitingTimeArray;

    /**
     * completion time method
     * @param accept priortyqueue a FIFO queue, the first tasks added are the first
     *               retrieved.
     * @return the completion time array .
     */
    static int[] completionTimeMethod(PriorityQueue<int[]> pq) {
        completionTimeArray = new int[n];
        turnAroundTimeArray = new int[n];
        arrivalTimeArray = new int[n];
        int i = 0;

        while (!pq.isEmpty()) {
            int[] pair = pq.poll();

            int arrival = pair[0];
            arrivalTimeArray[i] = arrival;
            int burst = pair[1];
            turnAroundTimeArray[i] = burst;

            if (i == 0) {
                completionTimeArray[i] = arrival + burst;
            } else if (completionTimeArray[i - 1] >= arrival) {
                completionTimeArray[i] = completionTimeArray[i - 1] + burst;
            } else {
                completionTimeArray[i] = arrival + burst;
            }
            i++;
        }

        return completionTimeArray;
    }

    /**
     * turn around time = completion time - arrival time
     * @param the first array
     * @param the second array
     * @return turn arround time in array format.
     */

    static int[] turnAroundTimeMethod(int[] CT, int[] AT) {
        turnAroundTimeArray = new int[n];

        for (int i = 0; i < n; i++) {
            turnAroundTimeArray[i] = CT[i] - AT[i];
        }

        return turnAroundTimeArray;
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
            waitingTimeArray[i] = TAT[i] - turnAroundTimeArray[i];
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
        int[] completionTimeArray = completionTimeMethod(pq);
        int[] turnArroundTimeArray = turnAroundTimeMethod(completionTimeArray, arrivalTimeArray);
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