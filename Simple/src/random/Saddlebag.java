package random;

import java.util.Random;

public class Saddlebag {

	public int[][] createAscendingMatrix(int width, int height) {
		int[][] matrix = new int[height][width];
		Random rand = new Random();
		
		for(int h=0; h < height; h++) {
			int value = 0;
			for(int w=0; w < width; w++) {
				if(w != 0)
					// Save the value to the left
					value = matrix[h][w-1];
				if(h != 0 && value < matrix[h-1][w])
					// If value above is higher than the left use that one
					value = matrix[h-1][w];
				// Since this is ascending matrix increase the max value between 1 and 5
				matrix[h][w] = value + rand.nextInt(4) + 1;
			}
		}
		return matrix;
	}
	
	public void printMatrix(int[][] matrix) {
		StringBuilder builder = new StringBuilder("{ ");
		for(int h = 0; h < matrix.length; h++) {
			for(int w = 0; w < matrix[h].length; w++) {
				int val = matrix[h][w];
				if(val < 10)
					builder.append("0");
				builder.append(matrix[h][w]);
				if(h != matrix.length - 1 || w != matrix[h].length - 1)
					builder.append(",");
			}
			if(h != matrix.length - 1)
				builder.append("\n  ");
			else builder.append(" }");
		}
		System.out.println(builder.toString());
	}
	
	public int[][] findValue(int[][] matrix, int value) {
		System.out.println(matrix.length);
		System.out.println(matrix[0].length);
		int xVal = matrix[0].length - 1;
		int yVal = 0;
		// Check error conditions first to prevent IndexOutOfBounds
		while(xVal > -1 && yVal < matrix.length && value != matrix[yVal][xVal]) {
			System.out.println("Checking [" + yVal + "," + xVal + "] = " + matrix[yVal][xVal]);
			if(value > matrix[yVal][xVal])
				yVal++; // If its less move down
			else
				xVal--; // Otherwise move left
		}
		// If we searched but didn't find the value
		if(xVal < 0 || yVal > matrix[0].length) {
			xVal = -1;
			yVal = -1;
		}
		return new int[][] {{yVal},{xVal}};
	}
	
	public static void main(String[] args) {
		Saddlebag s = new Saddlebag();
		int value = 10;
		int[][] matrix = s.createAscendingMatrix(5, 4);
		
		s.printMatrix(matrix);
		int[][] index = s.findValue(matrix, value);
		System.out.println("Index of " + value + ": " + index[0][0] + ", " + index[1][0]);
	}
}
