package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 * 
 */
public class FloorCeilBinarySearchImpl implements FloorCeil {

    @Override
    public Integer floor(Integer[] array, Integer x) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        Integer result = null;

        while (leftIndex <= rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            if (array[mid] <= x && (mid == array.length - 1 || array[mid + 1] > x)) {
                result = array[mid]; // já é o floor
            } else if (array[mid] <= x) {
                leftIndex = mid + 1;
            } else {
                rightIndex = mid - 1;
            }
        }

        return result;
    }

    @Override
    public Integer ceil(Integer[] array, Integer x) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        Integer result = null;

        while (leftIndex <= rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            if (array[mid] >= x && (mid == 0 || array[mid - 1] < x)) {
                result = array[mid];
                return result; // já achou o ceil
            } else if (array[mid] < x) {
                leftIndex = mid + 1;
            } else {
                rightIndex = mid - 1;
            }
        }

        return result;
    }
}
