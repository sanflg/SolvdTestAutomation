package com.solvd.newLinkedList;

import java.util.*;
import java.util.function.Consumer;

public class NewLinkedList<E> extends AbstractList<E> implements List<E>
{
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public NewLinkedList() {
    }

    public int size() {
        return size;
    }

    public int modCount() {
        return modCount;
    }

    public E getFirst() { ;
        return first.getItem();
    }

    public E getLast() {
        return last.getItem();
    }

    public Node<E> getFirstNode() { ;
        return first;
    }

    public Node<E> getLastNode() {
        return last;
    }

    public NewLinkedList(Collection<? extends E> c) {
        this();
        addAll(size,c);
    }

    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.getPrev();
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.setPrev(newNode);
        if (pred == null)
            first = newNode;
        else
            pred.setNext(newNode);
        size++;
        modCount++;
    }

    private E unlinkFirst(Node<E> f) {
        final E element = f.getItem();
        final Node<E> next = f.getNext();
        f.setItem(null);
        f.setNext(null);
        first = next;
        if (next == null)
            last = null;
        else
            next.setPrev(null);
        size--;
        modCount++;
        return element;
    }

    private E unlinkLast(Node<E> l) {
        final E element = l.getItem();
        final Node<E> prev = l.getPrev();
        l.setItem(null);
        l.setPrev(null);
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.setNext(null);
        size--;
        modCount++;
        return element;
    }

    E unlink(Node<E> x) {
        final E element = x.getItem();
        final Node<E> next = x.getNext();
        final Node<E> prev = x.getPrev();

        if (prev == null) {
            first = next;
        } else {
            prev.setNext(next);
            x.setPrev(null);
        }

        if (next == null) {
            last = prev;
        } else {
            next.setPrev(prev);
            x.setNext(null);
        }

        x.setItem(null);
        size--;
        modCount++;
        return element;
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    public void addFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.setPrev(newNode);
        size++;
        modCount++;
    }

    public void addLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.setNext(newNode);
        size++;
        modCount++;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.getNext()) {
                if (x.getItem() == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getItem())) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> c){
        addAll(size, c);
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.getPrev();
        }

        for (Object o : a) {
            E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.setNext(newNode);
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.setNext(succ);
            succ.setPrev(pred);
        }

        size += numNew;
        modCount++;
        return true;
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).getItem();
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.getItem();
        x.setItem(element);
        return oldVal;
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    Node<E> node(int index) {

        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.getNext();
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.getPrev();
        }
        return x;
    }

    public NewIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new NewIterator<E>(index, this);
    }

    public NewIterator<E> listIterator() {
        checkPositionIndex(size);
        return new NewIterator<E>(size, this);
    }

    public NewDescendingIterator<E> descendingIterator(int index) {
        checkPositionIndex(index);
        return new NewDescendingIterator<>(index,this);
    }

    public NewDescendingIterator<E> descendingIterator() {
        checkPositionIndex(size);
        return new NewDescendingIterator<>(size,this);
    }
}
