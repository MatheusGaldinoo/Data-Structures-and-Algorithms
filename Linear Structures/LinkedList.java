import java.util.*;

public class LinkedList {

    private Node head; 
    private Node tail;
    private int size;
	
    public LinkedList() {
    	this.head = null;
    	this.tail = null;
    	this.size = 0;
    }
   
    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    public void addFirst(int value) {
    	Node n = new Node(value);
    	
    	if (isEmpty()) {
    		this.head = n;
    		this.tail = n;
    	} else {
    		n.next = this.head;
    		this.head.prev = n;
    		this.head = n;
    	}
    	this.size++;
    }

    public void addLast(int value) {
    	Node n = new Node(value);
    	
    	if (isEmpty()) {
    		this.head = n;
    		this.tail = n;
    	} else {
    		n.prev = this.tail;
    		this.tail.next = n;
    		this.tail = n;
    	}
    	this.size++;
    }

    public void add(int index, int value) {
    	if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
            
        Node n = new Node(value);
            
        if (index == 0) {
            this.addFirst(value);
            
        } else if (index == size() - 1) {
            this.addLast(value);
            
        } else {
        	Node aux = this.head;
        	for (int i = 0; i < index-1; i++) 
        		aux = aux.next;
        	
        	n.next = aux.next;
        	aux.next = n;
        	n.next.prev = n;
        	n.prev = aux;
        	
        	this.size++;
        }
    }

    public int getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.head.value;
    }

    public int getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.tail.value;
    }

    public int get(int index) {
    	if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
            
        Node aux = this.head;
            
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
            
        return aux.value;
    }

    public int removeFirst() {
    	if (isEmpty()) throw new NoSuchElementException();
        
        Node n = this.head;
            
        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
            
        this.size--;
        return n.value;
    }

    public int removeLast() {
    	if (isEmpty()) throw new NoSuchElementException();
        
    	Node n = this.tail;
        
        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }           
        
        size--;
        return n.value;
    }

    public int remove(int index) {
    	if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        
        if (index == 0) return removeFirst();
        if (index == this.size - 1) return removeLast();
        
        Node aux = this.head;
        for (int i = 0; i < index; i++)
            aux = aux.next;
        
        aux.prev.next = aux.next;
        aux.next.prev = aux.prev;
        this.size--;       
        return aux.value;
    }

    public boolean removeByValue(int value) {
    	Node aux = this.head;
        for (int i = 0; i < this.size; i++) {
            if (aux.value == value) {
                if (i == 0) removeFirst();
                else if (i == size() - 1) removeLast();
                else {
                    aux.prev.next = aux.next;
                    aux.next.prev = aux.prev;
                    this.size -= 1;
                }
                return true;
            }
            aux = aux.next;
        }
            
        return false;
    }

    public int indexOf(int value) {
    	 Node aux = this.head;
    	 int index = 0;
    	 while (aux != null) { 
    		 if(aux.value == value) return index;
    	     aux = aux.next;
    	     index++;
    	 }
    	 return -1;
    }

    public boolean contains(int value) {
    	return indexOf(value) != -1;
    }
   
    public int lastIndexOf(int value) {
    	Node aux = this.tail;
   	 	int index = this.size - 1;
   	 	while (aux != null) { 
   	 		if(aux.value == value) return index;
   	 		aux = aux.prev;
   	 		index--;
   	    }
   	 	return -1;
    }
    
    public String toString() {
        if (isEmpty()) return "";

        Node aux = this.head;
        String out = "";
        while (aux != null) {
            out += aux.value + ", ";
            aux = aux.next;
        }
        return out.substring(0, out.length() - 2);
    }
    
    public int size() {
        return this.size;
    }
}

class Node {

    int value;
    Node prev;
    Node next;

    Node(int value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }

}
