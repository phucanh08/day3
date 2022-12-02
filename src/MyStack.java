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
    transient Node<E> last;

    public MyStack(int initialCapacity) {
        this.maxSize = initialCapacity;
    }

    public boolean isFull() {
        return size >= maxSize;
    }

    public boolean isEmpty() {
        return last.prev == null;
    }

    public boolean push(E e) {
        if (!isFull()) {
            final Node<E> l = last;
            last = new Node<>(e, l);
            size++;
            return true;
        }
        return false;
    }

    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        final Node<E> next = last.prev;
        last.item = null;
        last.prev = null; // help GC
        this.last = next;
        size--;
    }

    public E peek() {
        final Node<E> f = last;
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
