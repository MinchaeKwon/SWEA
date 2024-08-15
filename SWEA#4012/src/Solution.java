/**
 * 4012. 요리사
 * 
 * @author minchae
 * @date 2024. 8. 15.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	
	static int[][] map;
	static boolean[] visited;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N];
			
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			comb(0, 0);
			
			System.out.println("#" + t + " " + answer);
		}

	}
	
	private static void comb(int depth, int start) {
		if (depth == N / 2) {
			answer = Math.min(answer, getScore());
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			visited[i] = true;
			comb(depth + 1, i + 1);
			visited[i] = false;
		}
	}
	
	private static int getScore() {
		int a = 0;
		int b = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				
				if (visited[i] && visited[j]) { // 선택된 재료인 경우 
					a += map[i][j];
				} else if (!visited[i] && !visited[j]) {
					b += map[i][j];
				}
			}
		}
		
		return Math.abs(a - b);
	}

}
