import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.NoSuchElementException;

/**
* TeamSet to be implemented by students
* Note: Check the Java 8 API to make sure you are throwing
* the correct exceptions.
* @author cbaca3
* @version 1
* @param <T> generic type T
 */
public class TeamSet<T> implements Set<T>, Iterable<T> {
    private T[] backingArray;
    private int size;

    /**
     * Constructor should set an empty backingArray of size 10.
     */
    public TeamSet() {
        backingArray = (T[]) new Object[10];
    }

    /**
     * Adds the specified element to the end of this set if
     * it is not already present, and increments size. If
     * the element requires you to resize the backingArray,
     * double the current array length.
     * This method should return a boolean of whether or not the
     * element was added.
     * This should not support adding nulls. Consult the API.
     * @param t is a generic type t
     * @return true if you can add t, false if you can't
     */
    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException("Whoops, you can't do that!");
        }
        if (contains(t)) {
            return false;
        }
        if (size >= backingArray.length) {
            T[] tempArray = (T[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        backingArray[size] = t;
        size++;
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to
     * this set if they're not already present. You may also
     * need to resize the backing array to 2 * current size.
     * This method should return a boolean of whether or not any
     * of the elements were added.
     * This should not support adding nulls. Consult the API.
     * @param c a collection
     * @return true if elements were added, false if not
     */
    public boolean addAll(Collection<? extends T> c)
        throws NullPointerException {
        boolean addAllable = false;
        if (c == null) {
            addAllable = false;
            throw new NullPointerException("Whoops, you can't do that!");
        }
        for (T t : c) {
            if ((add(t))) {
                addAllable = true;
            }
        }
        return addAllable;
    }

    /**
     * Removes all of the elements from this set.
     * Should not use any form of loops, should be O(1).
     * Size should be reset to 0. Capacity should be reset to 10.
     */
    public void clear() {
        T[] nullArray = (T[]) new Object[10];
        backingArray = nullArray;
        size = 0;
    }

    /**
     * Returns true if this set contains the specified element.
     * @param o object
     * @return true if the element is in the set, false if not
     */
    public boolean contains(Object o) {
        boolean itsInHere = false;
        if (o == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (backingArray[i].equals(o) && backingArray[i] != null) {
                itsInHere = true;
            }
        } return itsInHere;
    }

    /**
     * Returns true if this set contains all of the elements of the
     * specified collection.
     * @param c collection
     * @return true if set contains all elements in the collection, c if not
     */
    public boolean containsAll(Collection<?> c)  { //for each loop,
        boolean here = true;
        if (c == null) {
            throw new NullPointerException();
        }
        for (Object o : c) {
            if (!contains(o)) {
                here = false;
            }
        }
        return here;
    }

    /**
     * Removes the specified element from this set if it is present,
     * and shifts all other elements up. Will return a boolean value
     * of whether or not the element has been removed.
     * @param o object
     * @return true if element has been removed, false if not
     */
    public boolean remove(Object o) {
        int counter = 0;
        boolean removable = false;
        if (o == null) {
            throw new NullPointerException("Whoops! You can't do that!");
        }
        if (!(contains(o))) {
            removable = false;
        } else {

            for (int i = 0; i < size; i++) {
                if (o.equals(backingArray[i])) {
                    counter++;
                    removable = true;
                    backingArray[i] = backingArray[counter];
                }
            }
        }
        return removable;
    }

    /**
    * Creates an inner class for an iterator method
    */
    class AnIterator<T> implements Iterator<T> {
        private T[] thing;
        private int index;

        /**
        * Creates a constructor for the class
        * @param thing an array of type T
        */
        public AnIterator(T[] thing) {
            this.thing = thing;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("You can't do that!");
            }
            return thing[index++];
        }
    }


    /**
     * Returns an iterator over the elements in this set.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return new AnIterator(backingArray);
    }

    /**
     * Compares the specified object with this set for equality.
     * Do not include order in your equality check.
     * ex: a set of {ARGENTINA, PERU, GERMANY} would be equivalent
     * to another set of {PERU, GERMANY, ARGENTINA}.
     * @param o object
     * @return true if the sets are equal, false if not
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeamSet)) {
            return false;
        }
        TeamSet t = (TeamSet) o;
        return containsAll(t) && (t.containsAll(this));
    }

    /**
     * Creates an array containing all of the elements in this set.
     * @return a copy array with all elements in the set
     */
    public Object[] toArray() {
        T[] temp = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            temp[i] = backingArray[i];
        }
        return temp;
    }

    /**
     * Should return the number of elements in the set.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Check if the set contains no elements.
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return (size == 0) ? true : false;
    }


    /* DO NOT EDIT ANYTHING BELOW THIS LINE,
       DOING SO WILL RESULT IN POINT DEDUCTIONS.
    */


    /**
     * @return the backing array, necessary for the tester.
     * DO NOT edit!
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * @return the sum of the hashcodes of the players in the set.
     */
    public int hashCode() {
        int sum = 0;
        for (T t : backingArray) {
            if (t != null) {
                sum += ((Player) t).hashCode();
            }
        }
        return sum;
    }

    /**
     * Retains only the elements in the set that are in the
     * specified collection, and removes everything else.
     * @param c a collection of all the elements to be retained
     * @return a boolean of whether the set was changed at all.
     */
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes every element in the specified collection.
     * @param c a collection of all the elements to be removed
     * @return a boolean of whether the set was changed.
     */
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an array containing all of the elements in this set.
     * The runtime type of the returned array is that of the
     * specified array.
     * @param a a generic array
     * @param <T> the runtime type of a
     * @return a generic array
     */
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
}
