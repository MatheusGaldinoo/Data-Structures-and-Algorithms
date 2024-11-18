public class Queue {

    private int[] queue;
    private int head;
    private int tail;
    private int size;
    
    /**
     * The queue should follow the circular approach. This means adding and removing should be O(1)
     */
    public Queue(int capacity) {
        this.queue = new int[capacity];
        this.head = -1;
        this.tail = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == -1 && this.tail == -1;
    }

    public boolean isFull() {
        return ((this.tail + 1) % this.queue.length) == this.head;
    }

    public void addLast(int value) {
        if (isFull()) throw new RuntimeException("QUEUE IS FULL!");
    
        if (isEmpty()) this.head = 0;
            
        this.tail = (this.tail + 1) % this.queue.length;
        this.queue[tail] = value;
        this.size++;
    }

    public int removeFirst() {
        if (isEmpty()) throw new RuntimeException("QUEUE IS EMPTY!");
        
        int value = this.queue[head];
        
        if (this.head == this.tail) {
            this.head = -1;
            this.tail = -1;
        } else {
            this.head = (this.head + 1) % this.queue.length;
        }
        
        size--;
        
        return value;
    }

    public int getFirst() {
        if (isEmpty()) throw new RuntimeException("QUEUE IS EMPTY!");

        return this.queue[head];
    }

    public int getLast() {
        if (isEmpty()) throw new RuntimeException("QUEUE IS EMPTY!");
        
        return this.queue[tail];
    }

    public String toString() {
        
        if (isEmpty()) return "";
        
        StringBuffer sb = new StringBuffer();
        
        int i = head;
        while (true) {
            sb.append(queue[i]);

            if (i == tail) {
                sb.append("");
                break;
            } else {
                sb.append(", ");
            }
            
            i = (i + 1) % queue.length;
        }
        
        return sb.toString();
    }

    // Should return the position of the first occurrence of the element passed as parameter
    public int indexOf(int value) {
        
        if (isEmpty()) return -1;
        
        int i = head;
        while (i != tail) {
            if (queue[i] == value) return i;
            i = (i+1) % queue.length;
        }
        
        if (queue[i] == value) return i;
        
        return -1;
    }

    // Should return the position of the last occurrence of the element passed as parameter
    public int lastIndexOf(int value) {
        if (isEmpty()) return -1; 

        int i = tail;
        while (true) {
            if (queue[i] == value) return i;
            if (i == head) break;
            i = (i - 1 + queue.length) % queue.length;
        }
        
        return -1;
    }
    
    public int size() {
        return this.size;
    }
}
