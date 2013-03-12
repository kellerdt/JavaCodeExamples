package random;

import java.math.BigInteger;

public class BitTesting {

	private static final int size = 10;
	private static BigInteger[] masks = new BigInteger[size];
	
	static {
		for(int i=0; i < size; i++) {
			BigInteger value = BigInteger.ONE;
			masks[i] = value.shiftLeft(i);
		}
	}
	
	public BitTesting() {}
	
	public void testBit(BigInteger value) {
		for(int i=1; i <= masks.length; i++) {
			if(!(value.and(masks[i-1])).equals(BigInteger.ZERO))
				System.out.println("Bit " + i + " is set");
		}
	}
	
	public static void main(String[] args) {
		BitTesting test = new BitTesting();
		System.out.println("Testing value 8");
		test.testBit(BigInteger.valueOf(8));
		System.out.println("Testing value 25");
		test.testBit(BigInteger.valueOf(25));
		System.out.println("Testing value 143");
		test.testBit(BigInteger.valueOf(143));
	}
}
