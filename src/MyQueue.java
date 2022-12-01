
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyQueue<E> {
    private final transient int maxSize;
    /**
     * Độ dài của List giá trị mặc định là 0.
     */
    private transient int size = 0;

    /**
     * Con trỏ tới vị trí đầu tiên.
     * Bất biến khi: (first == null && last == null) ||
     * (first.prev == null && first.item != null)
     */
    transient Node<E> first;

    /**
     * Con trỏ tới vị trí cuối cùng.
     * Bất biến khi: (first == null && last == null) ||
     * (last.next == null && last.item != null)
     */
    transient Node<E> last;

    public MyQueue(int initialCapacity) {
        this.maxSize = initialCapacity;
    }


    /**
     * Hủy liên kết phần tử đầu tiên first.
     */
    private E unlinkLast(Node<E> l) {
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    public boolean isFull() {
        return size >= maxSize;
    }

    /**
     * Enqueue.
     */
    public void add(E e) {
        if(!isFull()) {
            final Node<E> f = first;
            final Node<E> newNode = new Node<>(null, e, f);
            first = newNode;
            if (f == null) last = newNode;
            else f.prev = newNode;
            size++;
        }
    }

    public boolean offer(E e) {
        if(!isFull()) {
            add(e);
            return true;
        }
        return false;
    }

    public E remove() {
        final Node<E> f = last;
        if (f == null) throw new NoSuchElementException();
        return unlinkLast(f);
    }

    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkLast(f);
    }

    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
