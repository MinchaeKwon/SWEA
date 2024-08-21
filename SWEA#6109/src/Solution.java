/**
 * D4 6109. 추억의 2048 게임
 * 46,556 kb  219 ms
 * 
 * @author minchae
 * @date 2024. 8. 21.
 */

import java.util.*;
import java.io.*;

public class Solution {

	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N;

	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			String S = st.nextToken();

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			solve(S);

			System.out.println("#" + t);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}

	}

	private static void solve(String dir) {
		visited = new boolean[N][N];

		// 상, 하 -> 열마다 확인
		// 좌, 우 -> 행마다 확인
		switch (dir) {
		case "up": // 상
			// 맨 위의 열부터 확인
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					moveBlock(i, j, 0);
				}
			}

			break;
		case "down": // 하
			// 맨 밑의 열부터 확인
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i >= 0; i--) {
					moveBlock(i, j, 1);
				}
			}

			break;
		case "left": // 좌
			// 맨 왼쪽 행부터 확인
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					moveBlock(i, j, 2);
				}
			}

			break;
		case "right": // 우
			// 맨 오른쪽 행부터 확인
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					moveBlock(i, j, 3);
				}
			}

			break;
		}
	}

	// 특정 방향으로 블록 이동시키기
	private static void moveBlock(int x, int y, int dir) {
		// 현재 칸이 빈 칸인 경우 넘어감
		if (map[x][y] == 0) {
			return;
		}

		// 해당 방향에 빈 칸이 없을 때까지 이동
		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// 범위를 벗어나거나 이미 숫자가 합쳐진 칸인 경우
			if (!isRange(nx, ny) || visited[nx][ny]) {
				return;
			}

			// 숫자가 있는 경우 (빈 칸이 아닌 경우)
			if (map[nx][ny] > 0) {
				// 이동하려는 칸에 같은 숫자가 있는 경우 -> 숫자 합치고 종료
				if (map[nx][ny] == map[x][y]) {
					map[nx][ny] *= 2;
					map[x][y] = 0;

					visited[nx][ny] = true;
				}

				return;
			}

			// 빈 칸인 경우 이동 시킴
			map[nx][ny] = map[x][y];
			map[x][y] = 0;

			// 위치 갱신
			x = nx;
			y = ny;
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
