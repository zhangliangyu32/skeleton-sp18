public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        return isPalindrome(d);
    }
    private boolean isPalindrome(Deque<Character> word) {
        if (word.size() <= 1) {
            return true;
        }
        char head = word.removeFirst();
        char tail = word.removeLast();
        if (head != tail) {
            return false;
        }
        return isPalindrome(word);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        return isPalindrome(d, cc);
    }
    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        }
        char head = word.removeFirst();
        char tail = word.removeLast();
        if (!cc.equalChars(head, tail)) {
            return false;
        }
        return isPalindrome(word, cc);
    }

}
