import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class parser {
    private final Map<String, ArrayList<String>> adjacencyList = new HashMap<>();

    public Map<String, ArrayList<String>> getAdjList(String filename) {
        char[] contents   = this.getContents(filename).toCharArray();
        boolean parsingV1 = true ;
        StringBuilder v1  = new StringBuilder();
        StringBuilder v2  = new StringBuilder();

        for (char content : contents) {
            if (content == '\n') {
                this.addEdge(v1.toString(), v2.toString());
                parsingV1 = true;
                v1 = new StringBuilder();
                v2 = new StringBuilder();
            } else if (content == '-') {
                parsingV1 = false;
            } else {
                if (parsingV1) {
                    v1.append(content);
                } else {
                    v2.append(content);
                }
            }
        }

        return this.adjacencyList;
    }

    private void addEdge(String v1, String v2) {
        if (!this.adjacencyList.containsKey(v1)) {
            ArrayList<String> neighbors = new ArrayList<>();
            neighbors.add(v2);
            this.adjacencyList.put(v1, neighbors);
        } else {
            this.adjacencyList.get(v1).add(v2);
        }

        if (!this.adjacencyList.containsKey(v2)) {
             ArrayList<String> neighbors = new ArrayList<>();
             neighbors.add(v1);
             this.adjacencyList.put(v2, neighbors);
        } else {
            this.adjacencyList.get(v2).add(v1);
        }
    }

    private String getContents(String filename) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}