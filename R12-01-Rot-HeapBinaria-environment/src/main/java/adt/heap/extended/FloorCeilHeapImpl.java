package adt.heap.extended;

import java.util.Comparator;
import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

    public FloorCeilHeapImpl(Comparator<Integer> comparator) {
        super(comparator);
    }

    @Override
    public Integer floor(Integer[] array, double numero) {
        Integer floor = null;
        
        if (array != null && array.length > 0) {
            buildHeap(array);
            
            while (!isEmpty()) {
                Integer current = extractRootElement();    
                if (current <= numero) {
                    if (floor == null || current > floor) {
                        floor = current;
                    }
                }
            }
        }
        return floor;
    }

    @Override
    public Integer ceil(Integer[] array, double numero) {
        Integer ceil = null;
        
        if (array != null && array.length > 0) {
            buildHeap(array);
            
            while (!isEmpty()) {
                Integer current = extractRootElement();
                if (current >= numero) {
                    if (ceil == null || current < ceil) {
                        ceil = current;
                    }
                }
            }
        }
        return ceil;
    }
}