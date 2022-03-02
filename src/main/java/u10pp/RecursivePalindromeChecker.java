public class RecursivePalindromeChecker {
    /**
     * 
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if(s.length() <= 1) {
            return true;
        }
        boolean firstLastMatch = s.charAt(0) == s.charAt(s.length()-1);
        return firstLastMatch && isPalindrome(s.substring(1, s.length()-1));
    }
}
