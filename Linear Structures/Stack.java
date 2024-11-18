public class Stack {

    private int top;
    private int capacity;
    private int[] stack;

    public Stack(int capacity) {
        this.top = -1;
        this.capacity = capacity;
        this.stack = new int[capacity];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top + 1 == this.capacity;
    }

    public void push(int value) {
        if (isFull()) throw new RuntimeException("Stack Full");
        this.stack[++this.top] = value;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack Empty");
        return stack[this.top--];
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Stack Empty");
        return stack[this.top];
    }

    // Direct iteration over the array or creating arrays is not allowed.
    public String toString() {
        if (isEmpty()) return "";

        StringBuffer sb = new StringBuffer();
        Stack aux = new Stack(size());

        while(!isEmpty()) {
            aux.push(peek());
            sb.append(pop());

            if (size() > 0) sb.append(", ");
        }

        while(!aux.isEmpty()) {
            push(aux.pop());
        }

        return sb.toString();
    }

    /** 
     * Should return the position of the first occurrence of the element passed as a parameter.
     * Note that the top is always at position 0, below the top is position 1, etc.
     * Do not confuse with array indices. Direct iteration over the array is not allowed.
     **/
    public int indexOf(int value) {
        if (isEmpty()) return -1;

        int i = 0;
        Stack aux = new Stack(size());

        while (!isEmpty()) {
            if (peek() == value) break;
            aux.push(pop());
            i++; 
        }

        while (!aux.isEmpty()) {
            push(aux.pop());
        }

        if (isEmpty()) return -1;
        else return i;

    }

    
    public void sortStack() {
        Stack newStack = new Stack(this.size());
        while (!this.isEmpty()) {
            int aux = this.pop(); 
            while (!newStack.isEmpty() && newStack.peek() > aux) {
                this.push(newStack.pop());
            }
            newStack.push(aux);
        }

        while (!newStack.isEmpty()) {
            this.push(newStack.pop());
        }
    }
    
    public int size() {
        return this.top + 1;
    }
}
