package com.solvd.newLinkedList;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

class NewIterator<E> implements ListIterator<E> {
    private Node<E> lastReturned;
    private Node<E> next;
    private NewLinkedList<E> linkedList;
    private int nextIndex;
    private int expectedModCount = linkedList.modCount();

    NewIterator(int index, NewLinkedList<E> linkedList) {
        this.linkedList = linkedList;
        next = (index == linkedList.size()) ? null : linkedList.node(index);
        nextIndex = index;
    }

    public boolean hasNext() {
        return nextIndex < linkedList.size();
    }

    public E next() {
        checkForComodification();
        if (!hasNext())
            throw new NoSuchElementException();

        lastReturned = next;
        next = next.next;
        nextIndex++;
        return lastReturned.item;
    }

    public boolean hasPrevious() {
        return nextIndex > 0;
    }

    public E previous() {
        checkForComodification();
        if (!hasPrevious())
            throw new NoSuchElementException();

        lastReturned = next = (next == null) ? linkedList.getLastNode() : next.prev;
        nextIndex--;
        return lastReturned.item;
    }

    public int nextIndex() {
        return nextIndex;
    }

    public int previousIndex() {
        return nextIndex - 1;
    }

    public void remove() {
        checkForComodification();
        if (lastReturned == null)
            throw new IllegalStateException();

        Node<E> lastNext = lastReturned.next;
        linkedList.unlink(lastReturned);
        if (next == lastReturned)
            next = lastNext;
        else
            nextIndex--;
        lastReturned = null;
        expectedModCount++;
    }

    public void set(E e) {
        if (lastReturned == null)
            throw new IllegalStateException();
        checkForComodification();
        lastReturned.item = e;
    }

    public void add(E e) {
        checkForComodification();
        lastReturned = null;
        if (next == null)
            linkedList.addLast(e);
        else
            linkedList.linkBefore(e, next);
        nextIndex++;
        expectedModCount++;
    }

    public void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (linkedList.modCount() == expectedModCount && nextIndex < linkedList.size()) {
            action.accept(next.item);
            lastReturned = next;
            next = next.next;
            nextIndex++;
        }
        checkForComodification();
    }

    final void checkForComodification() {
        if (linkedList.modCount() != expectedModCount)
            throw new ConcurrentModificationException();
    }
}