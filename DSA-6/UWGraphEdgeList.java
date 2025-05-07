import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

interface InnerUWGraph {
    void addEdge(int s, int d, int wt);
    boolean isConnected();
    List<Integer> reachable(int source);
    void mst();
    int shortestPath(int source, int destination);    
}

class Edge{
    int source;
    int destination;
    int weight;
    Edge(int source, int destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph implements InnerUWGraph{
    private ArrayList<Edge> edgeList;
    private ArrayList<Integer> reachableList;
    private int[] distance ;
    private int size;
    private int minCost;

    Graph(int v){
        this.size = v;
        edgeList = new ArrayList<>();
        reachableList = new ArrayList<>();
        distance = new int[size];
        this.minCost = 0;
    }

    @Override
    public void addEdge(int source, int destination, int weight){
        edgeList.add(new Edge(source, destination, weight));
    }
    
    private void dfs(int cur, boolean[] visted){
        visted[cur] = true;
        reachableList.add(cur);

        for(Edge edge: edgeList){
            int neighbor = edge.destination;
            if(edge.source == cur && !visted[neighbor]){
                dfs(neighbor, visted);
            }
        }
    }

    @Override
    public boolean isConnected() {
        boolean[] visted = new boolean[size];
        dfs(0, visted);

        boolean connected = false;
        for(int i = 0; i < size; i++){
            if(!visted[i]){
                return connected;
            }
        }

        connected = true;
        return connected;
    }

    @Override
    public ArrayList<Integer> reachable(int source) {
        reachableList.clear();
        boolean[] visted = new boolean[size];
        dfs(source, visted);
        return reachableList;
    }
    private int prism(){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] visted = new boolean[size];

        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair curPair = pq.poll();
            
            if(!visted[curPair.source]){
                visted[curPair.source] = true;
                minCost += curPair.wt;

                for(int idx = 0; idx < edgeList.size(); idx++){
                    Edge edge = edgeList.get(idx);

                    if(edge.source == curPair.source && !visted[edge.destination]){
                        pq.add(new Pair(edge.destination, edge.weight));
                    }else if(edge.destination == curPair.source && !visted[edge.source]){
                        pq.add(new Pair(edge.source, edge.weight));
                    }
                }
            }
        }

        return minCost;
    }

    @Override
    public void mst() {
       int cost = prism();
       System.out.println(cost);
    }

    public static class Pair implements Comparable<Pair>{
        int source;
        int wt;
        Pair(int source, int wt){
            this.source = source;
            this.wt = wt;
        }

        @Override
        public int compareTo(Pair p) {
            return this.wt - p.wt;
        }

    }

    private void Dijkstra(int source, int destination) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] visted = new boolean[size];

        for(int i = 0; i < size; i++){
            if(i != source){
                distance[i] = Integer.MAX_VALUE;
            }
        }

        pq.offer(new Pair(source, 0));
        while(!pq.isEmpty()){
            Pair cur = pq.poll();
            
            if(!visted[cur.source]){
                visted[cur.source] = true;

                for(int idx = 0; idx < edgeList.size(); idx++){
                    if(cur.source == edgeList.get(idx).source){
                        Edge edge = edgeList.get(idx);
                        int u = edge.source;
                        int v = edge.destination;
                    
                        // relexation codtion 
                        if(distance[u] + edge.weight  < distance[v]){
                            distance[v] = distance[u] + edge.weight;
                            pq.add(new Pair(v, distance[v]));
                        }   
                    } else if(cur.source == edgeList.get(idx).destination){
                        Edge edge = edgeList.get(idx);
                        int u = edge.destination;
                        int v = edge.source;
                        
                        // relexation codtion 
                        if(distance[u] + edge.weight  < distance[v]){
                            distance[v] = distance[u] + edge.weight;
                            pq.add(new Pair(v, distance[v]));
                        }   
                    }
                }
            }
        }
        // for(int i = 0; i < size; i++){
        //     System.out.print(distance[i]+" ");
        // }
    }

    @Override
    public int shortestPath(int source, int destination) {
        Dijkstra(source, destination);
        int path = distance[destination];
        return path;
    }

    public void printGraph(){
        for(Edge edge : edgeList){
            System.out.println(edge.source+" "+edge.destination);
        }
    }
}

public class UWGraphEdgeList {
    public static void main(String[] args) {
        int v = 5;
        Graph graph = new Graph(v);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 2);
        
        graph.printGraph();
        System.out.println(graph.isConnected());
        System.out.println(graph.shortestPath(2, 4));
        graph.mst();
    }
}
