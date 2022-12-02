import java.util.ArrayList;
import java.util.Objects;

public class MyMap<K, V> {
    transient MapEntry<K, V> value;

    MyMap() {
    }

    public boolean isEmpty() {
        return value == null;
    }

    public ArrayList<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        for (MapEntry<K, V> x = value; x != null; x = x.prev) {
            keys.add(x.key);
            if (x.prev == null) break;
        }
        return keys;
    }

    public void put(K k, V v) {
        if (keys().contains(k)) {
            System.out.println("key đã tồn tại");
            return;
        }
        final MapEntry<K, V> next = value;
        value = new MapEntry<>(k, v, next);
    }

    public void replace(K key, V newValue) {
        for (MapEntry<K, V> x = value; x != null; x = x.prev) {
            if (key.equals(x.key)) {
                x.value = newValue;
                return;
            }
        }
        System.out.println("key không đã tồn tại");
    }

    public void remove(K k) {
        for (MapEntry<K, V> x = value; x != null; x = x.prev) {
            final MapEntry<K, V> next = x.prev;
            if (k.equals(x.key)) {
                if (next != null) x.prev = next.prev;
                return;
            }
            if (k.equals(next.key) && next.prev == null) {
                x.prev = null;
                return;
            }
        }
    }

    public V get(K k) throws Exception {
        for (MapEntry<K, V> x = value; x != null; x = x.prev) {
            if (k.equals(x.key)) {
                return x.value;
            }
        }
        throw new Exception("no Key");
    }

    public String toString() {
        String s = "";
        for (MapEntry<K, V> x = value; x != null; x = x.prev) {
            if (s.isEmpty()) s = x.toString();
            else s = String.join(", ", x.toString(), s);
            if (x.prev == null) break;
        }
        s = "{" + s + "}";
        return s;
    }

    static class MapEntry<K, V> {
        final K key;
        V value;
        MapEntry<K, V> prev;

        MapEntry(K key, V value, MapEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.prev = next;
        }

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }


        public final String toString() {
            return "\"" + key + "\" : \"" + value + "\"";
        }

        public final boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof MapEntry) {
                MapEntry<?, ?> e = (MapEntry<?, ?>) o;
                return Objects.equals(key, e.key) && Objects.equals(value, e.value);
            }
            return false;
        }
    }
}
