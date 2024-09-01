/**
 * 1767. 프로세서 연결하기
 * 23,016 kb  143 ms
 * 
 * @author minchae
 * @date 2024. 8. 30.
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
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N;

	static int[][] map; // 0은 빈 cell, 1은 core
	static ArrayList<Pair> core;

	static int max; // 연결된 전선 개수
	static int answer; // 전선 길이의 합 (최소)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			core = new ArrayList<>();

			max = 0;
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1 && !isEnd(i, j)) {
						core.add(new Pair(i, j));
					}
				}
			}

			simulation(0, 0, 0);

			System.out.println("#" + t + " " + answer);
		}
	}

	private static void simulation(int depth, int cnt, int sum) {
		// core.size() - depth => 선택할 수 있는 코어 개수
		// 현재 선택된 코어 개수 + 남은 코어 개수를 했는데도 현재 max값보다 작다면 밑에는 더이상 볼 필요 없음
		if (max > cnt + core.size() - depth) {
			return;
		}
		
		if (depth == core.size()) {
			if (cnt > max) {
				max = cnt;
				answer = sum;
			} else if (cnt == max) {
				// 최대한 많은 프로세서에 연결하는 경우가 여러가지인 경우 최소 전선 길이 구함
				answer = Math.min(answer, sum);
			}

			return;
		}

		Pair cur = core.get(depth);

		// 상하좌우 4방향 확인
		for (int i = 0; i < 4; i++) {
			int nx = cur.x;
			int ny = cur.y;
			int len = 0;
			
			boolean connect = false;

			// 해당 방향으로 전원을 연결할 수 있는지 확인
			while (true) {
				nx += dx[i];
				ny += dy[i];

				// 범위를 벗어나면 전원을 연결할 수 있는 것
				if (!isRange(nx, ny)) {
					connect = true;
					break;
				}

				// 전선을 놓다가 다른 프로세서가 있거나 다른 전선을 만나는 경우 -> 다음 방향 탐색
				if (map[nx][ny] != 0) {
					break;
				}
				
				map[nx][ny] = 2; // 전선 놓기
				len++;
			}
			
			if (connect) {
				simulation(depth + 1, cnt + 1, sum + len); // 전선 연결 O
				restore(nx, ny, len, i); // 연결 가능하면 다시 돌아온 뒤에 원복
			} else {
				restore(nx, ny, len, i); // 연결 하지 못하는 경우에는 맵을 원복하고 재귀 호출
				simulation(depth + 1, cnt, sum); // 전선 연결 X
			}
		}
	}
	
	// 원복
	private static void restore(int x, int y, int len, int dir) {
		for (int i = 0; i < len; i++) {
			x -= dx[dir];
			y -= dy[dir];
			
			map[x][y] = 0;
		}
	}

	// 프로세서가 가장자리에 있는지 확인
	private static boolean isEnd(int x, int y) {
		return x == 0 || x == N - 1 || y == 0 || y == N - 1;
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
