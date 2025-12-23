package problems;

public class Main {

    public static void main(String[] args) {

        FloorCeilBinarySearchImpl f = new FloorCeilBinarySearchImpl();

        Integer[] array = {1, 3, 5, 7};

        System.out.println(f.ceil(array, 6));
        System.out.println(f.floor(array, 6));
        
        
    }

    
}
