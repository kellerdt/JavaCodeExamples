package string;

import junit.framework.TestCase;

import org.junit.Test;

public class RepeatedChars extends TestCase {

	public Result findRepeatedChars(String text, int size) {
		Result result = new Result();
		result.num = 0;
		result.pattern = "";
		// Can't have repeated characters of size n unless there are n*2 characters
		// left to process.
		for(int i=0; i < text.length()-(size*2); i++) {
			System.out.println("Iteration " + i + " for " + text);
			int count = 0;
			String pattern = text.substring(i, i+size);
			System.out.println("Pattern = " + pattern);
			// Compare against the next chunk of that size
			System.out.println("Checking " + text.substring(i+size, i+2*size));
			for(int j=1; pattern.equals(text.substring(i+(j*size), i+(j*size)+size)); j++)
				count = j;
			System.out.println("Found " + count + " repeated instances");
			if(count > result.num) {
				result.num = count+1; // Account for first instance from pattern variable
				result.pattern = pattern;
			}
		}
		return result;
	}
	
	@Test
	public void testStrings() {
		String testString = "hellellotoooomyfriendss";
		Result result = findRepeatedChars(testString, 3);
		assertTrue(result.num == 2);
		assertTrue(result.pattern.equals("ell"));
		result = findRepeatedChars(testString, 2);
		assertTrue(result.num == 2);
		assertTrue(result.pattern.equals("oo"));
		result = findRepeatedChars(testString, 1);
		assertTrue(result.num == 4);
		assertTrue(result.pattern.equals("o"));
	}
	
	// Struct class to hold results
	private class Result {
		public int num;
		public String pattern;
	}
}
