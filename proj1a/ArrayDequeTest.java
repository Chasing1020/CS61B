public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        for (int num : nums) {
            arr.addLast(num);
            arr.addFirst(num);
        }
        arr.printDeque();
        for (int i = 0; i < 12; i++) {
            System.out.println("arr.removeFirst() = " + arr.removeFirst());
        }
        arr.printDeque();
        System.out.println("arr.size() = " + arr.size());
    }
}
