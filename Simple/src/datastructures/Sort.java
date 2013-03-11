package datastructures;

import java.util.List;

/**
 * Static class of different sorting algorithms.
 */
public class Sort {
	
	/**
	 * Sorts the list by comparing individual values and pushing smaller
	 * values to the front.
	 * Complexity = O(n^2) : In worst case have to move every value over the whole list.
	 * @param list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends Comparable> void stupidSort(List<V> list) {
		V temp = null;
		for(int i=0; i < list.size() - 1; ) {
			int result = list.get(i).compareTo(list.get(i + 1));
			if(result > 0) { // Switch the ordering
				temp = list.get(i + 1);
				list.set(i + 1, list.get(i));
				list.set(i, temp);
				i = i == 0 ? i+1 : i-1;
			} else
				i++;
		}
	}
	
	/**
	 * Sorts a list by breaking the list down into segments, ordering the smallest
	 * segments, and ordering the resulting larger chunks on the way back up.
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <V extends Comparable> void mergeSort(List<V> list) {
		Sort.mergeSort(list, 0, list.size()-1);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <V extends Comparable> void mergeSort(List<V> list, int low0, int hi0) {
		int low = low0;
		int hi = hi0;
		System.out.println("SORTING " + low + ":" + hi);
		if(low >= hi)
			return;
		
		int mid = (low + hi) / 2;
		mergeSort(list, low, mid);
		mergeSort(list, mid+1, hi);
		
		System.out.println("\tMERGING " + low + ":" + hi);
		int endLow = mid;
		int startHi = mid+1;
		while((low <= endLow) && (startHi <= hi)) {
			System.out.println("Comparing " + list.get(low) + " and " + list.get(startHi));
			if(list.get(low).compareTo(list.get(startHi)) < 0) {
				System.out.println("\t\tNo Change");
				low++; // Low is lower than the higher order list lowest element
			} else {
				System.out.println("\t\tSwitching order");
				list.add(low, list.remove(startHi)); // Insert higher order element at low index
				low++; // Move to the next item
				endLow++; // Move low end index up 1 to account for moved value
				startHi++; // Move high start index up 1 to account for moved value
			}
		}
	}
	
	/**
	 * Quicksort algorithm of dividing the list into two grouped segments higher and lower
	 * than a pivot point 
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <V extends Comparable> void quickSort(List<V> list) {
		quickSort(list, 0, list.size()-1);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <V extends Comparable> void quickSort(List<V> list, int low0, int hi0) {
		int low = low0;
		int hi = hi0;
		System.out.println("SORTING " + low + ":" + hi);
		if(low >= hi)
			return;
		else if(low == hi - 1) { // Only two elements left so check sort
			if(list.get(low).compareTo(list.get(hi)) > 0) {
				V temp = list.get(low);
				list.set(low, list.get(hi));
				list.set(hi, temp);
			}
			return;
		}
		
		V pivot = list.remove((low+hi)/2); // Select pivot from the middle
		list.add(hi, pivot); // Add pivot to the end to keep hi/low values accurate
		System.out.println("PIVOT " + pivot + " : LOW " + low + " : HI " + hi);
		while(low < hi) {
			// Search forward from low until a value higher than pivot is found
			// or low >= hi
			while(list.get(low).compareTo(pivot) <= 0 && low < hi) {
				low++;
				System.out.println("Low = " + low);
			}
			
			// Search backward from the hi-1 until a value lower than pivot is
			// found or low >= hi-1 (We removed the pivot)
			while(pivot.compareTo(list.get(hi)) <= 0 && low < hi) {
				hi--;
				System.out.println("High = " + hi);
			}
			
			// Swap the elements if found
			if(low < hi) {
				V temp = list.get(low);
				list.set(low, list.get(hi));
				list.set(hi, temp);
			}
		}
		
		// High marker is now in the center so set the pivot to that value and move the center
		// to the end
		list.set(hi0, list.get(hi));
		list.set(hi, pivot);
		
		// Pivot now separates values into groups with values above and below the pivot so
		// recursively call quicksort of the sub-groups
		System.out.println("Finished PIVOT " + pivot);
		System.out.println(list);
		if(low != 0) // Already at the bottom no need to check again
			quickSort(list, low0, low-1);
		if(hi != hi0) // Already at the top so no need to check again
			quickSort(list, hi+1, hi0);
	}
}
