package com.solvd.newLinkedList;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

class NewIterator<E> implements ListIterator<E> {
    private Node<E> lastReturned;
    private Node<E> next;
    private NewLinkedList<E> linkedList;
    private int nextIndex;

    NewIterator(int index, NewLinkedList<E> linkedList) {
        this.linkedList = linkedList;
        next = (index == linkedList.size()) ? null : linkedList.node(index);
        nextIndex = index;
    }

    public boolean hasNext() {
        return nextIndex < linkedList.size();
    }

    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();

        lastReturned = next;
        next = next.getNext();
        nextIndex++;
        return lastReturned.getItem();
    }

    public boolean hasPrevious() {
        return nextIndex > 0;
    }

    public E previous() {
        if (!hasPrevious())
            throw new NoSuchElementException();

        lastReturned = next = (next == null) ? linkedList.getLastNode() : next.getPrev();
        nextIndex--;
        return lastReturned.getItem();
    }

    public int nextIndex() {
        return nextIndex;
    }

    public int previousIndex() {
        return nextIndex - 1;
    }

    public void remove() {
        if (lastReturned == null)
            throw new IllegalStateException();

        Node<E> lastNext = lastReturned.getNext();
        linkedList.unlink(lastReturned);
        if (next == lastReturned)
            next = lastNext;
        else
            nextIndex--;
        lastReturned = null;
    }

    public void set(E e) {
        if (lastReturned == null)
            throw new IllegalStateException();
        lastReturned.setItem(e);
    }

    public void add(E e) {
        lastReturned = null;
        if (next == null)
            linkedList.addLast(e);
        else
            linkedList.linkBefore(e, next);
        nextIndex++;
    }

    public void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (nextIndex < linkedList.size()) {
            action.accept(next.getItem());
            lastReturned = next;
            next = next.getNext();
            nextIndex++;
        }
    }

}