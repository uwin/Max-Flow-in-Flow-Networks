package cw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Flow {
    static int[][] graph;
    static int snodes;
    static int sink;
    static int source;
    static int edges;
    static long start;
    static ArrayList<String> flow = new ArrayList<String>();

    public static void main (String[] args) throws java.lang.Exception {
        Flow m = new Flow();
        while (true) m.runalgo();
    }
    void runalgo(){
        Flow m = new Flow();
        Scanner input = new Scanner(System.in);
        String choice;
        System.out.println("1. Load graphs\n2. exit");
        choice = input.next();
        switch (choice) {
            case "1":
                m.LoadingOtherGraphs(m,input);
                m.Display(m);
                break;
            case "2":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input");
                runalgo();
                break;
        }
    }

    boolean bfs(int rGraph[][], int s, int t, int parent[],int nodes) {
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
        while (!queue.isEmpty())
        {
            int u = queue.poll();
            if (s==t) break;
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
    int fordFulkerson(int graph[][],int nodes) {

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
//            System.out.println("in ford while");
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

    void LoadingOtherGraphs(Flow m, Scanner input){
        int count=0;
        String filetype="";
        String filename="";
        edges=0;
        int choice=0;
//        String number="";
//        int count2=0;
//        int col=0;
        System.out.println("1. Bridge\n2. Ladder");
        choice=input.nextInt();
        switch (choice){
            case 1:
                filetype="bridge";
                break;
            case 2:
                filetype="ladder";
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        int choiceTwo=0;
        System.out.println("1. "+filetype+"_1"+"\n2. "+filetype+"_2"+"\n3. "+filetype+"_3"+"\n4. "+filetype+"_4"+"\n5. "+filetype+"_5"+"\n6. "+filetype+"_6"+"\n6. "+filetype+"_7"+"\n8. "+filetype+"_8"+"\n9. "+filetype+"_9");
        choiceTwo=input.nextInt();
        switch (choiceTwo){
            case 1:
                filename=filetype+"_1";
                break;
            case 2:
                filename=filetype+"_2";
                break;
            case 3:
                filename=filetype+"_3";
                break;
            case 4:
                filename=filetype+"_4";
                break;
            case 5:
                filename=filetype+"_5";
                break;
            case 6:
                filename=filetype+"_6";
                break;
            case 7:
                filename=filetype+"_7";
                break;
            case 8:
                filename=filetype+"_8";
                break;
            case 9:
                filename=filetype+"_9";
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }

        start=System.currentTimeMillis();

        try {
            File myObj = new File("G:\\My Drive\\iit\\lvl05\\SEM2\\Algo\\CW\\code\\src\\Some input files for testing\\"+filename+".txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            snodes= Integer.parseInt(data.trim());
            source= 0;
            sink=snodes-1;
            count++;

            graph = new int[snodes][snodes];
//            if(snodes>0){
//                for (int i = 0; i < snodes; i++) {
//                    for (int j = 0; j < snodes; j++) {
//                        graph[i][j]=0;
//                    }
//                }
//            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj2 = new File("G:\\My Drive\\iit\\lvl05\\SEM2\\Algo\\CW\\code\\src\\Some input files for testing\\"+filename+".txt");
            Scanner myReader2 = new Scanner(myObj2);
            count=0;
            while (myReader2.hasNextLine()) {
                String data2 = myReader2.nextLine();
                if(count>0){
                    String[] arrayString = data2.split(" ");
                    int StartNode = Integer.parseInt(arrayString[0]);
                    int EndNode = Integer.parseInt(arrayString[1]);
                    int capacity = Integer.parseInt(arrayString[2]);
                    edges++;
                    graph[StartNode][EndNode] = capacity;
                }
                count++;
            }
            myReader2.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void Display(Flow m){
        int count=0;
//        System.out.println("GRAPH VIEW ");
//        if(snodes>0){
//            for (int i = 0; i < snodes; i++) {
//                for (int j = 0; j < snodes; j++) {
//                    System.out.print(graph[i][j]+" ");
//                }
//                System.out.println();
//            }
//        }
        int maxflow=  m.fordFulkerson(graph, snodes);
        count=flow.size();
        System.out.println("AUGMENTED PATH");
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
        long end=System.currentTimeMillis();

        System.out.println("Max flow : " +maxflow);
        System.out.println("Edges : "+edges);
        System.out.println("Nodes : "+snodes);
        System.out.println();
        System.out.println("Time : "+(end-start)+"ms");

    }
}
