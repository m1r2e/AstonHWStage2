import java.util.Collection;
import java.util.Comparator;

public interface CustomList<E> {

    int size();

    boolean isEmpty();

    void clear();
    boolean add(E e);

    void add(int index, E element);

    boolean addAll(Collection< ? extends E> c);

    E get(int index);

    E remove(int index);

    boolean remove(Object o);

    void sort(Comparator<? super E> c);

}
