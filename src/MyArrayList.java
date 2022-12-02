import java.util.*;
import java.util.function.Function;

public class MyArrayList<E> {
    private final ArrayList<E> list;

    public MyArrayList(ArrayList<E> list) {
        this.list = list;
    }

    public MyArrayList() {
        list = new ArrayList<>();
    }

    public void add(E e) {
        list.add(e);
    }

    public void add(int index, E element) {
        list.add(index, element);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void remove(E e) {
        list.remove(e);
    }

    /**
     * Fun1 kiểm  phần tử đó có đúng là phần tử cần tìm không.
     * Fun2 kiểm  phần tử đó có lớn hơn phần tử đang tìm không
     */
    public E binarySearch(Function<E, Boolean> fun1, Function<E, Boolean> fun2) {
        return binarySearch(0, list.size(), fun1, fun2);
    }

    public E binarySearch(int firstIndex, int lastIndex, Function<E, Boolean> fun1, Function<E, Boolean> fun2) {
        if (fun1.apply(list.get(firstIndex))) {
            return list.get(firstIndex);
        }
        if (fun1.apply(list.get(lastIndex))) {
            return list.get(lastIndex);
        }

        if (lastIndex >= firstIndex) {
            int midIndex = (firstIndex + lastIndex) / 2;
            E mid = list.get(midIndex);
            if (fun1.apply(mid)) {
                return mid;
            }
            if (fun2.apply(mid)) firstIndex = midIndex;
            else lastIndex = midIndex;

            return binarySearch(firstIndex, lastIndex, fun1, fun2);
        }
        return null;
    }


    public <A, B> void selectedSort(MyFunction<E, Boolean> function) {
        selectedSort(function, 0, 0);
    }

    public <A, B> void selectedSort(MyFunction<E, Boolean> function, int l, int r) {
        if(r < list.size()) {
            if(r != 0) {
                for (int i = l; i < r; i++) {
                    if (function.apply(list.get(i), list.get(r + 1))) {
                        E e = list.get(r + 1);
                        list.remove(r + 1);
                        list.add(r, e);
                        selectedSort(function, l, r + 1);
                    }
                }
            } else {

            }
        }
    }

    public <A, B> void bubbleSort(MyFunction<E, Boolean> function) {
        bubbleSort(function, 0);
    }

    public <A, B> void bubbleSort(MyFunction<E, Boolean> function, int first) {
        for (int i = first + 1; i < list.size(); i++) {
            if (function.apply(list.get(first), list.get(i))) {
                swap(first, i);
                bubbleSort(function, first + 1);
            }
        }
    }

    public void swap(int i1, int i2) {
        E e = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, e);
    }


    public MyArrayList<E> where(Function<E, Boolean> function) {
        MyArrayList<E> result = new MyArrayList<>();
        list.forEach(e -> {
            if (function.apply(e)) {
                result.add(e);
            }
        });
        return result;
    }

    public E firstWhere(Function<E, Boolean> function) {
        for (E e : list) {
            if (function.apply(e)) {
                return e;
            }
        }
        return null;
    }

    public E lastWhere(Function<E, Boolean> function) {
        for (int i = 0; i < list.size(); i++) {
            if (function.apply(list.get(list.size() - 1 - i))) {
                return list.get(list.size() - 1 - i);
            }
        }
        return null;
    }

    public MyArrayList<E> skipWhile(Function<E, Boolean> function) {
        MyArrayList<E> result = new MyArrayList<>();
        for (E e : list) {
            if (function.apply(e)) {
                return result;
            }
            result.add(e);
        }
        return null;
    }

    public <T> MyArrayList<T> map(Function<E, T> function) {
        MyArrayList<T> result = new MyArrayList<>();
        for (E e : list) {
            result.add(function.apply(e));
        }
        return result;
    }

    public String toString() {
        String s = "";
        for (E e : list) {
            if (s.isEmpty()) s = e.toString();
            else s = String.join(",", s, e.toString());
        }
        return s;
    }

    static public interface MyFunction<E, R> {
        /**
         * Applies this function to the given argument.
         *
         * @param a,b the function argument
         * @return the function result
         */
        R apply(E a, E b);
    }

}