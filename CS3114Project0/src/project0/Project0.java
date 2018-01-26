package project0;

import java.util.*;
import java.io.*;
import java.util.ArrayList;
//Add imports as needed

public class Project0 {	
	public static void main(String[] args) {
		//Insert your code here
	    Scanner sc = new Scanner(System.in);
		int numberVerticies = sc.nextInt();
		int numberEdges = sc.nextInt();
		int numberQueries = sc.nextInt();
		AdjacencyList list = new AdjacencyList(numberVerticies);
		boolean errorCheck = true;
		for(int i=0; i < numberEdges; i++){
		    errorCheck = list.addEdge(sc.nextInt()-1, sc.nextInt()-1);
		    if(!errorCheck){
		        System.out.println("duplicate edge");
		        break;
		    }
		}
		int[] results;
		for(int i=0; i < numberQueries; i++){
		    results = list.getAdjacentVertices(sc.nextInt()-1);
		    for(int n= 0; n < results.length; n++){
		        System.out.print(results[n]+1 + " ");
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
	    public AdjacencyList(int length){
	        edges = new ArrayList[length];
	        for(int i = 0; i < edges.length; i++){
	            edges[i] = new ArrayList<Integer>();
	        }
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
	}
