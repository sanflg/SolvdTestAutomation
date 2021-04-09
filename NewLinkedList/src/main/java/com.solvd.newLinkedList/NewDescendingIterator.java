package com.solvd.newLinkedList;

import java.util.Iterator;

class NewDescendingIterator<E> implements Iterator<E> {
    private final NewIterator<E> itr;

    public NewDescendingIterator(int index, NewLinkedList<E> linkedList){
        this.itr = (NewIterator<E>) linkedList.listIterator();
    }

    public boolean hasNext() {
        return itr.hasPrevious();
    }
    public E next() {
        return itr.previous();
    }
    public void remove() {
        itr.remove();
    }
}