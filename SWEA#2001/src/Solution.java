/**
 * D2 2001. 파리퇴치
 * 20,036 kb  120 ms
 * 
 * @author minchae
 * @date 2024. 8. 14.
 */

import java.util.*;
import java.io.*;

// 2차원배열 누적합
public class Solution {
	
	static int N, M;
	
	static int[][] map; // 파리 개수 저장
	static int[][] sum; // 파리 개수 누적합 저장

 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			sum = new int[N + 1][N + 1];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// 2차원 배열에서의 누적합 저장
					// 이전 행의 누적합 + 이전 열의 누적합 - 중복값 + 현재 칸의 값
					sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + map[i][j];
				}
			}
			
			int answer = 0;
			
			// M * M 크기만큼 잘라서 최댓값 구함
			for (int i = M; i <= N; i++) {
				for (int j = M; j <= N; j++) {
					// M * M 만큼 잘랐을 때 현재 칸의 값 - (이전 행의 누적합 + 이전 열의 누적합 - 중복값)
					int cnt = sum[i][j] - (sum[i - M][j] + sum[i][j - M] - sum[i - M][j - M]);
					answer = Math.max(answer, cnt);
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}

}
