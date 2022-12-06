import java.util.EmptyStackException;

public class MyStack<E> extends Node<E> {
    private final transient int maxSize;
    /**
     * Độ dài của List giá trị mặc định là 0.
     */
    private transient int size = 0;

    private MyStack(E item, Node<E> prev, int maxSize) {
        super(item,prev);
        this.maxSize = maxSize;
    }

    private Node<E> newNode() {
        return new MyStack<>(super.item, super.prev, this.maxSize);
    }

    public MyStack(int initialCapacity) {
        this.maxSize = initialCapacity;
    }

    public boolean isFull() {
        return size >= maxSize;
    }

    public boolean isEmpty() {
        return this.prev == null;
    }

    public boolean push(E e) {
        if (!isFull()) {
            final Node<E> l = newNode();
            this.item = e;
            this.prev = l;
            size++;
            return true;
        }
        return false;
    }

    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        final Node<E> prev = this.prev;
        this.item = prev.item;
        this.prev = prev.prev;
        size--;
    }

    public void popAll() {
        if (isEmpty()) {
            return;
        }
        pop();
        popAll();
    }

    public E peek() {
        return (this.item == null) ? null : this.item;
    }

    public String toString() {
        String s = "";
        for (Node<E> e = this; e.prev != null; e = e.prev) {
            if (s.isEmpty()) s = e.item.toString();
            else s = String.join(", ", e.item.toString(), s);
            if (e.prev == null) break;
        }
        return s;
    }
}

abstract class Node<E> {
    E item;
    Node<E> prev;

    Node(E element, Node<E> next) {
        this.item = element;
        this.prev = next;
    }

    public Node() {
    }
}
