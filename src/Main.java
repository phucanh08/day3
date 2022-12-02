import java.util.Map;

public class Main {

    public static void main(String[] args){
        MyQueue<Integer> testQueue = new MyQueue<>(10);
        testQueue.add(5);
        testQueue.add(10);
        testQueue.add(16);
        testQueue.add(9);
        System.out.println("Hello, World!" + testQueue.peek());
        testQueue.remove();
        System.out.println("Hello, World!" + testQueue.peek());
        testQueue.remove();
        System.out.println("Hello, World!" + testQueue.peek());
        testQueue.remove();
        System.out.println("Hello, World!" + testQueue.peek());
        testQueue.remove();
        System.out.println("Hello, World!" + testQueue.peek());


        MyStack<Integer> stack = new MyStack<>(5);
        stack.push(10);
        stack.push(9);
        stack.push(8);
        stack.push(7);
        System.out.println("Hello, World!" + stack.peek());
        stack.pop();
       System.out.println("Hello, World!" + stack.peek());
        stack.pop();
       System.out.println("Hello, World!" + stack.peek());
        stack.pop();
       System.out.println("Hello, World!" + stack.peek());

        MyMap<String, Integer> b = new MyMap<>();
        b.addEntries(new MyMap.MapEntry<>("hello", 1));
        b.addEntries(new MyMap.MapEntry<>("hello", 5));
        System.out.println(b);
        b.addEntries(new MyMap.MapEntry<>("hi", 32));
        System.out.println(b);

        b.addEntries(new MyMap.MapEntry<>("I", 4));
        System.out.println(b);

        b.addEntries(new MyMap.MapEntry<>("h", 6));
        System.out.println(b);
        b.remove("hello");
        System.out.println(b);
        b.remove("h");
        System.out.println(b);
    }
}