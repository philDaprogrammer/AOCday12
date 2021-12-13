import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        parser parser = new parser();
        Map<String, ArrayList<String>> adjList = parser.getAdjList("input1.txt");
        solution sol = new solution(adjList);
        sol.solve();
    }
}