package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator.
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma
 * max-heap
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		int left = left(position);
		int right = right(position);
		int extreme = position;

		if (left <= index && comparator.compare(heap[left], heap[position]) > 0) {
			extreme = left;
		}

		if (right <= index && comparator.compare(heap[right], heap[extreme]) > 0) {
			extreme = right;
		}

		if (extreme != position) {
			Util.swap(heap, position, extreme);
			heapify(extreme);
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}

		if (element != null) {
			index++;
			int i = index;

			while (i > 0 && comparator.compare(element, heap[parent(i)]) > 0) {
				heap[i] = heap[parent(i)];
				i = parent(i);
			}
			heap[i] = element;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void buildHeap(T[] array) {
		if (array != null && array.length > 0) {
			this.heap = Arrays.copyOf(array, Math.max(array.length, INITIAL_SIZE));
			this.index = array.length - 1;

			for (int i = index / 2; i >= 0; i--) {
				heapify(i);
			}
		} else {
			this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
			this.index = -1;
		}
	}

	@Override
	public T extractRootElement() {
		T max = null;

		if (index >= 0) {
			max = heap[0];
			heap[0] = heap[index];
			heap[index] = null;
			index--;

			if (index >= 0) {
				heapify(0);
			}
		}
		return max;
	}

	@Override
	public T rootElement() {
		T root = null;
		if (!isEmpty()) {
			root = heap[0];
		}
		return root;
	}

	@Override
	public T[] heapsort(T[] array) {
		T[] result = array;

		if (array != null && array.length > 0) {
			T[] oldHeap = Arrays.copyOf(heap, heap.length);
			int oldIndex = index;
			Comparator<T> oldComparator = comparator;

			buildHeap(array);

			int n = index;
			for (int i = n; i >= 1; i--) {
				Util.swap(heap, 0, i);
				index--;
				heapify(0);
			}

			T[] sortedArray = Arrays.copyOf(heap, array.length);

			if (array.length > 1 && sortedArray[0].compareTo(sortedArray[sortedArray.length - 1]) > 0) {
				for (int i = 0; i < sortedArray.length / 2; i++) {
					Util.swap(sortedArray, i, sortedArray.length - 1 - i);
				}
			}
			this.heap = oldHeap;
			this.index = oldIndex;
			this.comparator = oldComparator;

			result = sortedArray;
		}
		return result;
	}

	@Override
	public int size() {
		int size = 0;

		if (index >= 0) {
			size = index + 1;
		}
		return size;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}
}
