import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(2);

    static Palindrome palindrome = new Palindrome();

    // Your tests go here.
    @Test
    public void testIsPalindromeOffByOne() {
        assertTrue(palindrome.isPalindrome("1", offByN));
        assertTrue(palindrome.isPalindrome("13", offByN));
        assertTrue(palindrome.isPalindrome("123", offByN));
    }

    @Test
    public void testIsNotPalindromeOffByOne() {
        assertFalse(palindrome.isPalindrome("11", offByN));
        assertFalse(palindrome.isPalindrome("12", offByN));
    }
}
