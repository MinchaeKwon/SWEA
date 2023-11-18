/**
 * 1249. [S/W 문제해결 응용] 4일차 - 보급로
 * 
 * @author Minchae
 * @date 2023. 11. 18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Pos implements Comparable<Pos> {
	int x;
	int y;
	int time; // 복구 시간
	
	public Pos(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}

	@Override
	public int compareTo(Pos o) {
		return this.time - o.time; // 복구 시간을 기준으로 오름차순 정렬
	}
}

public class Solution {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	static int[][] map;
	
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			
			result = Integer.MAX_VALUE;
			
			bfs();
			
			System.out.println("#" + t + " " + result);
		}

	}
	
	private static void bfs() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		
		pq.add(new Pos(0, 0, 0));
		visited[0][0] = true;
		
		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if (cur.x == N - 1 && cur.y == N - 1) {
				result = Math.min(result, cur.time);
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나지 않고 방문하지 않은 경우
				if (isRange(nx, ny) && !visited[nx][ny]) {
					pq.add(new Pos(nx, ny, cur.time + map[nx][ny]));
					visited[nx][ny] = true;
				}
			}
		}
		
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
