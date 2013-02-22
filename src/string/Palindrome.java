package string;

public class Palindrome {

	public boolean checkPalindrome(String text) {
		boolean result = true;
		int length = text.length();
		for(int i=0; i < Math.floor(length / 2); i++) {
			char front = text.charAt(i);
			char back = text.charAt((length-1) - i);
			if(front == back)
				continue;
			else {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public boolean checkPalindromeRecursive(String text) {
		int length = text.length();
		if(length == 1) {
			return true;
		} else if(length == 2) {
			return text.charAt(0) == text.charAt(1);
		} else {
			return (text.charAt(0) == text.charAt(length-1)) &&
					this.checkPalindromeRecursive(text.substring(1,length-1));
		}
	}
	
	public void testPalindrome(String text, boolean recursive) {
		Boolean result = null;
		if(recursive) {
			System.out.println("Testing Text For A Palindrome Recursively");
			result = this.checkPalindromeRecursive(text);
		} else {
			System.out.println("Testing Text For A Palindrome");
			result = this.checkPalindrome(text);
		}
		System.out.println("Text: " + text);
		System.out.println("Is Palindrome: " + result);
		System.out.println("--------------");
	}
	
	public static void main(String[] args) {
		Palindrome palindrome = new Palindrome();
		palindrome.testPalindrome("hello", false);
		palindrome.testPalindrome("hello", true);
		palindrome.testPalindrome("racecar", false);
		palindrome.testPalindrome("racecar", true);
		palindrome.testPalindrome("butterrettub", false);
		palindrome.testPalindrome("butterrettub", true);
	}
}
