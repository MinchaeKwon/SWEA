/**
 * 5643. 키 순서
 * 90,596 kb  1,332 ms
 * 
 * @author minchae
 * @date 2024. 9. 5.
 * */

import java.util.*;
import java.io.*;

// 특정 사람이 다른 모든 사람과 연결되어 있으면 키를 알 수 있음
// 플로이드 워셜 사용하면 모든 정점에서 다른 모든 정점까지의 거리 알 수 있음
// INF가 아닌 거리가 저장되어 있는 경우 연결되어 있는 것
public class Solution {
	
	static final int INF = 123456789;
	
	static int N, M;
	static int[][] map; // 여기서는 최단 경로를 구하는 것이 아니기 때문에 boolean으로 선언해서 사용 가능

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			map = new int[N + 1][N + 1];
			
			for (int i = 1; i <= N; i++) {
				Arrays.fill(map[i], INF);
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				map[a][b] = 1;
			}
			
			int answer = floyd();
			
			System.out.println("#" + t + " " + answer);
		}

	}
	
	// 플로이드 워셜
	private static int floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		int result = 0;
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			
			for (int j = 1; j <= N; j++) {
				// 자신보다 키가 큰 학생이나 작은 학생이 존재하는 경우
				if (map[i][j] != INF || map[j][i] != INF) {
					cnt++;
				}
			}
			
			// 자신을 제외하고 다른 사람과 연결되어 있는 경우
			if (cnt == N - 1) {
				result++;
			}
		}
		
		return result;
	}

}
