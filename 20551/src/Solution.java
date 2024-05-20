/**
 * 20551. 증가하는 사탕 수열
 * 
 * @author Minchae
 * @date 2024. 5. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			
			if (B >= C) {
				answer += B - C + 1;
				B = C - 1;
			}
			
			if (A >= B) {
				answer += A - B + 1;
				A = B - 1;
			}
			
			if (A < 1 || B < 1 || C < 1) {
				answer = -1;
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}

}
