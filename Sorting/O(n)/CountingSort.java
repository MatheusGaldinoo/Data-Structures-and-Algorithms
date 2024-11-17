public class CountingSort {
   
    /**
     * Traditional version of counting sort.
     */
    public int[] classicCountingSort(int[] a, int k) {
        int[] c = new int[k];
        int[] b = new int[a.length];
        
        for (int i = 0; i < a.length; i++) {
            c[a[i] - 1] += 1;
        }
        
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i-1];
        }
        
        for (int i = a.length - 1; i >= 0; i--) {
            b[c[a[i] - 1] - 1] = a[i];
            c[a[i] - 1] -= 1;
        }
        
        return b;
    }

    /**
     * Version of counting sort that handles zero values in the original collection.
     */
    public int[] zeroCountingSort(int[] v, int k) {
        int[] c = new int[k + 1];
        int[] b = new int[v.length];
        
        for (int i = 0; i < v.length; i++) {
            c[v[i]] += 1;
        }
        
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i-1];
        }
        
        for (int i = v.length - 1; i >= 0; i--) {
            b[c[v[i]] - 1] = v[i];
            c[v[i]] -= 1;
        }
        
        return b;
    }

    /**
     * Version of counting sort that handles negative values in the original collection.
     */
    public int[] negativosCountingSort(int[] v, int k) {
        int min = v[0];
        for (int i = 1; i < v.length; i++) {
            if (v[i] < min) {
            	min = v[i];
            }
        }
        
        int[] c = new int[k - min + 1];
        int[] b = new int[v.length];
        
        for (int i = 0; i < v.length; i++) {
            c[v[i] - min] += 1;
        }
        
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i-1];
        }
        
        for (int i = v.length - 1; i >= 0; i--) {
            b[c[v[i] - min] - 1] = v[i];
            c[v[i] - min] -= 1;
        }
        
        return b;
    }
}
