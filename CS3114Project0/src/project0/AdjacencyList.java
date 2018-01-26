package project0;

import java.util.ArrayList;

/**
 * @author Dylan Michaels(dtm1015), Lance Miller(lancem3)
 * @version Jan 26, 2018
 */
public class AdjacencyList {
    private ArrayList<Integer>[] edges;
    /**
     * 
     * @param length
     */
    public AdjacencyList(int length){
        edges = new ArrayList[length];
    }
    /**
     * 
     * @param vertex1
     * @param vertex2
     * @return
     */
    public boolean addEdge(int vertex1, int vertex2){
        if(edges[vertex1].contains(vertex2) || edges[vertex2].contains(vertex1)){
            return false;
        }
        edges[vertex1].add(vertex2);
        edges[vertex2].add(vertex1);
        return true;
    }
    /**
     * 
     * @return
     */
    public int[] getAdjacentVertices(int vertex){  
        int[] vertices = new int[edges[vertex].size()];
        for(int i = 0; i < vertices.length; i++){
            vertices[i] = edges[vertex].get(i);
        }
        return vertices;
    }
}