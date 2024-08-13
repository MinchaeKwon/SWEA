/**
 * D2 1954. 달팽이 숫자
 * 
 * @author minchae
 * @date 2024. 8. 13.
 */

import java.io.*;

public class Solution {
	
	// 우하좌상 -> 이 순서로 숫자가 채워짐
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			// 시작점, 시작 방향
			int x = 0;
			int y = 0;
			int dir = 0;
			
			for (int i = 1; i <= N * N; i++) {
				map[x][y] = i; // 숫자 넣어줌
				
				// 다음 위치 구함
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				// 범위를 벗어나거나 이미 숫자가 있는 경우
				if (!isRange(nx, ny) || map[nx][ny] != 0) {
					dir = (dir + 1) % 4; // 다음 방향으로 바꾸기
					
					// 방향 바꾼 후에 다음 위치 구함 (범위 벗어난 경우 방향을 바꾸기 때문에 다시 체크 안해도 됨)
					nx = x + dx[dir];
					ny = y + dy[dir];
				}
				
				// 위치 갱신
				x = nx;
				y = ny;
			}
			
			System.out.println("#" + t);
			printMap();
		}
	}
	
	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
