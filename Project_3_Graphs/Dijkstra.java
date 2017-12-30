/**
 * @author Brian Knotten
 * @version 1.0
 * CS 1501
 * Homework 3
 *
 * In Dijkstra.java, fill in the getDestinationSet method
 * as specified in the comments
 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    // Sets of settled and unsettled vertices and a map containing the current
    // lowest cost to reach each location.
    private static Set<Location> settled = new HashSet<Location>();
    private static Set<Location> unSettled = new HashSet<Location>();
    private static Map<Location, Integer> distances = new HashMap<Location, Integer>();
    

	// Returns the set of destinations that can be reached without going over budget
	public static Set<Location> getDestinationSet(WeightedGraph graph, Location start, Integer budget){
	// Problem #2
	// Fill in this method to compute the set of all possible destinations 
	// that can be reached while staying within the travel budget. You must
	// use Dijkstra's algorithm to get full credit.  We encourage you to
	// implement the |V|^2 time version of Dijkstra's algorithm.  You are
	// free to add helper methods.

	    /* ******************************
	     * ************Note**************
	     * This code is a heavily modified 
	     * version  of the code given to
	     * us in recitation by TA Alireza
	     * ****************************** */
	    
	    Set<Location> reachable = new HashSet<Location>();
	    Location w;

	    // Set the distance to the starting location to 0 and
	    // add it to the set of unsettled locations
	    distances.put(start, 0);
	    unSettled.add(start);

	    // While there are unsettled vertices find the one with
	    // the lowest cost, settle it, and update its neighbors
	    // with a new cost
	    while(!unSettled.isEmpty()){
		Location minVertex = getMin(unSettled);
		settled.add(minVertex);
		unSettled.remove(minVertex);
		findMinDistances(graph, minVertex);
	    }

	    // Iterate through all setttle vertices (all vertices with a
	    // path from the starting vertex) and add them to the reachable
	    // set if their cost is within the budget.
	    Iterator<Location> iter = settled.iterator();
	    while(iter.hasNext()){
		w = iter.next();
		if(distances.get(w) <= budget){
		    reachable.add(w);
		}
	    }
	    
	    // This is the set of all reachable locations within budget.
	    return reachable;
	}

    // Updates the given vertex's neighbors with new costs
    private static void findMinDistances(WeightedGraph graph, Location vertex){
	Set<Location> neighbors = graph.getNeighbors(vertex);
	Iterator<Location> iter = neighbors.iterator();
	Location w;

	// For each neighbor of the vertex if its cost from the source is currently
	// more than the vertex's cost + the distance between the vertex and the
	// neighbor:
	while(iter.hasNext()){
	    w = iter.next();
	    if(getShortestDistance(vertex) + graph.getWeight(vertex, w) <
	       getShortestDistance(w)){
		// Update the neighbor's cost = vertex's cost + distance between
		// the vertex and the neighbor
		distances.put(w, getShortestDistance(vertex) +
			      graph.getWeight(vertex, w));

		// Add the neighbor to the unSettled location set
		unSettled.add(w);
	    }
	}
    }

    // Retrieves the min cost location from a given set of locations
    private static Location getMin(Set<Location> locations){
	Location min = null;
	Iterator<Location> iter = locations.iterator();
	Location w;

	while(iter.hasNext()){
	    w = iter.next();
	    if(min == null){
		min = w;
	    } else {
		if (getShortestDistance(w) < getShortestDistance(min)){
		    min = w;
		}
	    }
	}
	return min;
    }

    // Retrieves the current cost of each location
    private static int getShortestDistance(Location destination){
	Integer d = distances.get(destination);
	if(d == null){
	    return Integer.MAX_VALUE;
	} else {
	    return d;
	}
    }
}
