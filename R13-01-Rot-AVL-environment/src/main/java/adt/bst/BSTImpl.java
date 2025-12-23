package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(BSTNode<T> node) {
		int result = -1;
		if (node != null && !node.isEmpty()) {
			result = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {

		BSTNode<T> result = new BSTNode<>();

		if (element != null && node != null && !node.isEmpty()) {
			if (element.equals(node.getData())) {
				result = node;
			} else if (element.compareTo(node.getData()) < 0) {
				result = search((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				result = search((BSTNode<T>) node.getRight(), element);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}

	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		if (!this.isEmpty()) {
			result = maximum(root);
		}
		return result;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (!node.getRight().isEmpty()) {
			result = maximum((BSTNode<T>) node.getRight());
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		if (!this.isEmpty()) {
			result = minimum(root);
		}
		return result;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (!node.getLeft().isEmpty()) {
			result = minimum((BSTNode<T>) node.getLeft());
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;

		if (element != null) {
			BSTNode<T> node = search(element);

			if (node != null && !node.isEmpty()) {
				if (!node.getRight().isEmpty()) {
					result = minimum((BSTNode<T>) node.getRight());
				} else {
					result = sucessor(node);
				}
			}
		}

		return result;
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> result = null;

		if (node.getParent() != null && !node.getParent().isEmpty()) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			if (node == parent.getLeft()) {
				result = parent;
			} else {
				result = sucessor(parent);
			}
		}

		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;

		if (element != null) {
			BSTNode<T> node = search(element);

			if (node != null && !node.isEmpty()) {
				if (!node.getLeft().isEmpty()) {
					result = maximum((BSTNode<T>) node.getLeft());
				} else {
					result = predecessor(node);
				}
			}
		}

		return result;
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> result = null;

		if (node.getParent() != null && !node.getParent().isEmpty()) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			if (node == parent.getRight()) {
				result = parent;
			} else {
				result = predecessor(parent);
			}
		}

		return result;
	}

	@Override
	public void remove(T element) {
		if (element != null && root != null && !root.isEmpty()) {
			BSTNode<T> node = search(element);
			if (node != null && !node.isEmpty()) {
				remove(node);
			}
		}
	}

	private void remove(BSTNode<T> node) {

		boolean hasLeft = node.getLeft() != null && !node.getLeft().isEmpty();
		boolean hasRight = node.getRight() != null && !node.getRight().isEmpty();

		// nó folha
		if (!hasLeft && !hasRight) {
			if (node == root) {
				root = new BSTNode<>();
			} else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				BSTNode<T> emptyNode = new BSTNode<>();
				emptyNode.setParent(parent);

				if (parent.getLeft() == node) {
					parent.setLeft(emptyNode);
				} else {
					parent.setRight(emptyNode);
				}
			}
		}
		// nó com apenas um filho
		else if (hasLeft != hasRight) {
			BSTNode<T> child;
			if (hasLeft) {
				child = (BSTNode<T>) node.getLeft();
			} else {
				child = (BSTNode<T>) node.getRight();
			}

			if (node == root) {
				root = child;
				child.setParent(null);
			} else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				if (parent.getLeft() == node) {
					parent.setLeft(child);
				} else {
					parent.setRight(child);
				}
				child.setParent(parent);
			}
		}
		// nó com dois filhos
		else {
			BSTNode<T> successor = minimum((BSTNode<T>) node.getRight());
			node.setData(successor.getData());
			remove(successor);
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		int[] index = { 0 };
		preOrder(array, index, root);
		return array;
	}

	private void preOrder(T[] array, int[] index, BTNode<T> node) {
		if (!node.isEmpty()) {
			array[index[0]++] = node.getData();
			preOrder(array, index, node.getLeft());
			preOrder(array, index, node.getRight());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		int[] index = { 0 };
		order(array, index, root);
		return array;
	}

	private void order(T[] array, int[] index, BTNode<T> node) {
		if (!node.isEmpty()) {
			order(array, index, node.getLeft());
			array[index[0]++] = node.getData();
			order(array, index, node.getRight());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		int[] index = { 0 };
		postOrder(array, index, root);
		return array;
	}

	private void postOrder(T[] array, int[] index, BTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(array, index, node.getLeft());
			postOrder(array, index, node.getRight());
			array[index[0]++] = node.getData();
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
