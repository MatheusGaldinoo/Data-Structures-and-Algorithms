public class MergeSort {

    public void sort(int[] v, int start, int end) {
        if (start >= end) return;

        int middle = (end + start) / 2;

        sort(v, start, middle);
        sort(v, middle + 1, end);

        merge(v, start, middle, end);
    }

    private void merge(int[] v, int start, int middle, int end) {
        int[] helper = new int[v.length];
        for (int i = start; i <= end; i++) {
            helper[i] = v[i];
        }

        int i = start;
        int j = middle + 1;
        int k = start;

        while (i <= middle && j <= end) {
            if (helper[i] <= helper[j]) {
                v[k++] = helper[i++];
            } else {
                v[k++] = helper[j++];
            }
        }

        while (i <= middle) {
            v[k++] = helper[i++];
        }
    }
}
