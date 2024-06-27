/**
 * 1961. 숫자 배열 회전
 * 
 * @author Minchae
 * @date 2024. 6. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#" + t);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[N - j - 1][i]);
				}
				System.out.print(" ");
				
				for (int j = 0; j < N; j++) {
					System.out.print(map[N - i - 1][N - j - 1]);
				}
				System.out.print(" ");
				
				for (int j = 0; j < N; j++) {
					System.out.print(map[j][N - i - 1]);
				}
				System.out.println();
			}
		}

	}

}
