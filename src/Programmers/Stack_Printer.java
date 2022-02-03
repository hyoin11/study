package Programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Stack_Printer {
	public static void main(String[] args) {
		int[] priorities = {2,1,3,2};
		int location = 2;
		
		System.out.println(solution(priorities, location));
	}
	
	public static int solution(int[] priorities, int location) {
		int answer = 0;
		
		Queue<HashMap<Integer, Integer>> que_pri = new LinkedList<>();
		Queue<HashMap<Integer, Integer>> que_result = new LinkedList<>();
		
		for(int i=0; i<priorities.length; i++) {
			HashMap<Integer, Integer> hm = new HashMap<>();
			hm.put(i, priorities[i]);
			
			que_pri.add(hm);
		}
		System.out.println(que_pri);
//		System.out.println(que_pri.poll().keySet());
		
		HashMap<Integer, Integer> hm_cur = new HashMap<>();
		int cur = 0;
		while(!que_pri.isEmpty()) {
			if(cur == 0) {
				hm_cur = que_pri.poll();
				cur = hm_cur.get(hm_cur.keySet().iterator().next());
			}
			HashMap<Integer, Integer> hm_next = que_pri.poll();
			int next = hm_next.get(hm_next.keySet().iterator().next());
			
			System.out.println("cur " + cur + " next " + next);
			if(cur < next) {
				// 더 큰 우선순위가 있는 경우
				que_pri.add(hm_cur);
			}
		}
//		for(int i=0; i<priorities.length-1; i++) {
//			for(int j=i+1; j<priorities.length; j++) {
//				
//			}
//		}
		return answer;
	}
}
