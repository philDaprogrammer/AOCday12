import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


public class Solution {
    private final Map<String, ArrayList<String>> adjList;
    private int paths;

    public Solution(String filename) { this.adjList = parse(filename); }

    private Map<String, ArrayList<String>> parse(String filename) {
        Map<String, ArrayList<String>> adjList = new HashMap<>();

        try {
            File f            = new File(filename);
            FileReader fr     = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] edge = line.split("-");
                addEdge(adjList, edge[0], edge[1]);
                addEdge(adjList, edge[1], edge[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adjList;
    }

    private void addEdge(Map<String, ArrayList<String>> adjList, String v1, String v2) {
        ArrayList<String> neighbors = new ArrayList<>();

        if (adjList.get(v1) == null) {
            neighbors.add(v2);
            adjList.put(v1, neighbors);
        } else {
            adjList.get(v1).add(v2);
        }
    }

    private int numInstances(ArrayList<String> path, String vertex) {
        int numInstances = 0;

        for (String v : path) {
            if (v.equals(vertex)) { numInstances++; }
        }

        return numInstances;
    }

    private void solveP1Rec(ArrayList<String> path, String vertex) {
        if (vertex.equals("end")) {
            this.paths++;
        } else {
            for (String neighbor : this.adjList.get(vertex)) {
                ArrayList<String> newPath = (ArrayList<String>) path.clone();
                newPath.add(neighbor);

                if (!neighbor.equals("start")) {
                    if ((neighbor.equals(neighbor.toLowerCase())) && (numInstances(newPath, neighbor) == 1)) {
                        solveP1Rec(newPath, neighbor);
                    } else if (neighbor.equals(neighbor.toUpperCase())) {
                        solveP1Rec(newPath, neighbor);
                    }
                }
            }
        }
    }

    private void solveP2Rec(ArrayList<String> path, String vertex, boolean has2) {
         if (vertex.equals("end")) {
             this.paths++;
         } else {
             for (String neighbor : this.adjList.get(vertex)) {
                 ArrayList<String> newPath = (ArrayList<String>) path.clone();
                 newPath.add(neighbor);

                 if (!neighbor.equals("start")) {
                     if ((neighbor.equals(neighbor.toLowerCase())) && (numInstances(newPath, neighbor) == 1)) {
                         solveP2Rec(newPath, neighbor, has2);
                     } else if (((!has2)) && (neighbor.equals(neighbor.toLowerCase())) && (numInstances(newPath, neighbor) == 2)) {
                         solveP2Rec(newPath, neighbor, true);
                     } else if (neighbor.equals(neighbor.toUpperCase())) {
                         solveP2Rec(newPath, neighbor, has2);
                     }
                 }
             }
         }
    }

    public void solveP1() {
        this.paths = 0;
        solveP1Rec(new ArrayList<>(Collections.singletonList("start")), "start");
        System.out.println("Total paths: " + this.paths);
    }

    public void solveP2() {
        this.paths = 0;
        solveP2Rec(new ArrayList<>(Collections.singletonList("start")), "start", false);
        System.out.println("Total paths: " + this.paths);
    }
}