import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>(Arrays.asList(5, 1, 6, 7, 9, 4));

        System.out.println(arrayList);
        arrayList.quickSort((a, b) -> a < b);
        System.out.println(arrayList);

        System.out.println(arrayList.binarySearch((a) -> a == 5, (b) -> b > 5));
        System.out.println(arrayList.fibonacciSearch((a) -> a > 6, (b) -> b < 9));
        arrayList.selectedSort((a, b) -> a < b);
        System.out.println(arrayList);
        System.out.println(arrayList.fibonacciSearch((a) -> a > 6, (b) -> b < 9));


        MyStack<Integer> stack = new MyStack<>(10);
        stack.push(5);
        stack.push(10);
        stack.push(15);
        stack.push(20);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);

    }
}