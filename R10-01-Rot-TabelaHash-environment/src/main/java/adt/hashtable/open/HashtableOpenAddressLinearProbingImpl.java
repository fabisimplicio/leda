package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
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
                int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
            
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
        boolean shouldRemove = element != null && !isEmpty();
    
        if (shouldRemove) {
            int probe = 0;
            boolean found = false;
            boolean shouldStop = false;
        
            while (probe < table.length && !found && !shouldStop) {
                int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
            
                if (table[hash] == null) {
                    shouldStop = true;
                } else if (table[hash].equals(element)) {
                    table[hash] = deletedElement;
                    elements--;
                    found = true;
                } else {
                    probe++;
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
            int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
            boolean found = false;
            
            while (probe < table.length && table[hash] != null && !found) {
                if (table[hash].equals(element)) {
                    result = (T) table[hash];
                    found = true;
                } else {
                    probe++;
                    hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
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
            int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
            boolean found = false;
            
            while (probe < table.length && table[hash] != null && !found) {
                if (table[hash].equals(element)) {
                    index = hash;
                    found = true;
                } else {
                    probe++;
                    hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
                }
            }
        }
        
        return index;
    }
}

