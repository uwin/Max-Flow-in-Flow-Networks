// Java program for implementation of Ford Fulkerson algorithm
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;
import java.util.LinkedList;

class MaxFlow
{
    static int[][] graph;
    static int snodes;
    static int sink;
    static int source;
    static int edges;
    static long start; //starting time
    static ArrayList<String> flow = new ArrayList<String>();
    /* Returns true if there is a path from source 's' to sink
    't' in residual graph. Also fills parent[] to store the
    path */
    boolean bfs(int rGraph[][], int s, int t, int parent[],int nodes)
    {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[nodes];
        for(int i=0; i<nodes; ++i)
            visited[i]=false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<nodes; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }
    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][],int nodes)
    {
        int u, v;
        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[nodes][nodes];

        for (u = 0; u < nodes; u++)
            for (v = 0; v < nodes; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[nodes];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while there is path from source to sink
        while (bfs(rGraph, source, sink, parent,nodes))
        {
            // Find minimum residual capacity of the nodes along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v=sink; v!=source; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=sink; v != source; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
                flow.add(u+"-->"+v+" ");
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }
    void NewGraph(int nodes,Scanner input,MaxFlow m,int source,int sink){
        edges=0;
        graph = new int[nodes][nodes];
        Boolean exit=false;
        if(nodes>0){
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    graph[i][j]=0;
                }
            }
        }
        while(exit==false){
            System.out.println("Do you want to exit (true/false) : ");
            exit = input.nextBoolean();
            if(exit==true){
                start=System.currentTimeMillis();
                break;
            }else{
                System.out.print("Input node : ");
                int node = input.nextInt();
                System.out.print("The linked node : ");
                int lNode = input.nextInt();
                System.out.print("The capacity : ");
                int capacity = input.nextInt();
                edges++;
                if(source==0){
                    graph[node][lNode] = capacity;
                }else if(source==1) {
                    graph[node - 1][lNode - 1] = capacity;
                }
                System.out.println("------------------------------------------");
            }
        }
    }
    void AddLink(int nodes,Scanner input,MaxFlow m){
        Boolean exit=false;
        while(exit==false){
            System.out.println("Do you want to exit (true/false) : ");
            exit = input.nextBoolean();
            if(exit==true){
                start=System.currentTimeMillis();
                break;
            }else{
                System.out.print("Input node : ");
                int node = input.nextInt();
                System.out.print("The linked node : ");
                int lNode = input.nextInt();
                System.out.print("The capacity : ");
                int capacity = input.nextInt();
                edges++;
                if(source==0){
                    graph[node][lNode] = capacity;
                }else if(source==1) {
                    graph[node - 1][lNode - 1] = capacity;
                }
                System.out.println("------------------------------------------");
            }
        }
    }
    void DeleteLink(int nodes,Scanner input,MaxFlow m){
        Boolean exit=false;
        while(exit==false){
            System.out.println("Do you want to exit (true/false) : ");
            exit = input.nextBoolean();
            if(exit==true){
                start=System.currentTimeMillis();
                break;
            }else{
                System.out.print("Input node : ");
                int node = input.nextInt();
                System.out.print("The linked node : ");
                int lNode = input.nextInt();
                edges--;
                if(source==0){
                    graph[node][lNode] = 0;
                }else if(source==1) {
                    graph[node - 1][lNode - 1] = 0;
                }
                System.out.println("------------------------------------------");
            }
        }
    }
    void LoadingOtherGraphs(MaxFlow m,Scanner input){
        int count=0;
        String number="";
        String filename="";
        int count2=0;
        int col=0;
        edges=0;
        int choice=0;
        System.out.println("1. 6 nodes and 11 edges\n2. 12 nodes and 22 edges\n3. 24 nodes and 44 edges\n4. 48 nodes and 88 edges");
        choice=input.nextInt();
        switch (choice){
            case 1:
                filename="Network_1.txt";
                break;
            case 2:
                filename="Network_2.txt";
                break;
            case 3:
                filename="Network_3.txt";
                break;
            case 4:
                filename="Network_4.txt";
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        start=System.currentTimeMillis();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(count==0){
                    snodes= Integer.parseInt(data);
                }else if(count==1){
                    source=Integer.parseInt(data);
                }else if(count==2){
                    sink=Integer.parseInt(data);
                }else {
                    break;
                }
                count++;
            }
            graph = new int[snodes][snodes];
            if(snodes>0){
                for (int i = 0; i < snodes; i++) {
                    for (int j = 0; j < snodes; j++) {
                        graph[i][j]=0;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj2 = new File(filename);
            Scanner myReader2 = new Scanner(myObj2);
            count=0;
            while (myReader2.hasNextLine()) {
                String data2 = myReader2.nextLine();
                if(count>2){
                    number="";
                    while(data2.length()!=count2) {
                        if (data2.substring(count2, count2+1).equals(",")) {
                            graph[(count-3)][col]=Integer.parseInt(number);
                            if(Integer.parseInt(number)>0){
                                edges++;
                            }
                            col++;
                            number="";
                        }else{
                            number=number+data2.substring(count2,count2+1);
                        }
                        count2++;

                    }
                }
                col=0;
                count2=0;
                count++;
            }
            myReader2.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void Display(MaxFlow m){
        int count=0;
        System.out.println("------------- GRAPH VIEW -----------------");
        if(snodes>0){
            for (int i = 0; i < snodes; i++) {
                for (int j = 0; j < snodes; j++) {
                    System.out.print(graph[i][j]+" ");
                }
                System.out.println();
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Number of Edges : "+edges);
        System.out.println("Number of Nodes : "+snodes);
        System.out.println("The maximum possible flow is " + m.fordFulkerson(graph, snodes));
        long end=System.currentTimeMillis();
        System.out.println("Time taken = "+(end-start)+"ms");
        count=flow.size();
        System.out.println("------------- AUGMENTED PATH -------------");
        for(int i=0;i<flow.size();i++){
            count--;
            String val=flow.get(count);
            if(!(val.substring(0,1).equals(String.valueOf(source)))){
                System.out.print(val);
            }else if(i==0){
                System.out.print(val);
            }else{
                System.out.println();
                System.out.print(val);
            }
        }
        System.out.println();
        System.out.println("------------------------------------------");
    }
    // Driver program to test above functions
    public static void main (String[] args) throws java.lang.Exception
    {
        MaxFlow m = new MaxFlow();

        Scanner input = new Scanner(System.in);
        int choice=0;
        int nodes=0;

        while(choice!=5) {
            System.out.println("1. Input a new Graph\n2. Add a link\n3. Delete a link\n4. Loading other graphs\n5. exit");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    while(nodes<6) {
                        System.out.println("Input number of nodes");
                        nodes = input.nextInt();
                        if(nodes>=6){
                            System.out.print("Input the source : ");
                            source = input.nextInt();
                            System.out.print("Input the sink : ");
                            sink = input.nextInt();
                            snodes = nodes;
                            m.NewGraph(nodes, input, m, source, sink);
                        }else{
                            System.out.println("At least 6 nodes should be there ");
                            break;
                        }
                    }
                    m.Display(m);
                    break;
                case 2:
                    m.AddLink(snodes, input, m);
                    m.Display(m);
                    break;
                case 3:
                    m.DeleteLink(snodes,input,m);
                    m.Display(m);
                    break;
                case 4:
                    m.LoadingOtherGraphs(m,input);
                    m.Display(m);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }

    }
}
