import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

interface weightedGraph {
    void addVertex(int vertex);
    void addEdge(int s, int d, int wt);
    boolean isConnected();
    List<Integer> reachable(int source);
    void mst();
    int shortestPath(int source, int destination);    
}

class Edge{
    int destination;
    int weight;
    Edge(int destination, int weight){
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph implements weightedGraph{
    private HashMap<Integer, ArrayList<Edge>> adjList;
    private ArrayList<Integer> reachableList;
    private int[] distance ;
    private int size;
    private int minCost;

    Graph(int v){
        this.size = v;
        adjList = new HashMap<>();
        reachableList = new ArrayList<>();
        distance = new int[size];
        this.minCost = 0;
    }

    @Override
    public void addVertex(int vertex){
        adjList.put(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(int source, int destination, int weight){        
        adjList.get(source).add(new Edge(destination, weight));
        adjList.get(destination).add(new Edge(source, weight));
    }
    
    private void dfs(int cur, boolean[] visted, HashMap<Integer, ArrayList<Edge>> adjList){
        visted[cur] = true;
        reachableList.add(cur);

        for(Edge edge: adjList.get(cur)){
            int neighbor = edge.destination;
            if(!visted[neighbor]){
                dfs(neighbor, visted, adjList);
            }
        }
    }

    @Override
    public boolean isConnected() {
        boolean[] visted = new boolean[size];
        dfs(0, visted, adjList);

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
        dfs(source, visted, adjList);
        return reachableList;
    }
    private int prism(){
        int minCost = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] visted = new boolean[size];

        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair curPair = pq.poll();
            if(!visted[curPair.source]){
                visted[curPair.source] = true;
                minCost += curPair.wt;

                for(int idx = 0; idx < adjList.get(curPair.source).size(); idx++){
                    Edge edge = adjList.get(curPair.source).get(idx);

                    if(!visted[edge.destination]){
                        pq.add(new Pair(edge.destination, edge.weight));
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

                for(int idx = 0; idx < adjList.get(cur.source).size(); idx++){
                    Edge edge = adjList.get(cur.source).get(idx);
                    int u = cur.source;
                    int v = edge.destination;
                    
                    // relexation codtion 
                    if(distance[u] + edge.weight  < distance[v]){
                        distance[v] = distance[u] + edge.weight;
                        pq.add(new Pair(v, distance[v]));
                    }
                }
            }
        }

    }

    @Override
    public int shortestPath(int source, int destination) {
        Dijkstra(source, destination);
        int path = distance[destination];
        return path;
    }

    public void printGraph()
    {
        for (HashMap.Entry<Integer, ArrayList<Edge> > entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Edge neighbor : entry.getValue()) {
                System.out.print(" ("+neighbor.destination + " , " + neighbor.weight+") ");
            }
            System.out.println();
        }
    }
}

public class UWGraph {
    public static void main(String[] args) {
        int v = 5;
        Graph graph = new Graph(v);

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 2);

        graph.printGraph();
        System.out.println(graph.isConnected());
        System.out.println(graph.shortestPath(3, 4));
        graph.mst();
    }
}
