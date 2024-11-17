public class SelectionSort {

    public void selectionSort(int[] v) {
        for (int i = 0; i < v.length; i++) {
		    int minIndex = i;
		    for (int j = i + 1; j < v.length; j++) {
			    if (v[j] < v[minIndex])
			    	minIndex = j;
        	}
		    int aux = v[i];
		    v[i] = v[minIndex];
		    v[minIndex] = aux;
        }
	}		
}
