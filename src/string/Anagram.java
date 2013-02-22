package string;

import java.util.HashMap;

import junit.framework.TestCase;

import org.junit.Test;

public class Anagram extends TestCase {

	private HashMap<Character, Integer> map = null;
	
	public boolean detectAnagramRecursive(String a, String b) {
		if(a.equals(b))
			return true;
		if(a.length() != b.length())
			return false;
		
		for(int i=0; i < b.length(); i++) {
			if(a.charAt(0) == b.charAt(i)) {
				return detectAnagramRecursive(removeChar(a,0), removeChar(b,i));
			}
		}
		return b.length() == 0;
	}
	public String removeChar(String s, Integer index) {
		char[] newString = new char[s.length()-1];
		int d=0;
		for(int i=0; i < s.length(); i++) {
			if(i == index)
				d=1;
			else
				newString[i - d] = s.charAt(i);
		}
		return new String(newString);
	}
	
	public boolean detectAnagramIterative(String a, String b) {
	    if(a.equals(b))
	        return true;
	    if(a.length() != b.length())
	        return false;
	    
	    map = new HashMap<Character, Integer>();
	    for(int i=0; i < a.length(); i++) {
	        Character Acharacter = a.charAt(i);
	        Character Bcharacter = b.charAt(i);
	        if(map.get(Acharacter) == null) {
	            map.put(Acharacter, 1);
	        } else {
	            map.put(Acharacter, map.get(Acharacter)+1);
	        }
	        if(map.get(Bcharacter) == null) {
	            map.put(Bcharacter, -1);
	        } else {
	            map.put(Bcharacter, map.get(Bcharacter)-1);
	        }
	    }

	    for(Integer val : map.values()) {
	        if(val != 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public boolean anagramIterative(String a, String b) {
		Long start = System.currentTimeMillis();
		boolean result = detectAnagramIterative(a, b);
		Long finish = System.currentTimeMillis();
		System.out.println("Running algorithm iteratively");
		System.out.println("Comparing " + a + " and " + b);
		System.out.println("Result = " + result + " Runtime = " + start + " - " + finish + " = " + (finish - start));
		System.out.println("--------------------");
		return result;
	}
	
	public boolean anagramRecursive(String a, String b) {
		Long start = System.currentTimeMillis();
		boolean result = detectAnagramRecursive(a, b);
		Long finish = System.currentTimeMillis();
		System.out.println("Running algorithm recursively");
		System.out.println("Comparing " + a + " and " + b);
		System.out.println("Result = " + result + " Runtime = " + start + " - " + finish + " = " + (finish - start));
		System.out.println("--------------------");
		return result;
	}
	
	@Test
	public void testResults() {
		assertTrue(anagramIterative("god", "dog"));
		assertTrue(anagramRecursive("god", "dog"));
		assertFalse(anagramIterative("Hello", "hello"));
		assertFalse(anagramRecursive("Hello", "hello"));
		assertTrue(anagramIterative(
				"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",
				"zyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcba"));
		assertTrue(anagramIterative(
				"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",
				"zyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcba"));
		assertTrue(anagramIterative("", ""));
		assertTrue(anagramRecursive("", ""));
	}
}
