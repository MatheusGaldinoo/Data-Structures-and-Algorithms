import java.util.Arrays;

public class QuickSort {

    public void quickSort(int[] v, int start, int end) {
    	if (start >= end) return;
    	
    	// Choose just one pivot
    	int pivot = median(v, start, end);
    	int pivot = hoare(v, start, end);
    	int pivot = lomuto(v, start, end);
    	
    	quickSort(v, start, pivot - 1);
    	quickSort(v, pivot + 1, end);
    
    }
  
    /**
     * Pick Pivot by Median Of Three Pivot Strategy
     */
    public int median(int[] v, int start, int end) {
    	int middle = (end + start) / 2;
    	int[] values = {v[start], v[middle], v[end]};
    	Arrays.sort(values);
    	
    	if (values[1] == v[start]) return start;
    	else if (values[1] == v[middle]) return middle;
    	else return end;
    }
    
    /**
     * Pick Pivot by Hoare Partition Scheme 
     */
    public int hoare(int[] v, int start, int end) {
    	int i = start + 1;
    	int j = end;
    	int pivot = v[start];
    	
    	while (i <= j) {
    		
    		while (i <= j && v[i] <= pivot) {
    			i++;
    		}
    		
    		while (i <= j && v[j] > pivot) {
    			j--;
    		}
    		
    		if (i < j) {
    			swap(v, i, j);
    		}
    	}
    	swap(v, start, j);
    	return j;
    }
    
    /**
     * Pick Pivot by Lomuto Partition Scheme 
     */
    public int lomuto(int[] v, int start, int end) {
    	int pivot = v[start];
    	int i = start;
    	
    	for (int j = start + 1; j <= end; j++) {
    		if (v[j] <= pivot) {
    			i++;
    			swap(v, i, j);
    		}
    	}
    	swap(v, start, i);
    	return i;
    }  
    

    private void swap(int[] v, int i, int j) {
    	int value = v[i];
        v[i] = v[j];
        v[j] = value;
    }
}
