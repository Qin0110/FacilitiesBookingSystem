package adt;

public interface ListInterface<T> {
    public boolean add(T element);
    public boolean add (int index, T element);
    public int indexOf (Object o);
    public boolean contains (Object o);
    public T get (int index);
    public boolean set (int index, T element);
    public boolean remove (int index); 
    public void clear();
    public boolean isEmpty();
    public int size();
    public void sort(Comparator<T> comparator);
}
