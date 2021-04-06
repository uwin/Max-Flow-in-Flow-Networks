import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Flow {
    private final int V;
    private LinkedList[] adj;

    public Flow(int V)
    {
        this.V = V;
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++){
            adj[v] = new LinkedList<Edge>();
        }
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public LinkedList<Edge>[] getGraph() {
        return adj;
    }

    public void addEdge(int from, int to, long capacity)
    {
        Edge e = new Edge(from,to,capacity);
        adj[from].add(e);
        adj[to].add(e);
    }

    public static ArrayList<String> loadBenchmark(String fileName) {
        try
        {
            File file=new File("G:\\My Drive\\iit\\lvl05\\SEM2\\Algo\\CW\\code\\src\\Some input files for testing\\"+fileName);    //creates a new file instance
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;

            ArrayList <String> values = new ArrayList<>();

            while((line=br.readLine())!=null)
            {
                values.add(line.trim());

            }
            fr.close();
            return values;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "V=" + V +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }

    public static void main(String[] args) {
        ArrayList<String> benchmark = loadBenchmark("ladder_1.txt");
        int n = Integer.parseInt(benchmark.get(0));
        benchmark.remove(0);

        System.out.println("benchmark = " + benchmark);

        int s = 4;
        int t = 7;


        Flow test = new Flow(n);

        for (String line : benchmark){
            String[] arrayString = line.split(" ");
            int StartNode = Integer.parseInt(arrayString[0]);
            int EndNode = Integer.parseInt(arrayString[1]);
            int capacity = Integer.parseInt(arrayString[2]);
            test.addEdge(StartNode,EndNode,capacity);
        }

        LinkedList<Edge>[] resultGraph = test.getGraph();

        for (int i = 0; i <n ; i++) {
            LinkedList<Edge> list = resultGraph[i];
            for (int j = 0; j <list.size() ; j++) {
                System.out.println(i + " > " +
                        list.get(j).to() + " Capacity | " +  list.get(j).capacity());
            }
        }

        FordFulkerson rr = new FordFulkerson(test,s,t);


        System.out.println("out "+rr.value());
    }
}