/**
 * @author Brian Knotten
 * @version 1.0
 * CS 1501
 * Homework 2
 *
 * Code provided by the Professor
 */

public class Node {
	public Word word;
	public Integer frequency;
	public Node next;
	
	public Node(Word word, Integer frequency, Node next){
		this.word = word;
		this.frequency = frequency;
		this.next = next;
	}
}
