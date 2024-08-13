// https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/

//Solution: Union Find

// Complexity: 
// Time: O(n)
// Space: O(n)

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int requireEdge = 0;
        UnionFind bobGraph = new UnionFind(n);
        UnionFind aliceGraph = new UnionFind(n);

        for (int[] edge : edges) {
            if (edge[0] != 3) continue;
            int u = edge[1];
            int v = edge[2];
            boolean flag = false;

           
            if (!bobGraph.connected(u, v)) {
                bobGraph.union(u, v);
                if (flag == false) {
                    flag = true;
                    requireEdge++;
                }
            } 
            if (!aliceGraph.connected(u, v)) {
                aliceGraph.union(u, v);
                if (flag == false) requireEdge++;
            }
        }

        

        for (int[] edge : edges) {
            if (edge[0] == 3) continue;
            int type = edge[0];
            UnionFind graph = type == 1 ? bobGraph : aliceGraph; 
            int u = edge[1];
            int v = edge[2];

            if (!graph.connected(u, v)) {
                graph.union(u, v);
                requireEdge++;
            }
        }

        if (bobGraph.fullyConnected() && aliceGraph.fullyConnected()) {
            return edges.length - requireEdge;
        }

        return -1;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int n;

        UnionFind(int n) {
            parent = new int[n+1];
            rank = new int[n+1];
            this.n = n;
            Arrays.fill(rank, 1);
            
            for (int i = 0 ; i < n + 1; i++) {
                parent[i] = i;
            }
        }

        int findRoot(int u) {
            if (parent[u] != u) {
                parent[u] = findRoot(parent[u]);
            }

            return parent[u];
        }

        void union(int u, int v) {
            int rootU = findRoot(u);
            int rootV = findRoot(v);
            if (rootU != rootV) {
                if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }

        boolean connected(int u,int v) {
            return findRoot(u) == findRoot(v);
        }

        boolean fullyConnected() {
            int tempRoot = -1;
            boolean result = true;
            for (int i = 1; i < n+1; i++) {
                if (tempRoot == -1) {
                    tempRoot = findRoot(i);
                } else if (tempRoot != -1 && tempRoot != findRoot(i)) {
                    result = false;
                    break;
                }
            }

            return result;
        }
    }
}