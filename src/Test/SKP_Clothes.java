package Test;

public class SKP_Clothes {
	public static void main(String[] args) {
		String[] color = {"RG", "WR", "BW", "GG"};
		int[] prices = {5000, 6000};
		
		System.out.println(solution(color, prices));
	}
	
	public static int solution(String[] color, int[] prices) {
        int answer = 0;

        String[] top_color = new String[color.length];
        String[] bottom_color = new String[color.length];
        boolean[] is_select = new boolean[color.length];
        for(int i=0; i<color.length; i++){
            String[] temp = color[i].split("");
            top_color[i] = temp[0];
            bottom_color[i] = temp[1];
        }

        for(int i=0; i<top_color.length; i++){
            for(int j=0; j<bottom_color.length; j++){
                if(top_color[i].equals(bottom_color[j]) && !is_select[j]){
                    is_select[j] = true;
                    break;
                }
            }
        }

        for(int i=0; i<is_select.length; i++){
            if(is_select[i]) answer += prices[0];
            else answer += prices[1];
        }
        
        return Math.min((prices[0] * 5), answer);
    }

}
