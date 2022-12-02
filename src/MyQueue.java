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
    transient Node<E> value;

    public MyQueue(int initialCapacity) {
        this.maxSize = initialCapacity;
    }


    public boolean isFull() {
        return size >= maxSize;
    }

    /**
     * Enqueue.
     */
    public void add(E e) {
        if (!isFull()) {
            final Node<E> f = value;
            value = new Node<>(f, e);
            size++;
        }
    }

    public boolean offer(E e) {
        if (!isFull()) {
            add(e);
            return true;
        }
        return false;
    }

    public void remove() {
        remove(value);
    }

    private void remove(Node<E> e) {
        Node<E> prev = e.prev;
        if (prev == null) {
            e.item = null;
            return;
        }
        if (prev.prev == null) {
            e.prev = null;
            return;
        }
        remove(e.prev);
    }

    public E peek() {
        final Node<E> f = value;
        return (f == null) ? null : f.item;
    }

    public String toString() {
        String s = "";
        for (Node<E> x = value; x != null; x = x.prev) {
            if (s.isEmpty()) s = x.toString();
            else s = String.join(", ", x.toString(), s);
            if (x.prev == null) break;
        }
        s = "{" + s + "}";
        return s;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        public String toString() {
            return item.toString();
        }

        Node(Node<E> prev, E element) {
            this.item = element;
            this.prev = prev;
        }
    }
}
