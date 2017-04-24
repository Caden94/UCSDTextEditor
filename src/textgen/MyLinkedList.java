package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null) throw new NullPointerException();
		else {
			LLNode<E> n = new LLNode<E>(element);
			n.next = tail;
			n.prev = n.next.prev;
			n.next.prev = n;
			n.prev.next = n;
			size++;
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> temp = getNode(index);
		return temp.data;
	}
	/**
	 * Helper method
	 */
	public LLNode<E> getNode(int index) {
		if (index < 0 || index >= size || head.next == tail) {
		throw new IndexOutOfBoundsException();
		}
		else {
			LLNode<E> temp = new LLNode<E>(null);
			temp.next = head.next;
			for (int i = 0; i < index+1; i++) {
				temp.data = temp.next.data;
				temp.prev = temp.next.prev;
				temp.next = temp.next.next;
			}
			return temp;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		// IndexOutOfBoundsException
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// Element == null
		else if (element == null) throw new NullPointerException();
		// Linked List == null
		else if (head.next == tail) {
			LLNode<E> n = new LLNode<E>(element);
			n.next = tail;
			n.prev = head;
			head.next = n;
			tail.prev = n;
			size++;
		}
		// Normal
		else {
			LLNode<E> temp = new LLNode<E>(null);
			temp.next = head.next;
			for (int i = 0; i < index+1; i++) {
				temp.data = temp.next.data;
				temp.prev = temp.next.prev;
				temp.next = temp.next.next;
			}
			// temp node == Linked List[index]
			LLNode<E> n = new LLNode<E>(element);
			n.next = temp.next.prev;
			n.prev = temp.prev;
			n.next.prev = n;
			n.prev.next = n;
			size++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method		
		LLNode<E> temp = getNode(index);
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		size--;
		return temp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E> temp = getNode(index);
		if (element == null) throw new NullPointerException();
		temp.prev.next.data = element;
		return temp.prev.next.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
