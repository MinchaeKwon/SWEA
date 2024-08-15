/**
 * D3 5215. 햄버거 다이어트
 * 20,328 kb  182 ms
 * 
 * @author minchae
 * @date 2024. 8. 14.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, L; // 재료의 수, 제한 칼로리
	static int[] score, K; // 맛에 대한 점수, 칼로리
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			score = new int[N];
			K = new int[N];
			
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				score[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0, 0, 0);
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	private static void comb(int depth, int cal, int sum) {
		// 제한 칼로리를 넘어가면 종료
		if (cal > L) {
			return;
		}
		
		if (depth == N) {
			answer = Math.max(answer, sum); // 최댓값 갱신
			return;
		}
		
		comb(depth + 1, cal + K[depth], sum + score[depth]); // 재료를 선택했을 때
		comb(depth + 1, cal, sum); // 선택하지 않았을 때
	}

}
