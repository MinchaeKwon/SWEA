/**
 * 1486. 장훈이의 높은 선반 찾기
 * 
 * @author Minchae
 * @date 2024. 6. 30.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, B;
	
	static int[] height;
	static boolean[] visited;
	
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			height = new int[N];
			visited = new boolean[N];
			
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			
			System.out.println("#" + t + " " + (min - B));
		}

	}
	
	private static void dfs(int depth) {
		if (depth == N) {
			int sum = 0;
			
			// 포함할 직원의 키만 더함
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					sum += height[i];
				}
			}
			
			// 최솟값 갱신
			if (sum >= B && min > sum) {
				min = sum;
			}
			
			return;
		}
		
		// 해당 직원을 포함할 경우
		visited[depth] = true;
		dfs(depth + 1);
		
		// 직원을 포함하지 않을 경우
		visited[depth] = false;
		dfs(depth + 1);
	}

}
