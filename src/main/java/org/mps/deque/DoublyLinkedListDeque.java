package org.mps.deque;

import java.util.Comparator;

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

    @Override
    public T get(int index) {
        DequeNode<T> current;
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Índice incorrecto");
        else {
            if(index < size/2) {
                current = first;
                for (int i=0; i<index;i++) {
                    current = current.getNext();
                }
            } else {
                current = last;
                for (int i = size - 1; i>index ;i--) {
                    current = current.getPrevious();
                }
            }
        }
        return current.getItem();
    }

    @Override
    public boolean contains(T value) {
        DequeNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    @Override
    public void remove(T value) {
        DequeNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                if (current == first) {
                    deleteFirst();
                } else if (current == last) {
                    deleteLast();
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    size--;
                }
                return;
            }
            current = current.getNext();
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size > 1) {
            DequeNode<T> current = first.getNext();
            while (current != null) {
                T item = current.getItem();
                DequeNode<T> temp = current.getPrevious();
                while (temp != null && comparator.compare(temp.getItem(), item) > 0) {
                    temp.getNext().setItem(temp.getItem());
                    temp = temp.getPrevious();
                }
                if (temp == null) {
                    first.setItem(item);
                } else {
                    temp.getNext().setItem(item);
                }
                current = current.getNext();
            }
        } else {
            throw new DoubleEndedQueueException("List is empty; can't sort anything");
        }
    }
}