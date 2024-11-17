public class InsertionSort {
	
    public void insertionSort(int[] v) {
        for (int i = 0; i < v.length; i++) {
        	int j = i - 1;
        	int aux = v[i]; 
        	while (j >= 0 && v[j] > aux) {
            	v[j + 1] = v[j];
        		j = j - 1;
        	} 
        	v[j + 1] = aux;
        }
    }
}
