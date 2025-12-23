package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T resultado = null;
		if (!isEmpty()) {
			resultado = array[0];
		}
		return resultado;
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail == array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
		array[tail] = null;
		tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {		
 		if (isFull()) {
			throw new QueueOverflowException();
		} 
		if (element != null) {
			tail++;
			array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T resultado;
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			resultado = array[0];
			shiftLeft();
		}
		return resultado;
	}

}
