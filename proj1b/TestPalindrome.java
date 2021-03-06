import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        StringBuilder actual = new StringBuilder();
        for (int i = 0; i < "persiflage".length(); i++) {
            actual.append(d.removeFirst());
        }
        assertEquals("persiflage", actual.toString());
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("1"));
        assertTrue(palindrome.isPalindrome("11"));
        assertTrue(palindrome.isPalindrome("121"));
    }

    @Test
    public void testIsNotPalindrome() {
        assertFalse(palindrome.isPalindrome("12"));
        assertFalse(palindrome.isPalindrome("21"));
    }
}
