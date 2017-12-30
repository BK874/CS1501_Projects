/**
 * @author Brian Knotten
 * @version 1.0
 * CS 1501
 * Homework 2
 *
 * In ChainingHashMap.java, fill in the get, 
 * put, and remove methods as specified in 
 * the comments.
 */


public class ChainingHashMap{
	Node[] array;
	int size;
	
	public ChainingHashMap(int size){
		this.size = size;
		array = new Node[size];
	}

	public Integer get(Word key) {
	// Problem #1A
	// Fill in this method to get the value corresponding
	// with the key. Note: if the key is not found, then
	// return null.

	    // Hash the key mod size to get its index
	    int hashed = key.hashCode() % size;

	    // Iterate through the linkedList at the index returning
	    // the corresponding value if found and returning null
	    // otherwise.
	    for(Node x = array[hashed]; x != null; x = x.next){
		if(key.equals(x.word)){
		    return (Integer)x.frequency;
		}
	    }
	    return null;
	}

    
	public void put(Word key, Integer value) {
	// Problem #1B
	// Fill in this method to insert a new key-value pair into
	// the map or update the existing key-value pair if the
	// key is already in the map.

	    // Hash the key mod size to get its index
	    int hashed = key.hashCode() % size;

	    // Iterate through the linkedList at the index replacing
	    // the corresponding frequency if found, creating a new
	    // node at the beginning otherwise.
	    for(Node x = array[hashed]; x != null; x = x.next){
		if (key.equals(x.word)){
		    x.frequency = value;
		    return;
		}
	    }
	    array[hashed] = new Node(key, value, array[hashed]);
	}

	public Integer remove(Word key) {
	// Problem #1C
	// Fill in this method to remove a key-value pair from the
	// map and return the corresponding value. If the key is not
	// found, then return null.

	    // Hash the key mod size to get its index &
	    // set the previous node to the head.
	    int freq;
	    int hashed = key.hashCode() % size;
	    Node prev = array[hashed];

	    // Iterate through the linkedList at the index,
	    // returning the frequency of the word if found
	    // after deleting the node by pointing the previous
	    // node to the removed node's next node. If the node
	    // is the head node, replace it with its next node.
	    for(Node x = array[hashed]; x != null; x = x.next){
		if(key.equals(x.word)){
		    freq = x.frequency;
		    if(x.equals(array[hashed].word)){
			array[hashed] = array[hashed].next;
			return (Integer)freq;
		    }
		    prev.next = x.next;
		    return (Integer)freq;
		}
		prev = x;
	    }
	    return null;
	}
	
	// This method returns the total size of the underlying array.
	// In other words, it returns the total number of linkedlists.
	public int getSize(){
		return size;
	}
	
	// This method counts how many keys are stored at the given array index.
	// In other words, it computes the size of the linkedlist at the given index.
	public int countCollisions(int index){
		if(index < 0 || index >= size) return -1;
		
		int count = 0;
		Node temp = array[index];
		while(temp != null){
			temp = temp.next;
			count++;
		}
		
		return count;
	}
	
}
