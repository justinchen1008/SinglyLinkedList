import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - (description)
 *
 *	@author	
 *	@since	
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {
		head = new ListNode<E>(null);
		tail = new ListNode<E>(null);
	}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {}
	
	/**	Clears the list of elements */
	public void clear() {
		ListNode<E> temp = head;
		while(temp != null){
			temp.setValue(null);
			temp = temp.getNext();
			head = temp;
		}
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		if(head == null){
			head = new ListNode<E>(obj);
			tail = head;
			return true;
		}
		ListNode<E> temp = tail;
		ListNode<E> tempNode = new ListNode<E>(obj);
		temp.setNext(tempNode);
		tail = tempNode;
		return true;
	}
	//^good
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		int cnt = 0;
		ListNode<E> temp = head;
		if(index == 0){
			ListNode<E> tempObj = new ListNode<E>(obj);
			ListNode<E> nextObj = head;
			head = tempObj;
			if(tail == null){
				tail = nextObj;
			}
			tempObj.setNext(nextObj);
			return true;
		}
		while(cnt < index){
			if(temp == tail || temp == null){
				throw new NoSuchElementException();
			}
			cnt++;
			temp = temp.getNext();
		}
		if(temp == tail){
			add(obj);
		}
		ListNode<E> nextObj = temp.getNext();
		ListNode<E> tempObj = new ListNode<E>(obj);
		temp.setNext(tempObj);
		tempObj.setNext(nextObj);
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		int cnt = 0;
		ListNode<E> tempNode = head;
		while(tempNode != null && tempNode.getNext() != null){
			tempNode = tempNode.getNext();
			cnt++;
		}
		return cnt;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		int cnt = 0;
		ListNode<E> temp = head;
		while(cnt != index){
			cnt++;
			temp = temp.getNext();
			if(temp == null){
				throw new NoSuchElementException();
			}
		}
		return temp;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		int cnt = 0;
		ListNode<E> temp = head;
		while(cnt != index){
			cnt++;
			temp = temp.getNext();
			if(temp == null){
				throw new NoSuchElementException();
			}
		}
		E tempObj = temp.getValue();
		temp.setValue(obj);
		return tempObj;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		int cnt = 0;
		ListNode<E> temp = head;
		//maybe <?
		if(index == 0){
			head = temp.getNext();
			temp.setNext(null);
			return temp.getValue();
		}
		while(cnt < index-1){
			cnt++;
			temp = temp.getNext();
			if(temp == null){
				throw new NoSuchElementException();
			}
		}
		ListNode<E> skip = temp.getNext();
		if(skip == null) throw new NoSuchElementException();
		ListNode<E> dst = skip.getNext();
		//consider: last element (change last = wtv), first element(change first = wtv)
		//if(dst == null) throw new NoSuchElementException();
		temp.setNext(dst);
		if(dst == null){
			tail = temp;
		}
		return skip.getValue();
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		if(head==null && tail == null){
			return true;
		}
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		ListNode<E> temp = head;
		if(isEmpty() || head.getValue().equals(object)||tail.getValue().equals(object)) return true;
		while(temp != tail){
			if(temp == null){
				return false;
			}
			if(temp.getValue().equals(object)){
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		int cnt= 0;
		ListNode<E> temp = head;
		if(head.getValue().equals(element)) return 0;
		while(temp != tail){
			temp = temp.getNext();
			cnt++;
			if(temp.getValue().equals(element)) return cnt;
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
