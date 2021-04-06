import java.util.*;

public class FordFulkerson
{
    private boolean[] marked; // true if s->v path in residual network
    private Edge[] edgeTo; // last edge on s->v path
    private double value; // value of flow
    private double bottleneck; // value of flow

    public FordFulkerson(Flow G, int s, int t)
    {
        value = 0.0;
        while (hasAugmentingPath(G, s, t))
        {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));

            for (int v = t; v != s; v = edgeTo[v].other(v)) edgeTo[v].addResidualFlowTo(v, bottle);
            value += bottle;
        }
    }
    private boolean hasAugmentingPath(Flow G, int s, int t)
    {
        edgeTo = new Edge[G.V()];
        marked = new boolean[G.V()];

        LinkedList<Integer> queue = new LinkedList<>() ;
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty())
        {
            int v = queue.poll();

            for (Edge e : G.adj(v))
            {
                int w = e.other(v);
                if (!marked[w] && (e.residualCapacityTo(w) > 0) ){
                    edgeTo[w] = e;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
        return marked[t];
    }

    public double value()
    { return value; }

    public boolean inCut(int v)
    { return marked[v]; }

    @Override
    public String toString() {
        return "FordFulkerson{" +
                "marked=" + Arrays.toString(marked) +
                ", edgeTo=" + Arrays.toString(edgeTo) +
                ", value=" + value +
                ", bottleneck=" + bottleneck +
                '}';
    }
}