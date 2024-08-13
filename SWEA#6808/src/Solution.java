/**
 * D3 6808. 규영이와 인영이의 카드게임
 * 
 * @author minchae
 * @date 2024. 8. 13.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static final int TOTAL = 171;
	
	static int[] my; // 규영이의 카드 순서
	static boolean[] visited;
	
	static int win, lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			my = new int[9];
			visited = new boolean[18];
			
			win = 0;
			lose = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 9; i++) {
				my[i] = Integer.parseInt(st.nextToken());
				visited[my[i] - 1] = true;
			}
			
			permulation(0, 0);
			
			System.out.println("#" + t + " " + win + " " + lose);
		}
	}
	
	// sum : 규영이 점수 합
	private static void permulation(int depth, int sum) {
		if (depth == 9) {
			// 규영이가 이기는 경우
			if (sum > TOTAL - sum) {
				win++;
			}
			
			// 규영이가 지는 경우
			if (sum < TOTAL - sum) {
				lose++;
			}
			
			return;
		}
		
		for (int i = 0; i < 18; i++) {
			// 이미 뽑힌 숫자인 경우 다음으로 넘어감
			if (visited[i]) {
				continue;
			}
			
			visited[i] = true; // 인영이의 카드 숫자
			
			// 새로 뽑은 인영이의 숫자와 규영이 카드 숫자를 비교
			if (my[depth] > i) {
				permulation(depth + 1, sum + my[depth] + (i + 1)); // 이긴 경우 두 수의 총합을 더함
			} else {
				permulation(depth + 1, sum); // 숫자가 작아서 진 경우에는 sum을 그대로 넘김
			}
			
			visited[i] = false;
		}
	}

}
