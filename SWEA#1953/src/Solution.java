/**
 * 1953. 탈주범 검거
 * 26,140 kb  220 ms
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
	
	static int N, M, R, C, L;
	
	static int[][] map;
	
	static HashMap<Integer, List<Integer>> dirMap; // 2차원 배열도 가능
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		makeMap();
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = move();
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	private static void makeMap() {
		dirMap = new HashMap<>();
		
		dirMap.put(1, Arrays.asList(0, 1, 2, 3));
		dirMap.put(2, Arrays.asList(0, 1));
		dirMap.put(3, Arrays.asList(2, 3));
		dirMap.put(4, Arrays.asList(0, 3));
		dirMap.put(5, Arrays.asList(1, 3));
		dirMap.put(6, Arrays.asList(1, 2));
		dirMap.put(7, Arrays.asList(0, 2));
	}
	
	// 맨홀 뚜껑으로 들어가서 이동
	private static int move() {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Pair(R, C)); // R, C에서 시작 -> 이때 시간은 1
		visited[R][C] = true;
		
		int time = 1;
		int result = 1;
		
		while (!q.isEmpty()) {
			// L만큼 이동 (1부터 시작)
			if (time == L) {
				return result;
			}
			
			// 1시간마다 한 칸씩 이동 가능하고 L시간동안 이동했는지 확인해야 하므로 큐의 크기만큼 반복
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Pair cur = q.poll();
				
				for (int dir : dirMap.get(map[cur.x][cur.y])) {
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];
					
					// 범위 벗어남 || 이미 방문 || 터널이 없는 곳
					if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) {
						continue;
					}
					
					// 서로 연결되어 있지 않은 경우
					if (!check(cur.x, cur.y, nx, ny)) {
						continue;
					}
					
					q.add(new Pair(nx, ny));
					visited[nx][ny] = true;
					
					result++;
				}
			}
			
			time++;
		}
		
		return result;
	}
	
	// 다음 칸에서 이동할 수 있는 방향으로 이동했을 때 현재 칸에 도달하는지 확인
	private static boolean check(int x, int y, int nx, int ny) {
		int type = map[nx][ny];
		
		for (int dir : dirMap.get(type)) {
			if (nx + dx[dir] == x && ny + dy[dir] == y) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
