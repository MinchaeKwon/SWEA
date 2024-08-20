/**
 * D4 4796. 의석이의 우뚝 선 산
 * 103,956 kb  661 ms
 * 
 * @author minchae
 * @date 2024. 8. 20.
 */

import java.util.*;

public class Solution {

	static int[] h;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			
			h = new int[N];
			
			for (int i = 0; i < N; i++) {
				h[i] = sc.nextInt();
			}
			
			int up = 0;
			int down = 0;
			
			int answer = 0;
			
			for (int i = 0; i < N - 1; i++) {
				if (h[i] < h[i + 1]) {
					// 우뚝 산을 발견하고 내려가다가 다시 높은 산을 만난 경우
					if (down > 0) {
						answer += up * down;
						
						// 초기화
						up = 0;
						down = 0;
					}
					
					up += 1;
				} else {
					down += 1;
				}
			}
			
			// 우뚝 산이 만들어지는 경우가 남은 경우
			if (up > 0 && down > 0) {
				answer  += up * down;
			}
			
			System.out.println("#" + t + " " + answer);
		}

		sc.close();
	}

}
