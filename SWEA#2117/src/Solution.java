/**
 * 2117. 홈 방범 서비스
 * 110,532 kb  906 ms
 * 
 * @author minchae
 * @date 2024. 9. 3.
 * */

import java.util.*;
import java.io.*;

public class Solution {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map; // 집이 있는 위치 1, 나머지 0

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			int home = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (map[i][j] == 1) {
						home++;
					}
				}
			}
			
			int answer = 0;
			int start = N % 2 == 0 ? N + 1 : N;
			
			for (int k = start; k >= 1; k--) {
				int price = (int) (Math.pow(k, 2) + Math.pow((k - 1), 2));
				
				if (price > home * M) {
					continue;
				}
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int cnt = bfs(i, j, k, price);
						
						answer = Math.max(answer, cnt);
					}
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}
	
	private static int bfs(int x, int y, int k, int price) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new Pair(x, y));
		visited[x][y] = true;
		
		int cnt = map[x][y];
		int time = 1;
		
		while (!q.isEmpty()) {
			if (time == k) {
				break;
			}
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Pair cur = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if (!isRange(nx, ny) || visited[nx][ny]) {
						continue;
					}
					
					q.add(new Pair(nx, ny));
					visited[nx][ny] = true;
					
					if (map[nx][ny] == 1) {
						cnt++;
					}
				}
			}
			
			time++;
		}
		
		return cnt;
		
		// 보안 회사의 이익 계산
//		int result = (cnt * M) - price;
//		
//		return result >= 0 ? cnt : 0;
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
