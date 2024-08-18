/**
 * D2 14510. 나무 높이
 * 
 * @author minchae
 * @date 2024. 8. 18.
 */

import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] tree = new int[N];
			int max = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				
				max = Math.max(max, tree[i]);
			}
			
			int even = 0; // 키가 2만큼 자라야 하는 나무의 개수
			int odd = 0; // 키가 1만큼 자라야 하는 나무의 개수
			
			for (int i = 0; i < N; i++) {
				int diff = max - tree[i];
				
				if (diff == 0) {
					continue;
				}
				
				even += diff / 2;
				odd += diff % 2;
			}
			
			// 짝수와 홀수 개수가 1만 차이 나도록 수정
			if (even > odd && even - odd > 1) {
				while (even - odd > 1) {
					even -= 1;
					odd += 2;
				}
			}
			
			int answer = 0;
			
			if (odd > even) {
				answer = odd * 2 - 1;
			} else if (even > odd) {
				answer = even * 2;
			} else {
				answer = even + odd;
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}

}
