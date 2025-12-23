package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
       
		if (array != null && array.length > 0 && leftIndex >= 0 && rightIndex < array.length && leftIndex <= rightIndex) {

            int menor = array[leftIndex];
            int maior = array[leftIndex];

            for (int i = leftIndex + 1; i <= rightIndex; i++) {
                if (array[i] < menor) {
                    menor = array[i];
                } else if (array[i] > maior) {
                    maior = array[i];
                }
            }

            int tamanho = maior - menor + 1;
            int[] cont = new int[tamanho];

            for (int i = leftIndex; i <= rightIndex; i++) {
                cont[array[i] - menor]++;
            }

            for (int i = 1; i < tamanho; i++) {
                cont[i] += cont[i - 1];
            }

            Integer[] arrayOrdenado = new Integer[array.length];

            for (int i = rightIndex; i >= leftIndex; i--) {
                int valor = array[i];
                int posicao = cont[valor - menor] - 1;
                arrayOrdenado[leftIndex + posicao] = valor;
                cont[valor - menor]--;
            }

            for (int i = leftIndex; i <= rightIndex; i++) {
                array[i] = arrayOrdenado[i];
            }
        }
	}

}
