package random;

import junit.framework.TestCase;

import org.junit.Test;

public class ByteArrayTesting extends TestCase {

	public static final int SHORT_OFFSET = 16/8;
	public static final int INT_OFFSET = 32/8;
	public static final int LONG_OFFSET = 64/8;
	
	private int totalOffset = 0;
	
	/**
	 * Since an int is 32 bits put each 4 bytes into the array at a time
	 * by using logical AND and bit shifting the int.
	 * @param value
	 * @param array
	 * @param offset
	 */
	public void putInt(int value, byte[] array, int offset) {
		array[offset] = (byte) (0xff & (value >> 24));
		array[offset+1] = (byte) (0xff & (value >> 16));
		array[offset+2] = (byte) (0xff & (value >> 8));
		array[offset+3] = (byte) (0xff & value);
	}
	
	/**
	 * Build the int back out from the byte array by bit shifting and using
	 * the logical OR operation.  The logical AND prevents two's complement issues.
	 * @param array
	 * @param offset
	 * @return
	 */
	public int getInt(byte[] array, int offset) {
		return ((array[offset] & 0xff) << 24) |
				((array[offset+1] & 0xff) << 16) |
				((array[offset+2] & 0xff) << 8) |
				(array[offset+3] & 0xff);
	}
	
	/**
	 * Since a long is 64 bits put each 8 bytes into the array at a time
	 * by using logical AND and bit shifting the long.
	 * @param value
	 * @param array
	 * @param offset
	 */
	public void putLong(long value, byte[] array, int offset) {
		array[offset] = (byte) (0xff & (value >> 56));
		array[offset+1] = (byte) (0xff & (value >> 48));
		array[offset+2] = (byte) (0xff & (value >> 40));
		array[offset+3] = (byte) (0xff & (value >> 32));
		array[offset+4] = (byte) (0xff & (value >> 24));
		array[offset+5] = (byte) (0xff & (value >> 16));
		array[offset+6] = (byte) (0xff & (value >> 8));
		array[offset+7] = (byte) (0xff & value);
	}
	
	/**
	 * Build the long back out from the byte array by bit shifting and using
	 * the logical OR operation.  The logical AND prevents two's complement issues.
	 * @param array
	 * @param offset
	 * @return
	 */
	public long getLong(byte[] array, int offset) {
		return ((array[offset] & 0xff) << 56) |
				((array[offset+1] & 0xff) << 48) |
				((array[offset+2] & 0xff) << 40) |
				((array[offset+3] & 0xff) << 32) |
				((array[offset+4] & 0xff) << 24) |
				((array[offset+5] & 0xff) << 16) |
				((array[offset+6] & 0xff) << 8) |
				(array[offset+7] & 0xff);
	}
	
	@Test
	public void test() {
		byte[] array = new byte[32];
		
		// Put values onto the array
		this.putInt(100, array, 0);
		this.totalOffset += ByteArrayTesting.INT_OFFSET;
		this.putInt(200, array, this.totalOffset);
		this.totalOffset += ByteArrayTesting.INT_OFFSET;
		this.putLong(123456789L, array, this.totalOffset);
		
		this.printArrayContents(array);
		
		// Read the values out of the array and verify they are the same
		long Lval = this.getLong(array, this.totalOffset);
		System.out.println(Lval);
		assertTrue(Lval == 123456789L);
		this.totalOffset -= ByteArrayTesting.INT_OFFSET;
		int Ival = this.getInt(array, this.totalOffset);
		System.out.println(Ival);
		assertTrue(Ival == 200);
		this.totalOffset -= ByteArrayTesting.INT_OFFSET;
		Ival = this.getInt(array, this.totalOffset);
		System.out.println(Ival);
		assertTrue(Ival == 100);
	}
	
	private void printArrayContents(byte[] array) {
		System.out.print("[");
		for(int i=0; i < array.length; i++) {
			System.out.print(array[i] & 0xff);
			if(i != array.length-1)
				System.out.print(",");
		}
		System.out.println("]");
	}
}
