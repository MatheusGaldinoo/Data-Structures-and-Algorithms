import java.util.*;

public class BST {

    private Node root;
    private int size;
    
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public void add(int v) {
        this.size++;
        
        Node aux = this.root;
        Node newNode = new Node(v);
        
        if (aux == null) {
            this.root = newNode;
            return;
        }

        while (aux != null) {
            if (v < aux.value) {
                if (aux.left == null) {
                    aux.left = newNode;
                    newNode.parent = aux;
                    return;
                }
                aux = aux.left;
            } else {
                if (aux.right == null) {
                    aux.right = newNode;
                    newNode.parent = aux;
                    return;
                }
                aux = aux.right;
            }
        }
    }
    
    public Node search(int element) {
        
        Node aux = this.root;
        
        while (aux != null) {
            if (aux.value == element) return aux;
            if (element < aux.value) aux = aux.left;
            if (element > aux.value) aux = aux.right;
        }
        
        return null;
    }
    
    public int height() {
       return height(this.root);
    }

    public int height(Node n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public boolean equals(BST another) {
        return this.preOrder().equals(another.preOrder());    
    }
    
    public String preOrder() {
        return preOrder(this.root).trim();
    }
    
    public String preOrder(Node n) {
        if (n == null) return "";
        
        String result = String.valueOf(n.value) + " "; 
        String left = preOrder(n.left);
        String right = preOrder(n.right);
        result += left + right;
        
        return result;
    }

    public String inOrder() {
        return inOrder(this.root).trim();
    }

    public String inOrder(Node n) {
        if (n == null) return "";
        
        String left = inOrder(n.left);
        String result = String.valueOf(n.value) + " "; 
        String right = inOrder(n.right);
        result = left + result + right;
        
        return result;
    }

    public String postOrder() {
        return postOrder(this.root).trim();
    }

    public String postOrder(Node n) {
        if (n == null) return "";
        
        String left = postOrder(n.left);
        String right = postOrder(n.right);
        String result = String.valueOf(n.value) + " "; 
        result = left + right + result;
        
        return result;
    }

    public int countLeaves() {
        return countLeaves(this.root);
    }

    public int countLeaves(Node n) {
        if (n == null) return 0;
        if (n.isLeaf()) return 1;
        else return countLeaves(n.left) + countLeaves(n.right);
    }

    public int countGreaterThan(int v) {
        return countGreaterThan(v, this.root);
    }
    
    public int countGreaterThan(int v, Node n) {
        if (n == null) return 0;
        if (n.value > v) return 1 + countGreaterThan(v, n.left) + countGreaterThan(v, n.right);
        return countGreaterThan(v, n.right);    
    }
    
    public int countLessThan(int v) {
        return countLessThan(v, this.root);
    }
    
    public int countLessThan(int v, Node n) {
        if (n == null) return 0;
        if (n.value < v) return 1 + countLessThan(v, n.left) + countLessThan(v, n.right);
        return countLessThan(v, n.left);    
    }
    
    public Node min() {
        return min(this.root);
    }
    
    public Node min(Node n) {        
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }
    
    public Node max() {
        return max(this.root);
    }
    
    public Node max(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }
    
    public Node successor(int k) {
        Node n = search(k);
        
        if (n == null) return null;
        
        if (n.right != null) return min(n.right);
        
        while (n.parent != null && n.parent.value > n.value) {
            n = n.parent;
        }
        
        return n;
    }
    
    public Node predecessor(int k) {
        Node n = search(k);
        
        if (n == null) return null;
        
        if (n.left != null) return max(n.left);
        
        while (n.parent != null && n.parent.value < n.value) {
            n = n.parent;
        }
        
        return n;
    }
    
    public Node remove(int k) {
        if (search(k) == null) {
            return null;
        }
        this.root = remove(this.root, k);
        return this.root;
    }

    public Node remove(Node aux, int k) {
        if (aux == null) return null;

        if (k < aux.value) aux.left = remove(aux.left, k);
        else if (k > aux.value) aux.right = remove(aux.right, k);
        else { 
            if (aux.isLeaf()) return null;
            else if (aux.hasOnlyLeftChild()) {
                aux.left.parent = aux.parent;
                return aux.left;
            } else if (aux.hasOnlyRightChild()) {
                aux.right.parent = aux.parent;
                return aux.right;
            }

            Node successor = successor(aux.value);
            aux.value = successor.value;
            aux.right = remove(aux.right, successor.value);
        }
        return aux;
    }
    
    public int balance(Node n) {
        return height(n.left) - height(n.right);
    }
    
    public void bfs() {
        if (root == null) return;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node aux = queue.poll();

            System.out.print(aux.value + " ");

            if (aux.left != null) {
                queue.add(aux.left);
            }

            if (aux.right != null) {
                queue.add(aux.right);
            }
        }
    }
    
    public int size() {
        return this.size;
    }
    
}

class Node {
    
    int value;
    Node left;
    Node right;
    Node parent;
    
    Node(int v) {
        this.value = v;
    }
    
    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }
    
    public boolean hasTwoBranches() {
        return (this.left != null && this.right != null);
    }
    
    public boolean hasOnlyBranch() {
        return (!(isLeaf() || hasTwoBranches()));
    }
    
    public boolean hasOnlyLeftChild() {
        return (this.left != null && this.right == null);
    }

    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }
}
