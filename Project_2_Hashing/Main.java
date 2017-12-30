/**
 * @author Brian Knotten
 * @version 1.0
 * CS 1501
 * Homework 2
 *
 * In Main.java, fill the collisionTest and 
 * sparsityTest methods as specified in the 
 * comments.
 */

public class Main {
    public static void main(String[] args){
	Datum[] dataArray = DataReader.loadData();
	ChainingHashMap map = new ChainingHashMap(1000);

	// Populate the map with words and their corresponding frequencies
	for(int i=0; i<dataArray.length; i++)
	    map.put(dataArray[i].word, dataArray[i].frequency);
		
	// Evaluate the effectiveness of the hash function
	int sizeOfLargestList = collisionTest(map);
	int numberOfEmptyLists = sparsityTest(map);
		
	// Print the results
	System.out.println("The size of the largest linkedlist is: " + sizeOfLargestList);
	System.out.println("The total number of empty linkedlists is: " + numberOfEmptyLists);
    }
	
    public static int collisionTest(ChainingHashMap map){
	// Problem #2A
	// Fill in this method to compute the size of the largest
	// linkedlist. You must use the getSize and countCollisions
	// methods to get full credit.

	// Variables for the largest linkedList (defaulted to 0)
	// and the current linkedList size.
	int largest = 0;
	int curr;

	// Iterate through the underlying array comparing
	// linkedList sizes and storing the largest to
	// be returned.
	for(int i = 0; i < map.getSize(); i++){
	    curr = map.countCollisions(i);
	    if(curr > largest){
		largest = curr;
	    }
	}
	return largest;
    }
	
    public static int sparsityTest(ChainingHashMap map){
	// Problem #2B
	// Fill in this method to compute the number of empty
	// linkedlists. You must use the getSize and countCollisions
	// methods to get full credit.

	// Variables to store the number of empty linkedLists
	// and the size of the current linkedList
	int num = 0;
	int llSize = 0;

	// Iterate through the underlying array finding the
	// size of the linkedList at the each index and
	// incrementing the counter if the linkedList
	// is empty.
	for(int i = 0; i < map.getSize(); i++){
	    llSize = map.countCollisions(i);
	    if(llSize == 0){
		num++;
	    }
	}
	return num;
    }

}
