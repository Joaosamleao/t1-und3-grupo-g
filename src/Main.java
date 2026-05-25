import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge> {
        private final int v, w;
        private final double weight;

        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int either() { return v; }

        public int other(int vertex) {
            return vertex == v ? w : v;
        }

        @Override
        public int compareTo(Edge that) {
            return Double.compare(this.weight, that.weight);
        }
    }

    static class EdgeWeightedGraph {
        private final int V;
        private final List<Edge>[] adj;
        private final List<Edge> allEdges;

        @SuppressWarnings("unchecked")
        public EdgeWeightedGraph(int V) {
            this.V = V;
            this.adj = (List<Edge>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<>();
            }
            this.allEdges = new ArrayList<>();
        }

        public void addEdge(Edge e) {
            int v = e.either();
            int w = e.other(v);
            adj[v].add(e);
            adj[w].add(e);
            allEdges.add(e);
        }

        public List<Edge> edges() {
            return allEdges;
        }
    }

    static class UF {
        private final int[] parent;
        private final int[] rank;

        public UF(int N) {
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
        }
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public double distanceTo(Point that) {
            return Math.hypot(this.x - that.x, this.y - that.y);
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        String tStr = sc.next();
        if (tStr == null) return;

        int T = Integer.parseInt(tStr);

        for (int caseNum = 1; caseNum <= T; caseNum++) {
            int n = sc.nextInt();
            int r = sc.nextInt();

            Point[] cities = new Point[n];
            for (int i = 0; i < n; i++) {
                cities[i] = new Point(sc.nextInt(), sc.nextInt());
            }

            EdgeWeightedGraph G = new EdgeWeightedGraph(n);

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double dist = cities[i].distanceTo(cities[j]);
                    G.addEdge(new Edge(i, j, dist));
                }
            }

            List<Edge> edges = G.edges();
            Collections.sort(edges);

            UF uf = new UF(n);
            double roads = 0.0;
            double railroads = 0.0;
            int statesCount = n;

            for (Edge e : edges) {
                int v = e.either();
                int w = e.other(v);

                if (!uf.connected(v, w)) {
                    uf.union(v, w);

                    if (e.weight <= r) {
                        roads += e.weight;
                        statesCount--;
                    } else {
                        railroads += e.weight;
                    }
                }
            }

            long finalRoads = Math.round(roads);
            long finalRailroads = Math.round(railroads);

            System.out.println("Case #" + caseNum + ": " + statesCount + " " + finalRoads + " " + finalRailroads);
        }
    }
}