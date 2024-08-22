/**
 * 1861. 정사각형 방
 * 37,000 kb  266 ms
 * 
 * @author minchae
 * @date 2024. 8. 22.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	static int[][] map;
	
	static int max = 0;
	static int room = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			max = 0;
			room = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			move();
			
			System.out.println("#" + t + " " + room + " " + max);
		}

	}
	
	// 움직여보기
	private static void move() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i, j, 1);
			}
		}
	}
	
	// 갈 수 있는 방의 개수 구하기
	private static int dfs(int x, int y, int cnt) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isRange(nx, ny)) {
				continue;
			}
			
			if (map[nx][ny] - map[x][y] == 1) {
				cnt = dfs(nx, ny, cnt + 1);
				
				// 최대값 갱신
				if (cnt > max) {
					max = cnt;
					room = map[x][y];
				} else if (cnt == max) {
					// 현재 최대값과 같은 경우 숫자가 작은 방 번호로 갱신
					room = Math.min(room, map[x][y]);
				}
				
				break; // 방의 번호는 다 다르기 때문에 특정 방에서는 한 방향으로만 이동 가능해서 바로 break
			}
		}
		
		return cnt;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
