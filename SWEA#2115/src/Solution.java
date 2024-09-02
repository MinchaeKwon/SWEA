/**
 * 2115. 벌꿀 채취
 * 19,144 kb  113 ms
 * 
 * @author minchae
 * @date 2024. 9. 2
 * */

import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, M, C;
	static int[][] map;
	
	static int answer;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			
			select();
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	// 두 일꾼의 벌통 선택
	private static void select() {
		int result1 = 0;
		int result2 = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				// 일꾼 1의 벌통 선택
				max = 0;
				collect(0, i, j, 0, 0);
				
				result1 = max;
				
				// 일꾼 2의 벌통 선택
				max = 0;
				
				// 일꾼1이 선택한 벌통 다음 열에서 선택
				for (int k = j + M; k < N - M + 1; k++) {
					collect(0, i, k, 0, 0);
				}
				
				// 일꾼 1이 선택한 벌통 다음 행에서 선택
				for (int k = i + 1; k < N; k++) {
					for (int z = 0; z < N - M + 1; z++) {
						collect(0, k, z, 0, 0);
					}
				}
				
				result2 = max;
				
				answer = Math.max(answer, result1 + result2);
			}
		}
	}

	// 벌통에서 꿀 채취하고 수익 계산
	private static void collect(int depth, int x, int y, int total, int profit) {
		// 최대 양을 초과하면 종료
		if (total > C) {
			return;
		}
		
		// 선택한 벌통 개수만큼 진행한 경우 종료
		if (depth == M) {
			max = Math.max(max, profit);
			return;
		}
		
		collect(depth + 1, x, y + 1, total, profit);
		collect(depth + 1, x, y + 1, total + map[x][y], profit + map[x][y] * map[x][y]);
	}
	
}
