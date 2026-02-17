package adt;

// <T> meaning: <> generic data type, inside <> is a parameter holder, T = Type.
// Generic data type can represent any Primitive Data Type: String, int...

import java.io.Serializable;

public class ArrayList<T> implements ListInterface<T>, Serializable {
    private T[] array;
    private int numberOfEntries; // Current Array Value contains.
    private static final int DEFAULT_CAPACITY = 5;
    
    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayList(int arrayCapacity){
        numberOfEntries = 0;
        array = (T[]) new Object[arrayCapacity];
    }
    
    public boolean add(T newValue){
        if(this.isFull()){
            this.doubleArray();
        }
        array[numberOfEntries] = newValue;
        numberOfEntries++;
        return true;
    }
    
    public boolean add(int index, T newValue){
        boolean successful = true;
        if((index >= 0) && (index <= numberOfEntries + 1)){
            if(this.isFull()){
                this.doubleArray();
            }
            for (int i = numberOfEntries; i > index; i--){
                array[i] = array[i - 1];
            }
            array[index] = newValue;
            numberOfEntries++;
            return true;
        } 
        else{
            successful = false;
        }
        return successful;
    }
    
    public void clear(){
        numberOfEntries = 0;
    }
    
    public boolean contains(Object o){
        boolean indicator = false;
        for(int i = 0; i < this.size(); i++){
            if(array[i].equals(o)){
                indicator = true;
                break;
            }
        }
        return indicator;
    }
    
    public int indexOf (Object o){
        int index_count = -1;
        for(int i = 0; i < this.size(); i++){
            if(array[i].equals(o)){
                index_count = i;
                break;
            }
        }
        return index_count;
    }
    
    public T get(int index){
        return array[index];
    }
    
    public void set (int index, T element){
        array[index] = element;
    }
    
    public boolean isEmpty(){
        return (this.size() == 0);
    }
    
    public boolean isFull(){
        return numberOfEntries == array.length;
    }
    
    public boolean remove(int index){
        // T removal_value = array[index];
        if (index < 0 || index >= numberOfEntries) {
            return false;
        }
        
        for(int i = index; i < this.size() - 1; i++){
            array[i] = array[i + 1];
        }
        
        numberOfEntries--; 
        array[numberOfEntries] = null; // remove the deleted stuff

        return true;
    }
    
    
    // Maybe here can be optimize
    // Problem: When earlier index dosen't have a value but later index have, does it still count as size?++
    public int size(){
        return numberOfEntries;
    }
    
    private void doubleArray(){
        T[] oldArray = array;
        array = (T[]) new Object[oldArray.length * 2];
        for (int i = 0; i < oldArray.length; i++){
            array[i] = oldArray[i];
        }
    }
    
    public String toString(){
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
          outputStr += array[index] + "\n";
        }

        return outputStr;
    }
}
