import java.util.ArrayList;
import java.util.Objects;

public class MyMap<K, V> {

    int hasCode;
    transient MapEntry<K, V> value;

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    MyMap() {
    }

    public boolean isEmpty() {
        return false;
    }

    public ArrayList<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        for (MapEntry<K, V> x = value; x != null; x = x.next) {
            keys.add(x.key);
            if (x.next == null) break;
        }
        return keys;
    }

    public void addEntries(MapEntry<K, V> entry) {
        if(keys().contains(entry.key)) {
            System.out.println("key đã tồn tại");
            return;
        }
        final MapEntry<K, V> v = value;
        int hash = hash(entry.getKey());
        value = new MapEntry<>(hash, entry.key, entry.value, v);
    }

    public boolean updateValueByKey(K key, V newValue) {
        for (MapEntry<K, V> x = value; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = newValue;
                return true;
            }
        }
        System.out.println("key không đã tồn tại");
        return false;
    }

    public boolean remove(K k) {

        for (MapEntry<K, V> x = value; x != null; x = x.next) {
            final MapEntry<K, V> next = x.next;
            if (k.equals(x.key)) {
                if (next != null) x.next = next.next;
                return true;
            }
            if(k.equals(next.key) && next.next == null) {
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

    public String toString() {
        String s = "";
        for (MapEntry<K, V> x = value; x != null; x = x.next) {
            if (s.isEmpty()) s = "\"" + x.key + "\" : \"" + x.value + "\"";
            else s = String.join(", ", "\"" + x.key + "\" : \"" + x.value + "\"", s);
            if (x.next == null) break;
        }
        s = "{" + s + "}";
        return s;
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

        public MapEntry(K key, V value) {
            hash = 0;
            this.key = key;
            this.value = value;
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
            if (o == this) return true;
            if (o instanceof MapEntry) {
                MapEntry<?, ?> e = (MapEntry<?, ?>) o;
                return Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue());
            }
            return false;
        }
    }
}
