package string;

public class Reverse {

	public static final int WORD = 1;
	public static final int SENTENCE = 2;
	public static final int R_WORD = 3;
	public static final int R_SENTENCE = 4;
	
	public String reverseSentence(String sentence) {
		if(!sentence.contains(" "))
			return sentence; // Check case of no spaces
		
		int total = 0, lastSpace = 0, length = sentence.length();
		do {
			lastSpace = sentence.lastIndexOf(' ');
			lastSpace = lastSpace < 0 ? 0 : lastSpace;
			sentence = sentence.substring(0, total) +			// Section already processed
					sentence.substring(lastSpace + 1, length) + // Section to move without the space
					" " +  										// Add a space after the section moved
					sentence.substring(total, lastSpace); 		// Section not processed
			total += length - lastSpace;
		} while(total < lastSpace); // Quit after processed section passes unprocessed
		return sentence;
	}
	
	public String reverseSentenceRecursive(String sentence) {
		int lastSpace = sentence.lastIndexOf(' ');
		if(lastSpace == -1)
			return sentence;
		else
			return sentence.substring(lastSpace + 1) + " " +
				this.reverseSentence(sentence.substring(0, lastSpace));
	}
	
	public String reverseWord(String word) {
		int length = word.length();
		// We don't have to process last character (similar to bubble sort)
		for(int i=0; i < length-1; i++) {
			word = word.substring(0, i) + word.substring(length-1) + word.substring(i, length-1); 
		}
		return word;
	}
	
	public String reverseWordMemory(String word) {
		char[] reverse = new char[word.length()];
		for(int i=0; i < word.length(); i++) {
			reverse[i] = word.charAt(word.length()-(i+1));
		}
		return new String(reverse);
	}
	
	public String reverseWordRecursive(String word) {
		if(word.length() == 1)
			return word;
		else
			return word.substring(word.length()-1) +
					reverseWordRecursive(word.substring(0, word.length()-1));
	}
	
	public void testReverse(int option, String value) {
		String result = null;
		if(option == Reverse.WORD) {
			System.out.println("Testing Word Reversal");
			result = this.reverseWord(value);
		} else if(option == Reverse.SENTENCE) {
			System.out.println("Testing Sentence Reversal");
			result = this.reverseSentence(value);
		} else if(option == Reverse.R_WORD) {
			System.out.println("Testing Recursive Word Reversal");
			result = this.reverseWordRecursive(value);
		} else if(option == Reverse.R_SENTENCE) {
			System.out.println("Testing Recursive Sentence Reversal");
			result = this.reverseSentenceRecursive(value);
		} else {
			System.out.println("Invalid option sent to the test method");
		}
		System.out.println("Original: " + value);
		System.out.println("Reversed: " + result);
		System.out.println("------------------");
	}
	
	public void testReverse(int option, String value, int iterations) {
		long start = System.currentTimeMillis();
		if(option == Reverse.WORD) {
			System.out.println("Testing Word Reversal For " + iterations + " Iterations");
			for(int i=0; i < iterations; i++)
				this.reverseWord(value);
		} else if(option == Reverse.SENTENCE) {
			System.out.println("Testing Sentence Reversal For " + iterations + " Iterations");
			for(int i=0; i < iterations; i++)
				this.reverseSentence(value);
		} else if(option == Reverse.R_WORD) {
			System.out.println("Testing Recursive Word Reversal For " + iterations + " Iterations");
			for(int i=0; i < iterations; i++)
				this.reverseWordRecursive(value);
		} else if(option == Reverse.R_SENTENCE) {
			System.out.println("Testing Recursive Sentence Reversal For " + iterations + " Iterations");
			for(int i=0; i < iterations; i++)
				this.reverseSentenceRecursive(value);
		} else {
			System.out.println("Invalid option sent to the test method");
		}
		long finish = System.currentTimeMillis();
		System.out.println("Value: " + value);
		System.out.println("Time: " + (finish - start));
		System.out.println("------------------");
	}
	
	public static void main(String[] args) {
		Reverse reverse = new Reverse();
		reverse.testReverse(Reverse.WORD, "nabby");
		//reverse.testReverse(Reverse.SENTENCE, "nabby", 1000);
		//reverse.testReverse(Reverse.R_WORD, "nabby", 1000);
		//reverse.testReverse(Reverse.R_SENTENCE, "nabby", 1000);
		reverse.testReverse(Reverse.WORD, "Lets reverse this sentence");
		//reverse.testReverse(Reverse.SENTENCE, "Lets reverse this sentence", 1000);
		//reverse.testReverse(Reverse.R_WORD, "Lets reverse this sentence", 1000);
		//reverse.testReverse(Reverse.R_SENTENCE, "Lets reverse this sentence", 1000);
	}
}
