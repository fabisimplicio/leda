package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

    @Override
    public void insert(T element) {
        if (element != null) {
            if (isFull()) {
                throw new HashtableOverflowException();
            }
        
            int probe = 0;
            boolean inserted = false;
            boolean elementExists = false;
        
            while (probe < table.length && !inserted && !elementExists) {
                int hash = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, probe);
            
                if (table[hash] == null || table[hash].equals(deletedElement)) {
                    table[hash] = element;
                    elements++;
                    inserted = true;
                } else if (table[hash].equals(element)) {
                    elementExists = true;
                } else {
                    COLLISIONS++;
                    probe++;
                }
            }
        
            if (!inserted && !elementExists) {
                throw new HashtableOverflowException();
            }
        }
    }

	@Override
	public void remove(T element) {
        if (element != null && !isEmpty()) {
            int probe = 0;
            int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
            
            while (probe < table.length && table[hash] != null) {
                if (table[hash].equals(element)) {
                    table[hash] = deletedElement;
                    elements--;
                    probe = table.length; 
                } else {
                    probe++;
                    hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
                }
            }
        }
    }

	@Override
	@SuppressWarnings("unchecked")
	public T search(T element) {
        T result = null;
        
        if (element != null && !isEmpty()) {
            int probe = 0;
            int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
            
            while (probe < table.length && table[hash] != null && result == null) {
                if (table[hash].equals(element)) {
                    result = (T) table[hash];
                } else {
                    probe++;
                    hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
                }
            }
        }
        
        return result;
    }

	@Override
    public int indexOf(T element) {
        int index = -1;
        
        if (element != null && !isEmpty()) {
            int probe = 0;
            int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
            
            while (probe < table.length && table[hash] != null && index == -1) {
                if (table[hash].equals(element)) {
                    index = hash;
                } else {
                    probe++;
                    hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
                }
            }
        }
        
        return index;
    }
}
