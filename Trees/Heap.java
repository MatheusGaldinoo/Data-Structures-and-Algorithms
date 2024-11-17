import java.util.Arrays;

public class Heap {
    
    private int[] heap;
    private int tail;
    
    public Heap(int capacity) {
        this.heap = new int[capacity];
        this.tail = -1;
    }
    
    public Heap(int[] heap) {
        this.heap = heap;
        this.tail = this.heap.length - 1;
        this.buildHeap();
    }

    public boolean isEmpty() {
        return this.tail == -1;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * (i + 1);
    }

    public int parent(int i) {
        return Math.floorDiv(i-1, 2);
    }

    public void add(int n) {
    	if (this.tail >= heap.length - 1) {
    		resize();
    	}
    	
    	this.tail++;
    	this.heap[this.tail] = n;
    	
    	int i = this.tail;
    	while (i > 0 && heap[parent(i)] < heap[i]) {
    		int aux = heap[i];
    		heap[i] = heap[parent(i)];
    		heap[parent(i)] = aux;
    		i = parent(i);
    	}
    }

    private void buildHeap() {
    	for (int i = 0; i < heap.length; i++) {
    		heap[i] = 0;
    	}
    }
    
    public int remove() {
        if (isEmpty()) throw new RuntimeException("Empty");
        int element = this.heap[0];
        this.heap[0] = this.heap[tail];
        this.tail -= 1;

        this.heapify(0);
        
        return element;
    }
       
    public int getKth(int k) {
        while (k > 0) {
        	if (k == 1) return heap[k-1];
        	remove();
        	k--;
        }
        return -1;
    }
    
    
    private void heapify(int index) {
        if (isLeaf(index) || !isValidIndex(index)) return;
        
        int index_max = max_index(index, left(index), right(index));
        
        if (index_max != index) {
               swap(index, index_max);
               heapify(index_max);
        }
    } 
    
    private int max_index(int index, int left, int right) {
        if (this.heap[index] > this.heap[left]) {
            if (isValidIndex(right)) {
                if (this.heap[index] < this.heap[right])
                    return right;
            }
            return index;
        
        } else {
            if (isValidIndex(right)) {
                if (this.heap[left] < this.heap[right])
                    return right;
            } 
            
            return left;
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= tail;
    }
    
    private boolean isLeaf(int index) {
        return index > parent(tail) && index <= tail; 
    } 
    
    private void swap(int i, int j) {
        int aux = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = aux;
    }

    private void resize() {
        int[] newHeap = new int[this.heap.length * 2];
        for (int i = 0; i <= tail; i++)
            newHeap[i] = this.heap[i];
        
        this.heap = newHeap;
    }
    
    public int size() {
        return this.tail + 1;
    }
    
    public String toString() {
        return Arrays.toString(this.heap);
    }

}