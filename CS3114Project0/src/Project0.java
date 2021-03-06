
import java.util.*;
import java.io.*;
import java.util.ArrayList;
// Add imports as needed

public class Project0 {
    public static void main(String[] args) {
        // Insert your code here
        Scanner sc = new Scanner(System.in);
        int numberVerticies = sc.nextInt();
        int numberEdges = sc.nextInt();
        int numberQueries = sc.nextInt();
        AdjacencyList list = new AdjacencyList(numberVerticies);
        boolean errorCheck = true;
        for (int i = 0; i < numberEdges; i++) {
            errorCheck = list.addEdge(sc.nextInt(), sc.nextInt() );
            if (!errorCheck) {
                System.out.println("duplicate edge");
                break;
            }
        }
        int[] results;
        for (int i = 0; i < numberQueries; i++) {
            results = list.getAdjacentVertices(sc.nextInt());
            for (int n = 0; n < results.length; n++) {
                System.out.print(results[n]  + " ");
            }
            System.out.println("");
        }
    }


    private static class AdjacencyList {
        private ArrayList<Integer>[] edges;


        /**
         *
         * @param length
         */
        public AdjacencyList(int length) {
            edges = new ArrayList[length];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<Integer>();
            }
        }


        /**
         *
         * @param vertex1
         * @param vertex2
         * @return
         */
        public boolean addEdge(int vertex1, int vertex2) {
            if (edges[vertex1 - 1].contains(vertex2) || edges[vertex2 - 1].contains(
                vertex1)) {
                return false;
            }
            edges[vertex1 - 1].add(vertex2);
            edges[vertex2 - 1].add(vertex1);
            return true;
        }


        /**
         *
         * @return
         */
        public int[] getAdjacentVertices(int vertex) {
            int[] vertices;
            if (edges[vertex - 1].size() == 0) {
                vertices = new int[1];
                vertices[0] = -1;
            }
            else {
                vertices = new int[edges[vertex - 1].size()];
                for (int i = 0; i < vertices.length; i++) {
                    vertices[i] = edges[vertex - 1].get(i);
                }
            }
            return vertices;
        }
    }
}
