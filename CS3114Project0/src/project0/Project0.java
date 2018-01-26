package project0;

import java.util.*;
import java.io.*;
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
		    errorCheck = list.addEdge(sc.nextInt(), sc.nextInt());
		    if(!errorCheck){
		        System.out.println("duplicate edge");
		        break;
		    }
		}
		int[] results;
		for(int i=0; i < numberQueries; i++){
		    results = list.getAdjacentVertices(sc.nextInt());
		    for(int n= 0; n < results.length; n++){
		        System.out.print(n + " ");
		    }
		    System.out.println("");
		}
	}
}
