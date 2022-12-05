import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class MyArrayList<E> extends ArrayList<E> {
    private final Sort<E> _sort = new Sort<>(this);
    private final Search<E> _search = new Search<>(this);

    public MyArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public MyArrayList() {
    }

    public MyArrayList(Collection<E> c) {
        super(c);
    }

    /**
     * @param condition              kiểm  phần tử đó có đúng là phần tử cần tìm không.
     * @param isGreaterThanCondition kiểm  phần tử đó có lớn hơn phần tử đang tìm không
     */
    public Object binarySearch(Function<E, Boolean> condition, Function<E, Boolean> isGreaterThanCondition) {
        return _search.binarySearch(0, this.size() - 1, condition, isGreaterThanCondition);
    }

    public E fibonacciSearch(Function<E, Boolean> conditionMore, Function<E, Boolean> conditionLess) {
        return _search.fibonacciSearch(conditionMore, conditionLess, 0, 1, 0, this.size() - 1);
    }

    public <A, B> void selectedSort(MyFunction<E, Boolean> condition) {
        _sort.selectedSort(condition, 0, 0);
    }

    public <A, B> void bubbleSort(MyFunction<E, Boolean> condition) {
        _sort.bubbleSort(condition, 0);
    }

    public void quickSort(MyFunction<E, Boolean> condition) {
        _sort.quickSort(condition, 0, this.size() - 1);
    }

    public void heapSort(MyFunction<E, Boolean> condition) {
        _sort.heapSort(condition);
    }


    public MyArrayList<E> where(Function<E, Boolean> condition) {
        MyArrayList<E> result = new MyArrayList<>();
        this.forEach(e -> {
            if (condition.apply(e)) {
                result.add(e);
            }
        });
        return result;
    }

    public Object firstWhere(Function<E, Boolean> condition) {
        for (E e : this) {
            if (condition.apply(e)) {
                return e;
            }
        }
        return null;
    }

    public Object lastWhere(Function<E, Boolean> condition) {
        for (int i = this.size() - 1; i >= 0; i--) {
            if (condition.apply(this.get(i))) {
                return this.get(i);
            }
        }
        return null;
    }

    public MyArrayList<E> skipWhile(Function<E, Boolean> condition) {
        MyArrayList<E> result = new MyArrayList<>();
        for (E e : this) {
            if (condition.apply(e)) {
                return result;
            }
            result.add(e);
        }
        return null;
    }

    public <T> MyArrayList<T> map(Function<E, T> function) {
        MyArrayList<T> result = new MyArrayList<>();
        for (E e : this) {
            result.add(function.apply(e));
        }
        return result;
    }

    public String toString() {
        String s = "";
        for (E e : this) {
            if (s.isEmpty()) s = e.toString();
            else s = String.join(",", s, e.toString());
        }
        return s;
    }
}

class Search<E> {
    public final ArrayList<E> list;

    public Search(ArrayList<E> list) {
        this.list = list;
    }

    public E binarySearch(int firstIndex, int lastIndex, Function<E, Boolean> condition, Function<E, Boolean> isGreaterThanCondition) {
        if (condition.apply(list.get(firstIndex))) {
            return list.get(firstIndex);
        }
        if (condition.apply(list.get(lastIndex))) {
            return list.get(lastIndex);
        }

        if (lastIndex >= firstIndex) {
            int midIndex = (firstIndex + lastIndex) / 2;
            E mid = list.get(midIndex);
            if (condition.apply(mid)) {
                return mid;
            }
            if (isGreaterThanCondition.apply(mid)) firstIndex = midIndex;
            else lastIndex = midIndex;

            return binarySearch(firstIndex, lastIndex, condition, isGreaterThanCondition);
        }
        return null;
    }

    public E fibonacciSearch(Function<E, Boolean> conditionMore, Function<E, Boolean> conditionLess, int m_2, int m_1, int start, int end) {
        int m = (end < m_1 + start) ? end : (m_1 + m_2);

        boolean isNext = (!conditionMore.apply(list.get(m_1 + start)) && !conditionMore.apply(list.get(m_2 + start)))
                && (conditionLess.apply(list.get(m_1 + start)) && conditionLess.apply(list.get(m_2 + start)));
        boolean isPrev = (conditionMore.apply(list.get(m_1 + start)) && conditionMore.apply(list.get(m_2 + start)))
                && (!conditionLess.apply(list.get(m_1 + start)) && !conditionLess.apply(list.get(m_2 + start)));
        boolean isCenter = conditionMore.apply(list.get(m_2 + start)) && conditionMore.apply(list.get(m_1 + start));
        if (conditionMore.apply(list.get(m_1 + start)) && conditionMore.apply(list.get(m_1 + start))) {
            return list.get(m_1 + start);
        }
        if (conditionMore.apply(list.get(m_2 + start)) && conditionMore.apply(list.get(m_2 + start))) {
            return list.get(m_2 + start);
        }
        if (isNext) return fibonacciSearch(conditionMore, conditionLess, m_1, m, m_1 + start, end);
        if (isPrev) return fibonacciSearch(conditionMore, conditionLess, 0, 1, start, start + m_2);
        if (isCenter) return fibonacciSearch(conditionMore, conditionLess, 0, 1, start + m_2, start + m_1);
        return null;
    }
}

class Sort<E> {
    public final ArrayList<E> list;

    public Sort(ArrayList<E> list) {
        this.list = list;
    }

    public <A, B> void selectedSort(MyFunction<E, Boolean> condition, int l, int r) {
        for (int j = r + 1; j < list.size(); j++) {
            for (int i = l; i <= r; i++) {
                if (condition.apply(list.get(i), list.get(j))) {
                    E e = list.get(j);
                    list.remove(j);
                    list.add(i, e);
                    selectedSort(condition, l, j);
                }
            }
        }
    }

    public <A, B> void bubbleSort(MyFunction<E, Boolean> condition, int first) {
        for (int i = first + 1; i < list.size(); i++) {
            if (condition.apply(list.get(first), list.get(i))) {
                swap(first, i);
                bubbleSort(condition, first + 1);
            }
        }
    }

    public void quickSort(MyFunction<E, Boolean> condition, int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            E pivot = list.get(endIndex);
            int current = beginIndex;
            for (int i = beginIndex; i < endIndex; i++) {
                E e = list.get(i);
                if (condition.apply(e, pivot)) {
                    swap(current++, i);
                }
            }
            swap(current, endIndex);
            this.quickSort(condition, beginIndex, current - 1);
            this.quickSort(condition, current + 1, endIndex);
        }
    }


    private void heaping(MyFunction<E, Boolean> condition, int n, int i) {
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;
        int largest = i;
        if (leftIndex < n && condition.apply(list.get(leftIndex), list.get(i))) largest = leftIndex;
        if (rightIndex < n && condition.apply(list.get(rightIndex), list.get(i))) largest = rightIndex;
        if (largest != i) {
            swap(i, largest);
            heaping(condition, n, largest);
        }
    }

    public void heapSort(MyFunction<E, Boolean> condition) {
        int n = list.size();
        for (int i = (n / 2 - 1); i >= 0; i--) {
            heaping(condition, n, i);
        }
        for (int i = (n - 1); i >= 0; i--) {
            swap(0, i);
            heaping(condition, n, i);
        }
    }

    public void swap(int i1, int i2) {
        E e = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, e);
    }
}


interface MyFunction<E, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param a,b the function argument
     * @return the function result
     */
    R apply(E a, E b);
}
