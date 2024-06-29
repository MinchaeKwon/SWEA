/**
 * 1868. 파핑파핑 지뢰찾기
 * 
 * @author Minchae
 * @date 2024. 6. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
	
	static int N;
	
	static int[][] map; // 지뢰 -1
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) == '*' ? -1 : 0;
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 지뢰를 발견하면 근처 8방향의 칸에 지뢰 개수 증가 시킴
					if (map[i][j] == -1) {
						count(i, j);
					}
				}
			}
			
			int answer = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 근처 8방향에 지뢰가 없고 아직 방문하지 않은 경우
					if (map[i][j] == 0 && !visited[i][j]) {
						bfs(i, j);
						answer++;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 지뢰가 아니고, 탐색을 했는데도 아직 방문하지 않은 칸이 남아있는 경우
					if (map[i][j] != -1 && !visited[i][j]) {
						answer++;
					}
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}
	
	// 근처 지뢰 개수 세기
	private static void count(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isRange(nx, ny)) {
				continue;
			}
			
			if (map[nx][ny] != -1) {
				map[nx][ny]++;
			}
		}
	}
	
	// 근처에 지뢰가 없는 경우에 클릭을 해서 숫자를 표시함
	private static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 8; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == -1) {
					continue;
				}
				
				visited[nx][ny] = true;
				
				// 근처에 지뢰가 없는 경우에는 다시 탐색
				if (map[nx][ny] == 0) {
					q.add(new Pair(nx, ny));
				}
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
