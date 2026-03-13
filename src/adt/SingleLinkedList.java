/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class SingleLinkedList<T> implements ListInterface<T> {
    private Node firstNode;
    private int numberOfEntries;  	
    
    private class Node {
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
    
}
