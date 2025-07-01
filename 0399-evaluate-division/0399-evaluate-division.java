import java.util.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(var1, new HashMap<>());
            graph.putIfAbsent(var2, new HashMap<>());

            graph.get(var1).put(var2, value);
            graph.get(var2).put(var1, 1.0 / value);
        }

        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            if (!graph.containsKey(var1) || !graph.containsKey(var2)) {
                results[i] = -1.0;
            } else if (var1.equals(var2)) {
                results[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(graph, var1, var2, 1.0, visited);
            }
        }
        return results;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String currentVar, String targetVar, double product, Set<String> visited) {
        visited.add(currentVar);

        if (currentVar.equals(targetVar)) {
            return product;
        }

        if (graph.containsKey(currentVar)) {
            for (Map.Entry<String, Double> neighbor : graph.get(currentVar).entrySet()) {
                String nextVar = neighbor.getKey();
                double value = neighbor.getValue();

                if (!visited.contains(nextVar)) {
                    double result = dfs(graph, nextVar, targetVar, product * value, visited);
                    if (result != -1.0) {
                        return result;
                    }
                }
            }
        }
        return -1.0;
    }
}