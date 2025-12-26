package dsa;

import java.util.*;

public class Graph {

    private Map<String, List<Edge>> adj = new HashMap<>();

    public void addStop(String stop) {
        adj.putIfAbsent(stop, new ArrayList<>());
    }

    // ✅ FIXED: ensure nodes exist before adding edge
    public void addRoute(String from, String to, int distance) {

        if (!adj.containsKey(from)) {
            addStop(from);
        }
        if (!adj.containsKey(to)) {
            addStop(to);
        }

        adj.get(from).add(new Edge(to, distance));
        adj.get(to).add(new Edge(from, distance));
    }

    public int shortestPath(String source, String destination) {

        // ✅ SAFETY CHECK
        if (!adj.containsKey(source) || !adj.containsKey(destination)) {
            return -1;
        }

        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));

        for (String stop : adj.keySet()) {
            dist.put(stop, Integer.MAX_VALUE);
        }

        dist.put(source, 0);
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // ✅ SAFETY CHECK
            if (!adj.containsKey(current.name)) continue;

            for (Edge edge : adj.get(current.name)) {
                int newDist = current.dist + edge.distance;

                if (newDist < dist.get(edge.dest)) {
                    dist.put(edge.dest, newDist);
                    pq.add(new Node(edge.dest, newDist));
                }
            }
        }

        return dist.get(destination);
    }
}
