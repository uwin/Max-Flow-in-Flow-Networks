//package cw;
//
//import java.util.LinkedList;
//
//public class algo {
//    int fordFulkerson(int graph[][],int nodes)
//    {
//        int u, v;
//        // Create a residual graph and fill the residual graph
//        // with given capacities in the original graph as
//        // residual capacities in residual graph
//
//        // Residual graph where rGraph[i][j] indicates
//        // residual capacity of edge from i to j (if there
//        // is an edge. If rGraph[i][j] is 0, then there is
//        // not)
//        int rGraph[][] = new int[nodes][nodes];
//
//        for (u = 0; u < nodes; u++)
//            for (v = 0; v < nodes; v++)
//                rGraph[u][v] = graph[u][v];
//
//        // This array is filled by BFS and to store path
//        int parent[] = new int[nodes];
//
//        int max_flow = 0; // There is no flow initially
//
//        // Augment the flow while there is path from source to sink
//        while (bfs(rGraph, source, sink, parent,nodes))
//        {
//            // Find minimum residual capacity of the nodes along the path filled by BFS. Or we can say
//            // find the maximum flow through the path found.
//            int path_flow = Integer.MAX_VALUE;
//            for (v=sink; v!=source; v=parent[v])
//            {
//                u = parent[v];
//                path_flow = Math.min(path_flow, rGraph[u][v]);
//            }
//
//            // update residual capacities of the edges and
//            // reverse edges along the path
//            for (v=sink; v != source; v=parent[v])
//            {
//                u = parent[v];
//                rGraph[u][v] -= path_flow;
//                rGraph[v][u] += path_flow;
//                flow.add(u+"-->"+v+" ");
//            }
//
//            // Add path flow to overall flow
//            max_flow += path_flow;
//        }
//
//        // Return the overall flow
//        return max_flow;
//    }
//    boolean bfs(int rGraph[][], int s, int t, int parent[],int nodes)
//    {
//        // Create a visited array and mark all vertices as not
//        // visited
//        boolean visited[] = new boolean[nodes];
//        for(int i=0; i<nodes; ++i)
//            visited[i]=false;
//
//        // Create a queue, enqueue source vertex and mark
//        // source vertex as visited
//        LinkedList<Integer> queue = new LinkedList<Integer>();
//        queue.add(s);
//        visited[s] = true;
//        parent[s]=-1;
//
//        // Standard BFS Loop
//        while (queue.size()!=0)
//        {
//            int u = queue.poll();
//
//            for (int v=0; v<nodes; v++)
//            {
//                if (visited[v]==false && rGraph[u][v] > 0)
//                {
//                    queue.add(v);
//                    parent[v] = u;
//                    visited[v] = true;
//                }
//            }
//        }
//
//        // If we reached sink in BFS starting from source, then
//        // return true, else false
//        return (visited[t] == true);
//    }
//}
