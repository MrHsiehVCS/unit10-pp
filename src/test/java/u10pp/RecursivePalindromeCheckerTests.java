package u10pp;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RecursivePalindromeCheckerTests {
    @Test
    public void evenPalindrome_returnsTrue() {
        assertAll(
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("aa")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abccba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddeeddcba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddefggfeddcba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddefghhhhiijkkkkllkkkkjiihhhhgfeddcba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddefghhhhiijkkkklmnoonmlkkkkjiihhhhgfeddcba"))
        );
    }

    @Test
    public void oddPalindrome_returnsTrue() {
        assertAll(
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("a")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("aba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddeddcba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddefgfeddcba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddefghhhhiijkkkklkkkkjiihhhhgfeddcba")),
            () -> assertTrue(RecursivePalindromeChecker.isPalindrome("abcddefghhhhiijkkkklmnonmlkkkkjiihhhhgfeddcba"))
        );
    }

    @Test 
    public void nonPalindrome_returnsFalse() {
        assertAll(
            () -> assertFalse(RecursivePalindromeChecker.isPalindrome("ab")),
            () -> assertFalse(RecursivePalindromeChecker.isPalindrome("abab")),
            () -> assertFalse(RecursivePalindromeChecker.isPalindrome("abceba")),
            () -> assertFalse(RecursivePalindromeChecker.isPalindrome("aabcddeddcba")),
            () -> assertFalse(RecursivePalindromeChecker.isPalindrome("abcddefgfgeddcba")),
            () -> assertFalse(RecursivePalindromeChecker.isPalindrome("abcddefghhhhiijkkkklkkkkhhjiihhhhgfeddcba")),
            () -> assertFalse(RecursivePalindromeChecker.isPalindrome("abcddefghhhhhiijkkkklmnonmlkkkkjiihhhhgfeddcba"))
        );
    }
}
