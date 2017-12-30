/**
 * @author Brian Knotten
 * @version 1.0
 * CS 1501
 * Homework 1
 *
 * In Heap.java, fill in the method buildHeap. 
 * BuildHeap will take in an array of size n and in O(n) time,
 * build the complete binary heap containing these n elements.
 *
 * In Heap.java, fill in the method removeMax. RemoveMax will 
 * in O(log(n)) time, remove the root element, restore the heap 
 * structure, and finally return the removed root element.
 */


public class Heap {

    private Word[] heapArray;
    private int heapSize;
     
    public Heap(Word[] array){
	buildHeap(array);
    }
	
    public void buildHeap(Word[] array){
	// Problem #2
	// Fill in this method with an O(n) time algorithm
	// that builds an n element complete binary heap.
	// Note: You are allowed to add and modify fields
	// and helper methods.

	// Set the heap size equal to the array length and
	// clone the array into the heapArray
	heapSize = array.length;
	heapArray = array.clone();
	
	// For loop to iterate through the elements of the heapArray in reverse order and applies the sink operation on each
	for (int i = heapSize-1; i >= 0; i--){
	    if ((leftChild(heapSize-1, i) != -1) ||
		(rightChild(heapSize-1, i) != -1)){
		sink(heapArray, i);
	    }
	}
    }

    // Methods for finding the index of the Parent, Left Child, and Right Child of an element given its index
    private int parent (int i){
	if (i == 0) return -1;
	return (i-1)/2;
    }

    private int leftChild (int n, int i){
	if (2*i+1 > n) return -1;
	return 2*i+1;
    }

    private int rightChild(int n, int i){
	if (2*i+2 > n) return -1;
	return 2*i+2;
    }

    // Method for swapping two elements in an array
    // Copied from the Selector class; provided by the professor
    private static void swap(Word[] array, int i, int j){
	if(i == j) return;
		
	Word temp = array[i];
	array[i] = array[j];
	array[j] = temp;
    }

    // Method for sinking an element to its appropriate heap position
    // Modified version of the one given in the book
    private void sink(Word[] array, int k){
	while(2*k+1 <= heapSize-1){
	    int j = 2*k+1;
	    if(j < heapSize-1 && array[j].compareTo(array[j+1]) <= 0) j++;
	    if(array[k].compareTo(array[j]) > 0) break;
	    swap(array, k, j);
	    k = j;
	}
    }

	
    public Word removeMax(){
	// Problem #3
	// Fill in this method with an O(log(n)) time algorithm
	// that removes the root element, restores the heap
	// structure, and finally returns the removed root element.

	// Variable max to hold the current root of the heap
	Word max = heapArray[0];
	
	// Swap the root and last element of heap,
	// overwrite the last position (now holding the root),
	// decrement the heap size, and sink the new root.
	swap(heapArray, 0, heapSize-1);
	heapArray[heapSize-1] = null;
	heapSize--;
	sink(heapArray, 0);

	// Finally, return the old root as the max element
	return max;
    }
	
    public Word[] select(int k){
	Word[] result = new Word[k];
	for(int i = 0; i < k; i++){
	    result[i] = this.removeMax();
	}
	return result;
    }
}
