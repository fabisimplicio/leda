package sorting.simpleSorting;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

    SelectionSort3 selection = new SelectionSort3<>();

    Integer[] array = {10, 9, 8, 7, 6, 5};

    selection.sort(array, 0, 2);


    System.out.println(Arrays.toString(array));
        
    }    
    
}
