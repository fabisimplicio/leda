package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
	    
	    if (array != null && array.length > 0 && leftIndex >= 0 && rightIndex < array.length && leftIndex <= rightIndex) {

            int maior = array[leftIndex];

            for (int i = leftIndex + 1; i <= rightIndex; i++) {
                if (array[i] > maior) {
                    maior = array[i];
                }
            }

            int[] cont = new int[maior + 1];

            for (int i = leftIndex; i <= rightIndex; i++) {
                cont[array[i]]++;
            }

            for (int i = 1; i < cont.length; i++) {
                cont[i] += cont[i - 1];
            }

            Integer[] arrayOrdenado = new Integer[array.length];

            for (int i = rightIndex; i >= leftIndex; i--) {
                int valor = array[i];
                int posicao = cont[valor] - 1;
                arrayOrdenado[leftIndex + posicao] = valor;
                cont[valor]--;
            }

            for (int i = leftIndex; i <= rightIndex; i++) {
                array[i] = arrayOrdenado[i];
            }
        }
    }
}
