package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (array != null && array.length > 0 &&
            leftIndex >= 0 && rightIndex < array.length &&
            leftIndex < rightIndex) {

            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            sort(array, leftIndex, mid);
            sort(array, mid + 1, rightIndex);
            merge(array, leftIndex, mid, rightIndex);
        }
    }

	private void merge(T[] array, int left, int mid, int right) {
    	int size = right - left + 1;

    
    	T[] temp = Arrays.copyOf(array, size);

    	int i = left;
    	int j = mid + 1;
    	int k = 0;

    	while (i <= mid && j <= right) {
        	if (array[i].compareTo(array[j]) <= 0) {
            	temp[k++] = array[i++];
        	} else {
            	temp[k++] = array[j++];
        	}
    	}

    	while (i <= mid) {
        	temp[k++] = array[i++];
    	}

    	while (j <= right) {
        	temp[k++] = array[j++];
    	}

    	for (int m = 0; m < size; m++) {
        	array[left + m] = temp[m]; 
   		}
	}
}

