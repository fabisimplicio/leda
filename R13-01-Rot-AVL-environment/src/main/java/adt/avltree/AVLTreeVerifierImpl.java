package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

    @Override
    public boolean isAVLTree() {
        boolean result = false;
        if (isBST()) {
            result = isBalanced(avlTree.getRoot());
        }
        return result;
    }

    /**
     * Verifica se a árvore está balanceada (propriedade AVL)
     */
    private boolean isBalanced(BSTNode<T> node) {
        boolean result = true;
        if (node != null && !node.isEmpty()) {
            int balance = calculateBalance(node);
            boolean currentBalanced = (balance >= -1 && balance <= 1);
            
            boolean leftBalanced = isBalanced((BSTNode<T>) node.getLeft());
            boolean rightBalanced = isBalanced((BSTNode<T>) node.getRight());
            
            result = currentBalanced && leftBalanced && rightBalanced;
        }
        return result;
    }

    /**
     * Calcula o fator de balanceamento de um nó
     */
    private int calculateBalance(BSTNode<T> node) {
        int balance = 0;
        if (node != null && !node.isEmpty()) {
            int leftHeight = height((BSTNode<T>) node.getLeft());
            int rightHeight = height((BSTNode<T>) node.getRight());
            balance = leftHeight - rightHeight;
        }
        return balance;
    }

    /**
     * Calcula a altura de um nó
     */
    private int height(BSTNode<T> node) {
        int result = -1;
        if (node != null && !node.isEmpty()) {
            int leftHeight = height((BSTNode<T>) node.getLeft());
            int rightHeight = height((BSTNode<T>) node.getRight());
            result = 1 + Math.max(leftHeight, rightHeight);
        }
        return result;
    }

}
