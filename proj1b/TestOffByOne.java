import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    static Palindrome palindrome = new Palindrome();

    // Your tests go here.
    @Test
    public void testIsPalindromeOffByOne() {
        assertTrue(palindrome.isPalindrome("1", offByOne));
        assertTrue(palindrome.isPalindrome("12", offByOne));
        assertTrue(palindrome.isPalindrome("122", offByOne));
    }

    @Test
    public void testIsNotPalindromeOffByOne() {
        assertFalse(palindrome.isPalindrome("11", offByOne));
        assertFalse(palindrome.isPalindrome("13", offByOne));
    }
}
