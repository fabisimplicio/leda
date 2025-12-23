package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// Do not forget: you must override the methods insert and remove
	// conveniently.

	   // AUXILIARY
    protected int calculateBalance(BSTNode<T> node) {
        int balance = 0;
        if (node != null && !node.isEmpty()) {
            int leftHeight = height((BSTNode<T>) node.getLeft());
            int rightHeight = height((BSTNode<T>) node.getRight());
            balance = leftHeight - rightHeight;
        }
        return balance;
    }

    // AUXILIARY
    protected void rebalance(BSTNode<T> node) {
        if (node == null || node.isEmpty()) {
            return;
        }
        
        int balance = calculateBalance(node);
        
        // Left Heavy
        if (balance > 1) {
            if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
                rightRotation(node);
            } else {
                leftRotation((BSTNode<T>) node.getLeft());
                rightRotation(node);
            }
        }
        // Right Heavy
        else if (balance < -1) {
            if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
                leftRotation(node);
            } else {
                rightRotation((BSTNode<T>) node.getRight());
                leftRotation(node);
            }
        }
    }

    // AUXILIARY
    protected void rebalanceUp(BSTNode<T> node) {
        BSTNode<T> current = node;
        while (current != null) {
            rebalance(current);
            current = (BSTNode<T>) current.getParent();
        }
    }
    
    private void leftRotation(BSTNode<T> node) {
        BSTNode<T> rightChild = (BSTNode<T>) node.getRight();
        
        node.setRight(rightChild.getLeft());
        if (rightChild.getLeft() != null) {
            rightChild.getLeft().setParent(node);
        }
        
        rightChild.setParent(node.getParent());
        
        if (node.getParent() == null) {
            root = rightChild;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(rightChild);
        } else {
            node.getParent().setRight(rightChild);
        }
        
        rightChild.setLeft(node);
        node.setParent(rightChild);
    }
    
    private void rightRotation(BSTNode<T> node) {
        BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();
        
        node.setLeft(leftChild.getRight());
        if (leftChild.getRight() != null) {
            leftChild.getRight().setParent(node);
        }
        
        leftChild.setParent(node.getParent());
        
        if (node.getParent() == null) {
            root = leftChild;
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(leftChild);
        } else {
            node.getParent().setLeft(leftChild);
        }
        
        leftChild.setRight(node);
        node.setParent(leftChild);
    }

	@Override
	public void insert(T element) {
		// Garantia que não irá inserer elementos repetidos dentro da AVL
		BSTNode<T> search = search(element);
		if (search.isEmpty()) {
			recursiveInsert(getRoot(), element);
		}
	}

	private void recursiveInsert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);

			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);

			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(element) > 0) {
				recursiveInsert((BSTNode<T>) node.getLeft(), element);
			} else if (node.getData().compareTo(element) < 0) {
				recursiveInsert((BSTNode<T>) node.getRight(), element);
			}
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {	
			BSTNode<T> node = search(element);
			if (!node.isEmpty()) {
				remove(node);
			}
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			if (node.getParent() == null) {
				root = new BSTNode<T>();
			} else {
				if (node.getData().compareTo(node.getParent().getData()) < 0) {
					node.getParent().setLeft(new BSTNode<T>());
				} else {
					node.getParent().setRight(new BSTNode<T>());
				}
				rebalanceUp(node);
			}

		} else if (node.getRight().isEmpty()) {
			if (node.getParent() == null) {
				root = (BSTNode<T>) root.getLeft();
				root.setParent(null);

			} else {
				node.getLeft().setParent(node.getParent());
				if (node.getData().compareTo(node.getParent().getData()) < 0) {
					node.getParent().setLeft(node.getLeft());
				} else {
					node.getParent().setRight(node.getLeft());
				}
			}
			rebalanceUp(node);

		} else if (node.getLeft().isEmpty()) { 
			if (node.getParent() == null) {
				root = (BSTNode<T>) root.getRight();
				root.setParent(null);

			} else {
				node.getRight().setParent(node.getParent());
				if (node.getData().compareTo(node.getParent().getData()) < 0) {
					node.getParent().setLeft(node.getRight());
				} else {
					node.getParent().setRight(node.getRight());
				}
			}
			rebalanceUp(node);

		} else {
			BSTNode<T> sucessor = sucessor(node.getData());
			node.setData(sucessor.getData());
			remove(sucessor);
		} 
	}

	
	private int height(BSTNode<T> node) {
		int tempLeft = -1;
		int tempRight = -1;

		if (node != null && !node.isEmpty()) {
			tempLeft = 1 + height((BSTNode<T>) node.getLeft());
			tempRight = 1 + height((BSTNode<T>) node.getRight());
		}
		
		return Math.max(tempLeft, tempRight);
	}

		@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		int[] index = { 0 };
		preOrder(array, index, root);
		return array;
	}

	private void preOrder(T[] array, int[] index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[index[0]++] = node.getData();
			preOrder(array, index, (BSTNode<T>) node.getLeft());
			preOrder(array, index, (BSTNode<T>) node.getRight());
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

	private void postOrder(T[] array, int[] index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(array, index, (BSTNode<T>) node.getLeft());
			postOrder(array, index, (BSTNode<T>) node.getRight());
			array[index[0]++] = node.getData();
		}
	}

}