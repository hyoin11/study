package Programmers_;

public class Kakao22_sheepAndWolf {
	static int sheep_count = 0;
	static int wolf_count = 0;
	
	public static void main(String[] args) {
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		
		System.out.println(solution(info, edges));
	}
	
	public static int solution(int[] info, int[][] edges) {
//		int sheep_count = 0;
//		int wolf_count = 0;
		
		dfs(info, 0, edges);
//		for(int i=0; i<edges.length; i++) {
//			System.out.println(edges[i][0]);
//		}
		
		System.out.println("sheep_count" + sheep_count);
		System.out.println("wolf_count" + wolf_count);
		return 0;
	}
	
	public static void dfs(int[] info, int idx, int[][] edges) {
		System.out.println(idx);
//		if(info[idx] == 0) sheep_count++;
//		else wolf_count++;
//		
//		if(sheep_count <= wolf_count) {
//			wolf_count--;
//			return;
//		}
//		
//		boolean is_leaf = true;
//		for(int i=0; i<edges.length; i++) {
//			if(edges[i][0] == idx) {
//				dfs(info, edges[i][1], edges);
//				is_leaf = false;
//			}
//		}
//		if(!is_leaf && (info[idx] == 1)) {
//			wolf_count--;
//			return;
//		}
		for(int i=0; i<edges.length; i++) {
			if(edges[i][0] == idx) {
				dfs(info, edges[i][1], edges);
			}
		}
	}
}
