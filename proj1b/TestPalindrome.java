import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    OffByOne offByOne = new OffByOne();
    OffByN offBy5 = new OffByN(5);
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aa"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("121"));
        assertFalse(palindrome.isPalindrome("abc"));
    }
    @Test
    public void testIsPalindromeOffByOne() {
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertFalse(palindrome.isPalindrome("aa", offByOne));
        assertFalse(palindrome.isPalindrome("aba", offByOne));
        assertTrue(palindrome.isPalindrome("dbc", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
    }
    @Test
    public void testIspalindromeOffByN() {
        assertTrue(palindrome.isPalindrome("", offBy5));
        assertTrue(palindrome.isPalindrome("a", offBy5));
        assertFalse(palindrome.isPalindrome("aa", offBy5));
        assertFalse(palindrome.isPalindrome("aba", offBy5));
        assertTrue(palindrome.isPalindrome("abf", offBy5));
        assertFalse(palindrome.isPalindrome("flake", offBy5));
    }
}
