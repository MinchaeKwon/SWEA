/**
 * D4 1210. Ladder1
 * 
 * @author minchae
 * @date 2024. 8. 13.
 */

import java.util.*;
import java.io.*;

// 도착지부터 시작해서 출발지 찾기
public class Solution {
	
	static final int SIZE = 100;
	
	// 좌우상 (좌우로 가는 게 우선순위가 더 높음)
	static int[] dx = {0, 0, -1};
	static int[] dy = {-1, 1, 0};
	
	static int[][] map;
	static int ex, ey;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 0; t < 10; t++) {
			int T = Integer.parseInt(br.readLine());
			
			map = new int[SIZE][SIZE];
			
			for (int i = 0 ; i < SIZE; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < SIZE; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// 도착지인 경우
					if (map[i][j] == 2) {
						ex = i;
						ey = j;
					}
				}
			}
			
			int answer = solve(ex, ey);
			
			System.out.println("#" + T + " " + answer);
		}
	}
	
	private static int solve(int x, int y) {
		// 행이 0이 될 때까지 진행 -> 0이 되는 것은 출발지를 발견한 것
		while (x != 0) {
			// 3방향 탐색
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 범위를 벗어나거나 지나갈 수 없는 곳인 경우
				if (!isRange(nx, ny) || map[nx][ny] != 1) {
					continue;
				}
				
				map[nx][ny] = 3; // 이미 지나간 길 표시
				
				// 위치 갱신
				x = nx;
				y = ny;
			}
		}
		
		return y; // x가 0이 될 때 y값이 출발점 좌표
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
	}

}
