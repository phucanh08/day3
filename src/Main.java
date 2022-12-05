import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(5);
        arrayList.add(1);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(9);
        arrayList.add(4);
        System.out.println(arrayList);
//        arrayList.bubbleSort((a,b) -> a > b);
//        System.out.println(arrayList);
////        arrayList.selectedSort((a,b) -> a > b);
////        System.out.println(arrayList);
        arrayList.selectedSort((a,b) -> a < b);
        System.out.println(arrayList);
    }
}