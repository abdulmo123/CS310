import java.util.AbstractList;

public class SortedArrayList<E> extends AbstractList<E> {

    private E[] storage;
    public int capacity;
    public int size;

    public SortedArrayList() { // default constructor
        capacity = 2;
        size = 0;
        storage = (E[]) new Object[capacity];
    }

    public SortedArrayList(int cpty) { // constructor taking w/ parameter cpty
        this.capacity = cpty;
        this.storage = (E[]) new Object[cpty];
    }

    public boolean add(E item) {
        if (size >= capacity) {
            E[] temp = (E[]) new Object[capacity * 2]; // double capacity when array is full

            for (int i = 0; i < capacity; i++) {
                temp[i] = storage[i]; // copy contents
            }

            storage = temp; // make storage point to new doubled array
            capacity *= 2; // double capacity
        }

        int j;
        for (j = size() - 1; j >= 0; j--) {

            if (((Comparable<E>) item).compareTo(storage[j]) <= 0) {
                storage[j + 1] = storage[j]; // position where to add element
                // while maintaining sorted order
            } else
                break;
        }

        storage[j + 1] = item; // insert item into correct position
        ++size;
        return true;
    }

    public int capacity() {
        return capacity; // return the current capacity
    }

    public void clear() {
        size = 0; // empties array, set size to 0
    }

    @Override
    public E get(int index) {
        if (size() == 0) {
            return null; // if size is empty, return null
        }

        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
            // if index out of range
        }

        return storage[index]; // otherwise, return element at index
    }

    public boolean isEmpty() {
        return size() == 0; // return true if array is empty, false otherwise
    }

    public E remove(int index) {
        E[] storage1 = (E[]) new Object[storage.length + 1]; // create array for removal
        // and copy over elements not being removed
        // item being removed won't be copied over to new array
        int i;
        for (i = 0; i <= size() - 1; ++i) {
            if (capacity >= size * 2) {
                E[] temp = (E[]) new Object[capacity / 2]; // double down size of array

                for (int j = 0; j < size; j++) {
                    temp[j] = storage[j]; // copy elements
                }

                storage = temp; // make storage point to new reduced array
                capacity /= 2; // reduced capacity by factor of 2
            }

            if (i == index) {
                continue;
            }

            if (i > index) {
                storage1[i - 1] = storage[i]; // inserting elements into new array
            }
            if (i < index) {
                storage1[i] = storage[i];
            }

        }
        --size;
        storage = storage1; // storage points to new array
        return storage[i];
    }

    @Override
    public int size() {
        return size;
    }
}
