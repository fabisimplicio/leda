package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);

			if (balance > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					LLcounter++;
					node = Util.rightRotation(node);
				} else {
					LRcounter++;
					node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
					node = Util.rightRotation(node);
				}
			} else if (balance < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					RRcounter++;
					node = Util.leftRotation(node);
				} else {
					RLcounter++;
					node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
					node = Util.leftRotation(node);
				}
			}

			if (node.getParent() == null) {
				root = node;
			}
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null) {
			for (T elem : array) {
				if (elem != null) {
					insertWithoutRebalance(elem);
				}
			}
		}
	}

	private void insertWithoutRebalance(T element) {
		if (root == null || root.isEmpty()) {
			root = new BSTNode<>();
			root.setData(element);
			root.setLeft(new BSTNode<>());
			root.setRight(new BSTNode<>());
			root.getLeft().setParent(root);
			root.getRight().setParent(root);
		} else {
			insertWithoutRebalance(element, root);
		}
	}

	private void insertWithoutRebalance(T element, BSTNode<T> node) {
		if (element.compareTo(node.getData()) < 0) {
			if (node.getLeft().isEmpty()) {
				BSTNode<T> newNode = new BSTNode<>();
				newNode.setData(element);
				newNode.setLeft(new BSTNode<>());
				newNode.setRight(new BSTNode<>());
				newNode.setParent(node);
				node.setLeft(newNode);
			} else {
				insertWithoutRebalance(element, (BSTNode<T>) node.getLeft());
			}
		} else if (element.compareTo(node.getData()) > 0) {
			if (node.getRight().isEmpty()) {
				BSTNode<T> newNode = new BSTNode<>();
				newNode.setData(element);
				newNode.setLeft(new BSTNode<>());
				newNode.setRight(new BSTNode<>());
				newNode.setParent(node);
				node.setRight(newNode);
			} else {
				insertWithoutRebalance(element, (BSTNode<T>) node.getRight());
			}
		}
	}

}
