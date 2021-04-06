public class Edge{
    private final int from, to;
    private final double capacity; // capacity
    private double flow;

    public Edge(int from, int to, double capacity)
    {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }
    public int from() { return from; }
    public int to() { return to; }
    public double capacity() { return capacity; }
    public double flow() { return flow; }

    public int other(int vertex)
    {
        if (vertex == from) return to;
        else if (vertex == to) return from;
        else throw new IllegalArgumentException();
    }
    public double residualCapacityTo(int vertex)
    {
        if (vertex == from) return flow;
        else if (vertex == to) return capacity - flow;
        else throw new IllegalArgumentException();
    }

    public void addResidualFlowTo(int vertex, double delta)
    {
        if (vertex == to) flow -= delta;
        else if (vertex == from) flow += delta;
        else throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", capacity=" + capacity +
                ", flow=" + flow +
                '}';
    }
}