/**
 * 1952. 수영장
 * 18,392 kb  109 ms
 * 
 * @author minchae
 * @date 2024. 9. 4.
 * */

import java.util.*;
import java.io.*;

public class Solution2 {

	static int[] price;
	static int[] use;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			price = new int[4];
			use = new int[13];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= 12; i++) {
				use[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[13];
			
			for (int i = 1; i <= 12; i++) {
				if (i < 3) {
					// 이전 달 요금 + (1달 이용권 금액, 1일 이용권) 금액 최소
					dp[i] = dp[i - 1] + Math.min(price[1], price[0] * use[i]);
				} else {
					// (3달 이용권 금액, 이전 달 요금 + (1달 이용권 금액, 1일 이용권) 금액 최소)
					dp[i] = Math.min(dp[i - 3] + price[2], dp[i - 1] + Math.min(price[1], price[0] * use[i]));
				}
			}

			// 마지막에 1년 이용권 금액과 비교해서 작은 값 출력
			System.out.println("#" + t + " " + Math.min(dp[12], price[3]));
		}
	}

}