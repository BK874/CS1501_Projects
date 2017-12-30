/**
 * @author Brian Knotten
 * @version 1.0
 * CS 1501
 * Homework 4
 *
 * In KnapsackSolver.java, fill in the buildTable method
 * and the solve method as specified in the comments
 */

public class KnapsackSolver {

    // Utility array to keep track of the orders for use in Problem 2
    private static Order orderTable[][];

    public static int[][] buildTable(Order[] orders, int costLimit, int timeLimit){
	// Problem #1
	// Fill in this method to create a (costLimit + 1) by (timeLimit + 1) table
	// that for each (i, j) stores the maximum number of cookies that can be
	// produced with cost at most i in time at most j.

	// Initialize the utility array
	orderTable = new Order[costLimit+1][timeLimit+1];

	// Iterate through increasing cost and time constraints finding the largest
	// amount of cookies that can be produced. Based on the formula:
	// table[i][j] = max(table[i-order[k].costLimit][j-order[k].timeLimit] + v_k)
	int table[][] = new int[costLimit+1][timeLimit+1];
	for(int i = 0; i <= costLimit; i++){
	    for(int j = 0; j <= timeLimit; j++){
		int currentVal = 0;
		Order currentOrder = null;
		int temp;
		boolean test = false;
		for(int k = 0; k < orders.length; k++){
		    if(i >= orders[k].cost && j >= orders[k].time) {
			temp = table[i - orders[k].cost][j - orders[k].time] +
			    orders[k].numberOfCookies;
			if(temp > currentVal){
			    currentVal = temp;
			    currentOrder = orders[k];
			}
		    }
		    table[i][j] = currentVal;
		    orderTable[i][j] = currentOrder;
		}
	    }
	}
	return table;
    }
    

    public static Multiset solve(Order[] orders, int costLimit, int timeLimit){
	// Problem #2
	// Fill in this method to create a multiset that represents a combination of
	// cookie choices that maximizes the number of cookies with cost at most 
	// costLimit in time at most timeLimit.  Note: You can call buildTable as
	// a subroutine.

	// Build the table and utility array
	int table[][] = buildTable(orders, costLimit, timeLimit);

	// Initialize the Multiset to store the cookie order and the starting location
	Multiset set = new Multiset();
	int currentCost = costLimit;
	int currentTime = timeLimit;
	Order currentOrder = orderTable[currentCost][currentTime];
	
	// Iterate through the utility array, adding the order at each location
	// to the multiset before "backtracking" by subtracting that order's
	// cost and time from the order's indices and repeating at the new location.
	while(currentOrder != null){
	    set.add(currentOrder);
	    currentCost = currentCost - currentOrder.cost;
	    currentTime = currentTime - currentOrder.time;
	    currentOrder = orderTable[currentCost][currentTime];
	}
	// Return the final result
	return set;
    }
}
