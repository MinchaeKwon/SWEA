/**
 * D5 3421. 수제 버거 장인
 * 21,516 kb  709 ms
 * 
 * @author minchae
 * @date 2024. 8. 16.
 */

import java.util.*;
import java.io.*;

public class Solution {

	static int N, M;
	static boolean[][] check;
	static boolean[] visited;

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			check = new boolean[N][N];
			visited = new boolean[N];

			answer = 0;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				// 동시에 들어가면 안되는 재료 체크
				check[a][b] = true;
				check[b][a] = true;
			}

			comb(0);

			System.out.println("#" + t + " " + answer);
		}
	}

	private static void comb(int depth) {
		if (depth == N) {
			if (checkBurger()) {
				answer++;
			}
			
			return;
		}

		visited[depth] = true;
		comb(depth + 1);

		visited[depth] = false;
		comb(depth + 1);
	}

	// 버거를 만들 수 있는지 확인
	private static boolean checkBurger() {
		// 동시에 들어갈 수 없는 재료가 선택된 경우 확인
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visited[i] && visited[j] && check[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}

}
