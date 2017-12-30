/**
 * @author Brian Knotten
 * @version 2.0
 * CS 1501
 * Homework 1
 *
 * In Selector.java fill in the method select. Select will 
 * take in an array of size n and in O(n*k) time return an 
 * array with the k largest elements ordered from largest 
 * to smallest.
 */


import java.util.Arrays;

public class Selector {

    private static void swap(Word[] array, int i, int j){
	if(i == j) return;
		
	Word temp = array[i];
	array[i] = array[j];
	array[j] = temp;
    }
	
    public static Word[] select(Word[] array, int k){
	// Problem #1
	// Fill in this method with an O(n*k) time algorithm
	// that returns the k largest elements of array in
	// order from largest to smallest.
	// Note: This should return an array with k elements.

	// Modified version of the selection sort algorithm given
	// by the Professor in class; iterates through the array
	// to find the k-largest elements.
	for (int i = 0; i < k; i++){
	    Word max = array[i];
	    int index = i;

	    for (int j = i+1; j < array.length-1; j++){
		if (max.compareTo(array[j]) < 0){
		    max = array[j];
		    index = j;
		}
	    }
	    swap(array, i, index);
	}

	//Array to hold the largest k elements to be returned.
	Word[] largest = Arrays.copyOfRange(array, 0, k);

	return largest;
    }
}
