//// Package Name
//package com.company;
//
//// Imports
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.Queue;
//
//// Class green.MaxFlow
//@SuppressWarnings("ALL")
//public class green.MaxFlow {
//    // Instance Variables
//    private boolean[] marked; // if a node is visited set it to true
//    private Link[] linkTo; // Get all the links that has an augmenting path from source to sink
//    private long green.MaxFlow; // Get the max flow
//
//    // Get the maxflow
//    public green.MaxFlow(FlowNetwork flowNetwork, int source, int sink) {
//        // Check if this method return true
//        while (hasAugmentingPath(flowNetwork, source, sink)) {
//            String s = "";
//            long bottleNeck = Long.MAX_VALUE / 2; // Setting the bottleneck for this with the highest value possible
//            for (int n = sink; n != source; n = linkTo[n.getId()].getOtherNode(n)) { // Run for loop from sink to source till all the edges in the linkto list are got
//                bottleNeck = Math.min(bottleNeck, linkTo[n.getId()].getResidualCapacity(n)); // Replace the value to the minimum value, note bottleneck value gets updated after each iteration
//
//            }
//            for (int n = sink; n != source; n = linkTo[n.getId()].getOtherNode(n)) { // Run the for loop from sin to source again in order to add the residual flows to the original graph with bottleneck value, because there the same augmenting path applies to the original flownetwork
//                linkTo[n.getId()].addResidualFlow(n, bottleNeck); // Add the values to the linkto edges with the residual flow got from the bottleneck and update the original graph
//                s += linkTo[n.getId()].getTargetNode().getName() + " " + linkTo[n.getId()].getFromNode().getName() + " ";
//                //System.out.print(linkTo[n.getId()].getTargetNode().getName()+"--> "+linkTo[n.getId()].getFromNode().getName()+" ");
//
//                //System.out.println();
//
//            }
//            StringBuilder alphabet = new StringBuilder();
//
//            ArrayList<String> all = new ArrayList<>();
//
//            for (char alpha : s.toCharArray()) {
//                if (Character.toString(alpha).equals(" ")) {
//                    all.add(alphabet.toString());
//                    alphabet = new StringBuilder();
//                }
//                alphabet.append((alpha));
//
//            }
//
//            //reverse(all,all.size());
//            Collections.reverse(all);
//            for (String h : all) {
//                System.out.print(h + "->");
//            }
//            s = "";
//            System.out.println();
//            System.out.println("There is an Augmenting path ");
//            System.out.println("Maximum Flow that can go through the augmenting path is " + bottleNeck);
//
//
//            green.MaxFlow += bottleNeck;
//
//        }
//    }
//
//    // Return the maxflow
//    public long getMaxFlow() {
//        return green.MaxFlow;
//    }
//
//    // Find the maxflow using the Incut method
//    public boolean isInCut(int index) {
//        return marked[index];
//    }
//
//    // Check if there is an augmenting path
//    private boolean hasAugmentingPath(FlowNetwork flowNetwork, Node a, Node b) {
//        linkTo = new Link[flowNetwork.getNumOfNodes()]; // Initialize link to variable with the number of nodes from the graph
//        marked = new boolean[flowNetwork.getNumOfNodes()]; // Initialize marked variable with the number of nodes from the graph
//
//        // for bfs algorithm we use the queue data structure to add them
//        Queue<Node> queue = new LinkedList<>();
//        ((LinkedList<Node>) queue).add(a); // Add the source node to the queue
//        marked[a.getId()] = true; //mark the value of the source node as true
//
//        // While the queue is not empty
//        while (!queue.isEmpty()) {
//            Node n = queue.remove(); // Take out the first node from the queue
//            for (Link l : flowNetwork.getAdjacencyList(n)) { // Get all the links for this source node
//                Node other = l.getOtherNode(n); // Get the other node , in this case the target node because wwe are getting it from the source node
//                if (l.getResidualCapacity(other) > 0) { // If the capacity is greater than 0
//                    if (!marked[other.getId()]) { //If that node is not visited
//                        linkTo[other.getId()] = l; // store the index of it as 1
//                        marked[other.getId()] = true; // mark the nodes as visited
//                        ((LinkedList<Node>) queue).add(other); // add that node to queue
//                    }
//                }
//            }
//        }
//        return marked[b.getId()]; // If the queue is empty then return the sink node
//    }
//
//
//}
