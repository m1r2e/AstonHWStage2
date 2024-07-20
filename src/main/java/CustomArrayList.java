
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    transient Object[] elementData;
    private int size;

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

        final Object[] es = elementData;
        for (int i = 0; i < size; i++) {
            es[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (size == elementData.length)
            ensureCapacity(size + 1);
        elementData[size] = e;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacity(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elementData[index];
        int newSize = size - 1;

        fastRemove(index);

        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        Objects.requireNonNull(c);
        Arrays.sort((E[]) elementData, 0, size, c);

    }

    private void checkIndex(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1); // 1.5 times
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
}
