public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> list = new LinkedListDeque<>();
        for (char c : word.toCharArray()) {
            list.addLast(c);
        }
        return list;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < deque.size() >> 1; i++) {
            if (deque.get(i) != deque.get(deque.size() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < deque.size() >> 1; i++) {
            if (!cc.equalChars(deque.get(i), deque.get(deque.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
