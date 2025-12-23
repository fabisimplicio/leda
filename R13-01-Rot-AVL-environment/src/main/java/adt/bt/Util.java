package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (node != null && !node.isEmpty() && node.getRight() != null && !node.getRight().isEmpty()) {
			result = rotateLeft(node);
		}
		return result;
	}

	private static <T extends Comparable<T>> BSTNode<T> rotateLeft(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		node.setRight(right.getLeft());
		if (right.getLeft() != null) {
			right.getLeft().setParent(node);
		}
		right.setLeft(node);

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		right.setParent(parent);
		node.setParent(right);

		if (parent != null) {
			if (parent.getLeft() == node) {
				parent.setLeft(right);
			} else {
				parent.setRight(right);
			}
		}
		return right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (node != null && !node.isEmpty() && node.getLeft() != null && !node.getLeft().isEmpty()) {
			result = rotateRight(node);
		}
		return result;
	}

	private static <T extends Comparable<T>> BSTNode<T> rotateRight(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		node.setLeft(left.getRight());
		if (left.getRight() != null) {
			left.getRight().setParent(node);
		}
		left.setRight(node);

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		left.setParent(parent);
		node.setParent(left);

		if (parent != null) {
			if (parent.getLeft() == node) {
				parent.setLeft(left);
			} else {
				parent.setRight(left);
			}
		}
		return left;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
