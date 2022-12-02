import java.util.EmptyStackException;

public class MyStack<E> {
    private final transient int maxSize;
    /**
     * Độ dài của List giá trị mặc định là 0.
     */
    private transient int size = 0;

    /**
     * Con trỏ tới vị trí cuối cùng.
     * Bất biến khi: (first == null && last == null) ||
     * (last.next == null && last.item != null)
     */
    transient Node<E> value;

    public MyStack(int initialCapacity) {
        this.maxSize = initialCapacity;
    }

    public boolean isFull() {
        return size >= maxSize;
    }

    public boolean isEmpty() {
        return value.prev == null;
    }

    public boolean push(E e) {
        if (!isFull()) {
            final Node<E> l = value;
            value = new Node<>(e, l);
            size++;
            return true;
        }
        return false;
    }

    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        final Node<E> next = value.prev;
        value.item = null;
        value.prev = null; // help GC
        this.value = next;
        size--;
    }

    public void popAll() {
        if(isEmpty()) {
            return;
        }
        pop();
        popAll();
    }

    public E peek() {
        final Node<E> f = value;
        return (f == null) ? null : f.item;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;

        Node(E element, Node<E> next) {
            this.item = element;
            this.prev = next;
        }
    }
}
