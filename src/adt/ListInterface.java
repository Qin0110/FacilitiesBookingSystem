package adt;
// Referring to lecture note for interface method initialization

public interface ListInterface<T> {
    public boolean add(T value);
    public boolean add (int index, T element);
    public void set (int index, T element);
    public int indexOf (Object o);
    public void clear();
    public boolean contains (Object o);
    public T get (int index);
    public boolean remove (int index);
    public boolean isEmpty();
    public int size();
    public boolean isFull();
}
