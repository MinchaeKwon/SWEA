/**
 * 1949. 등산로 조성
 * 
 * @author minchae
 * @date 2024. 8. 15.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, K;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int maxHeight;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			maxHeight = 0;
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != maxHeight) {
						continue;
					}
					
					visited[i][j] = true;
					dfs(i, j, 1, false);
					visited[i][j] = false;
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	// 현재 좌표 x, y, 등산로 길이, 지형을 깎았는지
	private static void dfs(int x, int y, int length, boolean cut) {
		int cur = map[x][y];
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isRange(nx, ny) || visited[nx][ny]) {
				continue;
			}
			
			if (map[nx][ny] < cur) { // 경사가 낮아서 갈 수 있는 경우
				visited[nx][ny] = true;
				dfs(nx, ny, length + 1, cut);
				visited[nx][ny] = false;
			} else if (!cut) { // 높이가 같거나 높아서 갈 수 없는데 지형을 깎지 않은 경우
				if (map[nx][ny] - K < cur) {
					int tmp = map[nx][ny];
					
					visited[nx][ny] = true;
					map[nx][ny] = cur - 1; // 현재 높이에서 1만 깎아야 등산로를 최대한 길게 만들 수 있음
					
					dfs(nx, ny, length + 1, true);
					
					visited[nx][ny] = false;
					map[nx][ny] = tmp; // 원복
				}
			}
		}
		
		answer = Math.max(answer, length);
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
