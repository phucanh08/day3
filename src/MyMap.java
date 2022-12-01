
import java.util.LinkedList;
import java.util.Objects;

public class MyMap<K, V> {
    int hasCode;

    int hash = 0;

    transient MapEntry<K, V> value;

    MyMap() {}

    public boolean isEmpty() {
        return false;
    }

    public void addEntries(MapEntry<K, V> entry) {
        final MapEntry<K, V> v = value;
        value = new MapEntry<>(hash, entry.key, entry.value, v);
        hash++;
    }

    public boolean remove(K k) {
        for (MapEntry<K, V> x = value; x != null; x = x.next) {
            if (k.equals(x.key)) {
                x.next = null;
                return true;
            }
        }
        return false;
    }

    public V get(K k) throws Exception {
        for (MapEntry<K, V> x = value; x != null; x = x.next) {
            if (k.equals(x.key)) {
                return x.value;
            }
        }
        throw new Exception("no Key");
    }


    static class MapEntry<K, V> {
        final int hash;
        final K key;
        V value;
        MapEntry<K, V> next;

        MapEntry(int hash, K key, V value, MapEntry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof MapEntry) {
                MapEntry<?, ?> e = (MapEntry<?, ?>) o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }
    }
}
