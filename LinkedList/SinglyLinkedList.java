import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - (description)
 *
 *	@author	Naithik Pradeep
 *	@since	April 29, 2024
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	private int size;
	
	/* No-args Constructors */
	public SinglyLinkedList() {
		size = 0;
	}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		for(int i = 0; i < oldList.size(); i++) {
			add(oldList.get(i).getValue());
		}	
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		if(head == null) {
			head = new ListNode(obj);
			size++;
		}
		else if(tail == null) {
			tail = new ListNode(obj);
			head.setNext(tail);
			size++;
		}
		else {
			ListNode<E> added = tail;
			tail = new ListNode(obj);
			added.setNext(tail);
			size++;
		}
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		if(index > size || index < 0) {
			throw new NoSuchElementException();
		}
		else if(index == size) {
			if(index == 1) {
				tail = new ListNode(obj);
				head.setNext(tail);
			}
			else {
				add(obj);
			}
		}
		else if(index == 0) {
				
			ListNode<E> temp = head;
			head = new ListNode(obj);
			if(size == 1) {
				tail = temp;
				head.setNext(tail);
			}
			else {
				head.setNext(temp);	
			}
			size++;
			return true;
		}
		else if(index == 1) {
			ListNode<E> object = new ListNode(obj, head.getNext());
			head.setNext(object);
		}
		else {
			ListNode<E> curr = head;
			ListNode<E> prev = null;
			int i = 0;
			while (i < index) {
				prev = curr;
				curr = curr.getNext();
				i++;
			}
			ListNode<E> object = new ListNode(obj, curr.getNext());
			prev.getNext().setNext(object);
			size++;
		}
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		return size;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		if(index >= size || index < 0) {
			throw new NoSuchElementException();
		}
		else {
			ListNode<E> curr = head;
			int i = 0;
			while(i < index) {
				curr = curr.getNext();
				i++;
			}
			return curr;
		}
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		E e = get(index).getValue();
		get(index).setValue(obj);
		return e;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		if(size == 1 && index == 0) {
			E removed = head.getValue();
			head = null;
			size--;
			return removed;
		}
		else if(index == 0) {
			if(size == 2) {
				E removed = head.getValue();
				head = tail;
				tail = null;
				size--;
				return removed;
			}
			else {
				E removed = head.getValue();
				head = head.getNext();
				size--;
				return removed;
			}
		}
		else if(index == size - 1) {
			if(size == 2) {
				E removed = tail.getValue();
				head.setNext(null);
				tail = null;
				size--;
				return removed;
			}
			else {
				E removed = tail.getValue();
				tail = get(index - 1);
				get(index - 2).setNext(tail);
				size--;
				return removed;
			}
		}
		else {
			ListNode<E> prev = get(index - 1);
			E removed = get(index).getValue();
			prev.setNext(get(index).getNext());
			size--;
			return removed;
		}
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		if(head == null) {
			return true;
		}
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		for(int i = 0; i < size; i++) {
			if(get(i).getValue().equals(object)) {
				return true;
			}
		}
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		for(int i = 0; i < size; i++) {
			if(get(i).getValue().equals(element)) {
				return i;
			}
		}
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}
