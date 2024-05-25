/**
 * 1954. 달팽이 숫자
 * 
 * @author Minchae
 * @date 2024. 5. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	// 우하좌상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			
			// 시작점
			int x = 0;
			int y = 0;
			int dir = 0;
			
			for (int i = 1; i <= N * N; i++)  {
				map[x][y] = i;
				
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				// 범위를 벗어나거나 다른 숫자가 있는 경우 방향 바꿈
				if (!isRange(nx, ny) || map[nx][ny] != 0) {
					dir = (dir + 1) % 4;
					
					nx = x + dx[dir];
					ny = y + dy[dir];
				}
				
				x = nx;
				y = ny;
			}
			
			System.out.println("#" + t);
			
			for (int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}

	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
