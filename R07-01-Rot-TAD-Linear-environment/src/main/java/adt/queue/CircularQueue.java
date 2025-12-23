package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new QueueOverflowException();
			}
			if (isEmpty()) {
				head = 0;
				tail = 0;
			} else {
				tail = (tail + 1) % array.length;
			}
			array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T resultado = null;

		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			resultado = array[head];
			array[head] = null;
			elements--;
			if (elements == 0) {
				head = -1;
				tail = -1;
			} else {
				head = (head + 1) % array.length;
			}
		}
		return resultado;
	}

	@Override
	public T head() {
		T resultado = null;
		if (!isEmpty()) {
			resultado = array[head];
		}
		return resultado;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}

}
