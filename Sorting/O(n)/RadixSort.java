public class RadixSort {

	public int[] radixSort(int[] v) {
		if (v.length == 0) return v;

		int max = v[0];
        for (int i = 1; i < v.length; i++) {
            if (v[i] > max) {
                max = v[i];
            }
        }
		
		int size = (int) Math.log10(max) + 1;
		int[] out = v;
				
		for (int i = 0; i < size; i++) {
			out = countingSortToRadix(out, 10, i);
		}
		
		return out;
	}

	private int[] countingSortToRadix(int[] a, int k, int d) {
        int[] c = new int[k];
        int[] b = new int[a.length];
        
        for (int i = 0; i < a.length; i++) {
        	int dig = (int) (a[i] / Math.pow(10, d)) % 10;
            c[dig] += 1;
        }
        
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }
        
        for (int i = a.length - 1; i >= 0; i--) {
        	int dig = (int) (a[i] / Math.pow(10, d)) % 10;
            b[c[dig] - 1] = a[i];
            c[dig] -= 1;
        }
        
        return b;
    }

}