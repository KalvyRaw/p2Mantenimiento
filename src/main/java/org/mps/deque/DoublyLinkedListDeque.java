package org.mps.deque;

public class DoublyLinkedListDeque<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoublyLinkedListDeque() {
        // TODO
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void prepend(T value) {
        // TODO
        DequeNode<T> newNode = new DequeNode<>(value, null, first);
        if (first == null) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    @Override
    public void append(T value) {
        // TODO
        DequeNode<T> newNode = new DequeNode<>(value, last, null);
        if (last == null) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    @Override
    public void deleteFirst() {
        // TODO
        if (first == null) {
            throw new DoubleEndedQueueException("Error al eliminar el primer elemento, no tiene primero");
        } else {
            first = first.getNext();
            if (first != null) {
                first.setPrevious(null);
            } else {
                last = null;
            }
            size--;
        }
    }

    @Override
    public void deleteLast() {
        // TODO
       if (last == null) {
           throw new DoubleEndedQueueException("Error al eliminar el último elemento, no tiene último");

       } else {
            last = last.getPrevious();
            if (last != null) {
                last.setNext(null);
            } else {
                first = null;
            }
            size--;
        }
    }

    @Override
    public T first() {
        // TODO
        if (first == null) {
            throw new DoubleEndedQueueException("Error al conseguir el primer elemento, la lista está vacía");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        // TODO
        if (last == null) {
            throw new DoubleEndedQueueException("Error al conseguir el primer elemento, la lista está vacía");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        // TODO
        return this.size;
    }
}
