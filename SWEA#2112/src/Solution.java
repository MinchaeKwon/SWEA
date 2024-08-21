/**
 * 2112. 보호 필름
 * 117,744 kb  433 ms
 * 
 * @author minchae
 * @date 2024. 8. 21.
 */

import java.util.*;
import java.io.*;

public class Solution {

	static int D, W, K;
	
	static int[][] map; // 특성A는 0, 특성B는 1
	static int[][] copy; // 원복을 위해 복사된 맵 사용

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[D][W];
			copy = new int[D][W];

			answer = D;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					copy[i][j] = map[i][j];
				}
			}

			// 약품을 투입하지 않고도 성능 검사를 통과하는 경우 다음 테스트케이스로 넘어감
			if (check()) {
				System.out.println("#" + t + " " + 0);
				continue;
			}

			select(0, 0);
			
			System.out.println("#" + t + " " + answer);
		}

	}

	private static void select(int depth, int cnt) {
		// 현재 저장되어 있는 최소값보다 큰 경우 바로 종료
		if (cnt > answer) {
			return;
		}

		if (depth == D) {
			// 성능 검사를 통과하는 경우
			if (check()) {
				answer = Math.min(answer, cnt);
			}

			return;
		}

		// 약품 투입 X
		select(depth + 1, cnt);

		// 약품 A 투입
		Arrays.fill(copy[depth], 0); // 약품 투입
		select(depth + 1, cnt + 1);
		copy[depth] = Arrays.copyOf(map[depth], W); // 원복

		// 약품 B 투입
		Arrays.fill(copy[depth], 1); // 약품 투입
		select(depth + 1, cnt + 1);
		copy[depth] = Arrays.copyOf(map[depth], W); // 원복
	}

	// 성능 검사 통과 하는지 확인 (모든 열에 동일한 특성을 지닌 셀이 K개 이상 있는지 확인)
	private static boolean check() {
		for (int j = 0; j < W; j++) { // 열마다 확인
			int cur = copy[0][j];
			int cnt = 1;

			for (int i = 1; i < D; i++) {
				// 이미 K개 이상 연속된 경우에는 다음 열을 확인
				if (cnt >= K) {
					break;
				}
				
				if (copy[i][j] == cur) {
					cnt++;
				} else {
					cnt = 1;
					cur = copy[i][j];
				}
			}

			// 한 줄이라도 K개 이상 연속되는 셀이 없을 경우 성능 검사 통과하지 못함
			if (cnt < K) {
				return false;
			}
		}

		return true;
	}
}
