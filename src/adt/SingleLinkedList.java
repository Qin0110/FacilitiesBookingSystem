
package adt;
import java.io.Serializable;

public class SingleLinkedList<T> implements ListInterface<T>, Serializable{
    private Node firstNode;
    private int numberOfEntries;  	
    
    private class Node implements java.io.Serializable {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }


    @Override
    public boolean add(T element) {
        Node newNode = new Node (element);
        
        if(isEmpty()){
            firstNode = newNode;
        }else{
            Node lastNode = getNodeAt(numberOfEntries - 1); 
            lastNode.next = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if(index >= 0 && index <= numberOfEntries){
            Node newNode = new Node (element);
            
            if(index == 0){
                newNode.next = firstNode;
                firstNode = newNode;
            }else{
                Node nodeBfr = getNodeAt(index - 1);
                Node nodeAfr = nodeBfr.next;
                
                newNode.next = nodeAfr;
                nodeBfr.next = newNode;
            }
            numberOfEntries++;
            return true;
        }
        return false;    
    }
    
    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node curNode = firstNode;

        while (curNode != null) {
            if (curNode.data.equals(o)) {
                return index; 
            }
            curNode = curNode.next;
            index++;
        }
        return -1; 
    }
    
    
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < numberOfEntries) {
            return getNodeAt(index).data;
        }
        return null;
    }
    
    
    @Override
    public boolean   set(int index, T element) {
        if (index >= 0 && index < numberOfEntries ) {
            Node currentNode = getNodeAt(index);
            currentNode.data = element; 
            return true;
        }
        return false;
    }
    
    @Override
    public boolean remove(int index) {
        if (index >= 0 && index < numberOfEntries) {
            if (index == 0) {
                firstNode = firstNode.next;
            } else {
                Node nodeBfr = getNodeAt(index - 1);
                Node nodeToRemove = nodeBfr.next;
                nodeBfr.next = nodeToRemove.next; 
                nodeToRemove.next = null;
            }
            numberOfEntries--;
            return true; 
        }
        return false; 
    }
    
    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }
 

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public int size() {
        return numberOfEntries;
    }
    
    private Node getNodeAt(int index) {
        if (index < 0 || index >= numberOfEntries) {
            return null; 
        }

        Node curNode = firstNode;
        for (int counter = 0; counter < index; counter++) {
            curNode = curNode.next;
        }
        return curNode;
    }
    
    @Override
    public String toString(){
        if (this.firstNode == null) {
            return "[]";
        }
        else {
            StringBuilder sb = new StringBuilder();
            Node currentNode = this.firstNode;
            sb.append("[");
            while (currentNode != null){
                sb.append(currentNode.data);
                if (currentNode.next != null){
                    sb.append(", ");
                }
                currentNode = currentNode.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }
    
    @Override
    public void sort(Comparator<T> comparator) {
        if (firstNode == null || firstNode.next == null) {
            return;
        }
        firstNode = mergeSort(firstNode, comparator);
    }
    
    private Node mergeSort(Node h, Comparator<T> c) {
        if (h == null || h.next == null) {
            return h;
        }

        Node middle = getMiddle(h);
        Node nextOfMiddle = middle.next;
        
        middle.next = null; 

        Node left = mergeSort(h, c);
        Node right = mergeSort(nextOfMiddle, c);

        return sortedMerge(left, right, c);
    }

    private Node sortedMerge(Node a, Node b, Comparator<T> c) {
        Node result = null;
        
        if (a == null) 
            return b;
        if (b == null) 
            return a;

        if (c.compare(a.data, b.data) <= 0) {
            result = a;
            result.next = sortedMerge(a.next, b, c);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, c);
        }
        return result;
    }

    private Node getMiddle(Node h) {
        if (h == null) return h;
        
       
        Node slow = h, fast = h;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
