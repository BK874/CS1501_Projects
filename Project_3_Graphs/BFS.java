/**
 * @author Brian Knotten
 * @version 1.0
 * CS 1501
 * Homework 3
 *
 * In BFS.java, fill in the getReachableSet method
 * as specified in the comments
 */


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    
    static Queue<Location> vertices = new LinkedList<Location>();
    static Set<Location> visited = new HashSet<Location>();
    static Set<Location> neighbors;
    static Iterator<Location> iter;
    static Location v, w;    
    
    // Returns the set of reachable locations using breadth first search
    public static Set<Location> getReachableSet(WeightedGraph graph, Location start){
	// Problem #1
	// Fill in this method to compute the set of all possible reachable 
	// locations (ignoring costs and budget).  You must use Breadth
	// First Search to get full credit.

	// Add the starting vertex to the queue and set of visited locations.
	vertices.add(start);
	visited.add(start);

	// Iterate through the queue one vertex at time.
	// Retrieve the neighbors of the current vertex.
	while(!vertices.isEmpty()){
	    v = vertices.remove();
	    neighbors = graph.getNeighbors(v);
	    iter = neighbors.iterator();

	    // Iterate through the current vertex's neighbors.
	    // If the neighbor hasn't been visited, add it to the
	    // queue and set of visited vertices.
	    while(iter.hasNext()){
		w = iter.next();
		if (!visited.contains(w)){
		    vertices.add(w);
		    visited.add(w);
		}
	    }
	}

	// Return the set of reachable vertices from the starting vertex.
	return visited;
	
    }

}
