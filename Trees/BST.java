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

    public boolean equals(BST outra) {
        return this.preOrder().equals(outra.preOrder());	
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

    public String posOrder() {
        return posOrder(this.root).trim();
    }

    public String posOrder(Node n) {
        if (n == null) return "";
        
        String left = posOrder(n.left);
        String right = posOrder(n.right);
        String result = String.valueOf(n.value) + " "; 
        result = left + right + result;
        
        return result;
    }

    public int contaFolhas() {
        return contaFolhas(this.root);
    }

    public int contaFolhas(Node n) {
        if (n == null) return 0;
        if (n.isLeaf()) return 1;
        else return contaFolhas(n.left) + contaFolhas(n.right);
    }

    public int contaMaiores(int v) {
		return contaMaiores(v, this.root);
	}
	
	public int contaMaiores(int v, Node n) {
		if (n == null) return 0;
		if (n.value > v) return 1 + contaMaiores(v, n.left) + contaMaiores(v, n.right);
		return contaMaiores(v, n.right);	
	}
	
	public int contaMenores(int v) {
		return contaMenores(v, this.root);
	}
	
	public int contaMenores(int v, Node n) {
		if (n == null) return 0;
		if (n.value < v) return 1 + contaMenores(v, n.left) + contaMenores(v, n.right);
		return contaMenores(v, n.left);	
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
    
    public Node sucessor(int k) {
    	Node n = search(k);
    	
    	if (n == null) return null;
    	
    	if (n.right != null) return min(n.right);
    	
    	while (n.parent != null && n.parent.value > n.value) {
    		n = n.parent;
    	}
    	
    	return n;
    }
    
    public Node antecessor(int k) {
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

            Node sucessor = sucessor(aux.value);
            aux.value = sucessor.value;
            aux.right = remove(aux.right, sucessor.value);
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
    
    public boolean hasTwoBranch() {
    	return (this.left != null && this.right != null);
    }
    
    public boolean hasOnlyBranch() {
    	return (!(isLeaf() || hasTwoBranch()));
    }
    
    public boolean hasOnlyLeftChild() {
    	return (this.left != null && this.right == null);
    }

    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }
}
