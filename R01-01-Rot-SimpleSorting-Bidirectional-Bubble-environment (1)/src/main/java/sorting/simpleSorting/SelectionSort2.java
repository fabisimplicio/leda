package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;


public class SelectionSort2<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

        if(array != null && leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex) {

        for(int i = leftIndex; i < rightIndex; i++) {
            int indiceMenor = i;
            for(int j = i + 1; j < array.length; j++) {
                if(array[j].compareTo(array[indiceMenor]) < 0) {
                    indiceMenor = j;
                }
            }
            Util.swap(array, i, indiceMenor);
        }
        }
    }
}
