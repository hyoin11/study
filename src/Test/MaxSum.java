package Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxSum {
	public static int findMaxSum(List<Integer> list) {
		Collections.sort(list);
		
		int largest_sum = 0;
		for(int i=list.size()-1; i>=list.size()-2; i--) {
			largest_sum += list.get(i);
		}
		
		return largest_sum;
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(5, 9, 7, 11);
		System.out.println(findMaxSum(list));
	}
}
