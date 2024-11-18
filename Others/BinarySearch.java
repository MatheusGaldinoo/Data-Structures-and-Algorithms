public class BinarySearch {

    public int recursiveBinarySearch(int[] v, int start, int end, int key) {
        if (end < start) return -1;

        int mid = (end + start) / 2;
        
        if (key == v[mid]) return mid;
        else if (key < v[mid]) return recursiveBinarySearch(v, start, mid - 1, key);
        else return recursiveBinarySearch(v, mid + 1, end, key);
    }   
        
    public int iterativeBinarySearch(int[] v, int key) {
        int start = 0;
        int end = v.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (v[mid] == key)
                return mid;

            if (v[mid] < key)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return -1;
    }

}
