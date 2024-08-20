/**
 * D4 1486. 장훈이의 높은 선반
 * 20,240 kb  120 ms
 * 
 * @author minchae
 * @date 2024. 8. 20.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, B;
	static int[] H;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			H = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			
			solve(0, 0);
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	private static void solve(int depth, int sum) {
		if (depth == N) {
			if (sum >= B) {
				answer = Math.min(answer, (sum - B)); // B와의 차이 최솟값 구함
			}
			
			return;
		}
		
		solve(depth + 1, sum + H[depth]); // 해당 점원의 키를 포함했을 때
		solve(depth + 1, sum); // 포함하지 않았을 때
	}

}
