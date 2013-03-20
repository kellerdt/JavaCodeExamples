package random;

public class Permutations {

	public void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0) // If we have no more characters the tree has been built so just print the prefix
			System.out.println(prefix);
		else {
			// Split into strings of 1 size smaller while adding each character to
			// the prefix.  Basically create a tree branch child for each character
			// left in the original string.
			for (int i = 0; i < n; i++) {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
			}
		}
	}

	public static void main(String[] args) {
		String test = "abcd";
		Permutations p = new Permutations();

		p.permutation("", test);
	}
}
