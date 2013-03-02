package string;

import junit.framework.TestCase;

import org.junit.Test;

public class Palindrome extends TestCase {

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
	
	public boolean runTest(String text, boolean recursive) {
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
		return result;
	}
	
	@Test
	public void testPalindromes() {
		assertFalse(runTest("hello", false));
		assertFalse(runTest("hello", true));
		assertTrue(runTest("racecar", false));
		assertTrue(runTest("racecar", true));
		assertTrue(runTest("butterrettub", false));
		assertTrue(runTest("butterrettub", true));
	}
}
