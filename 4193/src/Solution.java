/**
 * 4193. 수영대회 결승전
 * 
 * @author Minchae
 * @date 2024. 7. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node {
		int x;
		int y;
		int cnt;
		
		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N;
	
	static int[][] map; // 0 빈 칸, 1 장애물, 2 주기가 2초인 소용돌이
	static int sx, sy, ex, ey;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
//					if (map[i][j] == 1) {
//						map[i][j] = -1;
//					}
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			int answer = bfs();
			System.out.println("#" + t + " " + answer);
		}

	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new Node(sx, sy, 0));
		visited[sx][sy] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.x == ex && cur.y == ey) {
				return cur.cnt;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
					continue;
				}
				
				if (map[nx][ny] == 2) {
					if (cur.cnt % 3 == 2) { // 2초마다 소용돌이가 사라지기 때문에 %3을 해서 소용돌이가 사라졌는지 확인
						q.add(new Node(nx, ny, cur.cnt + 1));
						visited[nx][ny] = true;
					} else {
						// 소용돌이가 사라지지 않은 경우에는 제자리에 있기 때문에 다시 큐에 삽입
						// 제자리에 있는 경우에는 이전에 방문처리가 되어있기 때문에 다시 할 필요가 없음
						q.add(new Node(cur.x, cur.y, cur.cnt + 1));
					}
				} else {
					q.add(new Node(nx, ny, cur.cnt + 1));
					visited[nx][ny] = true;
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
