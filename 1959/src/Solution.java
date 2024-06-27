/**
 * 1959. 두 개의 숫자열
 * 
 * @author Minchae
 * @date 2024. 6. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M;
	static int[] A;
	static int[] B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			B = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			if (N > M) {
				int tmp = N;
				N = M;
				M = tmp;
				
				int[] tmpArr = A;
				A = B;
				B = tmpArr;
			}
			
			int answer = 0;
			
			for (int i = 0; i < Math.abs(N - M) + 1; i++) {
				int sum = 0;
				
				for (int j = 0; j < N; j++) {
					sum += A[j] * B[j + i];
				}
				
				answer = Math.max(answer, sum);
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}

}
