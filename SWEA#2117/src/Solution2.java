/**
 * 2117. 홈 방범 서비스
 * 37,988 kb  322 ms
 * 
 * @author minchae
 * @date 2024. 9. 3.
 * */

import java.util.*;
import java.io.*;

public class Solution2 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] map; // 집이 있는 위치 1, 나머지 0
	
	static ArrayList<Pair> home;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			home = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (map[i][j] == 1) {
						home.add(new Pair(i, j));
					}
				}
			}
			
			int answer = 0;
			
			for (int k = 1; k <= N + 1; k++) {
				int price = k * k + (k - 1) * (k - 1); // 운영 비용
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int cnt = 0;
						
						// 현재 점을 중심으로 모든 집과의 거리 계산
						for (Pair cur : home) {
							// k미만인 경우 방범 서비스 가능한 것
							if (getDist(cur, i, j) < k) {
								cnt++;
							}
						}
						
						// 손해를 보지 않는 경우 최댓값 갱신
						if (cnt * M - price >= 0) {
							answer = Math.max(answer, cnt);
						}
					}
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}
	
	private static int getDist(Pair cur, int x, int y) {
		return Math.abs(cur.x - x) + Math.abs(cur.y - y);
	}
	
}
