package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

public class Lists extends TestCase {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <V extends Comparable> List<V> mergeSortedListsMemory(List<V> a, List<V> b) {
		List<V> merged = new ArrayList<V>(a.size() + b.size());
		int i=0, j=0;
		for( ; i < a.size() && j < b.size(); ) {
			if(a.get(i).compareTo(b.get(j)) >= 0) {
				merged.add(b.get(j));
				j++;
			} else {
				merged.add(a.get(i));
				i++;
			}
		}
		if(i != a.size()) {
			for(; i < a.size(); i++)
				merged.add(a.get(i));
		} else if(j != b.size()) {
			for(; j < b.size(); j++)
				merged.add(b.get(j));
		}
		return merged;
	}
	
	@Test
	public void test() {
		List<Integer> first = new ArrayList<Integer>();
		List<Integer> second = new ArrayList<Integer>();
		
		Random random = new Random();
		int firstSize = random.nextInt(10);
		int secondSize = random.nextInt(10);
		for(int i=0; i < firstSize; i++)
			first.add(random.nextInt(100));
		for(int i=0; i < secondSize; i++)
			second.add(random.nextInt(100));
		
		System.out.println(first);
		Sort.quickSort(first);
		System.out.println(first);
		
		/*
		Sort.stupidSort(first);
		Sort.stupidSort(second);
		List<Integer> merged = mergeSortedListsMemory(first, second);
		assert(merged.size() == firstSize + secondSize);
		for(int i=0; i < merged.size() -1; i++) {
			assert(merged.get(i) < merged.get(i+1));
		}
		*/
	}
}
