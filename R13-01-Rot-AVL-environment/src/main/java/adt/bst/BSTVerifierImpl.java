package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}


    @Override
    public boolean isBST() {
        boolean result = false;
        if (bst != null) {
            result = isBST(bst.getRoot(), null, null);
        }
        return result;
    }

    /**
     * Método recursivo auxiliar para verificar se é uma BST válida
     */
    private boolean isBST(BSTNode<T> node, T min, T max) {
        boolean result = true;
        
        if (node != null && !node.isEmpty()) {
            T data = node.getData();
            
            // Verifica se o valor está dentro dos limites permitidos
            boolean validMin = (min == null || data.compareTo(min) > 0);
            boolean validMax = (max == null || data.compareTo(max) < 0);
            
            if (!validMin || !validMax) {
                result = false;
            } else {
                // Verifica recursivamente as subárvores
                boolean leftValid = isBST((BSTNode<T>) node.getLeft(), min, data);
                boolean rightValid = isBST((BSTNode<T>) node.getRight(), data, max);
                result = leftValid && rightValid;
            }
        }
        return result;
    }
}
