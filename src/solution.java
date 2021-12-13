import java.util.ArrayList;
import java.util.Map;

public class solution {
    Map<String, ArrayList<String>> adjacencyList;
    ArrayList<ArrayList<String>> paths;

    public solution(Map<String, ArrayList<String>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.paths         = new ArrayList<>();
    }

    public void solve() {
        ArrayList<String> path = new ArrayList<>();
        path.add("start");
        this.solveRec("start", path);
        this.dumpSolution();
    }

    private void dumpSolution() {
        System.out.println("total Paths: " + paths.size());

        for (ArrayList<String> p : this.paths) {
            for (String node : p) {
                if (!node.equals("end")) {
                    System.out.print(node + ",");
                } else {
                    System.out.println(node);
                }
            }
        }
    }

    private boolean isValidPath(String node, ArrayList<String> path) {
        boolean isLowerCase = true;

        for (char c : node.toCharArray()) {
            if (Character.isUpperCase(c)) { isLowerCase = false; }
        }

        return !((isLowerCase) && (path.contains(node)));
    }

    private void solveRec(String node, ArrayList<String> path) {
        if (node.equals("end")) {
            this.paths.add(path);
        }  else {
            for (String neighbor : this.adjacencyList.get(node)) {
                if ((!neighbor.equals("start")) && (isValidPath(neighbor, path))) {
                    ArrayList<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    this.solveRec(neighbor, newPath);
                }
            }
        }
    }
}