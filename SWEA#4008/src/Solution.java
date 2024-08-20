/**
 * 4008. 숫자 만들기
 * 18,724 kb  116 ms
 * 
 * @author minchae
 * @date 2024. 8. 20.
 */

import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[] op; // '+', '-', '*', '/' 순서
	static int[] num;
	
	static int min, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			num = new int[N];
			op = new int[4];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			select(1, num[0]);
			
			System.out.println("#" + t + " " + (max - min));
		}
	}
	
	private static void select(int depth, int result) {
		if (depth == N) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			
//			System.out.println(result);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (op[i] <= 0) {
				continue;
			}
			
			op[i]--; // 개수 감소
			select(depth + 1, calculate(result, num[depth], i));
			op[i]++; // 원복
		}
	}
	
	private static int calculate(int n1, int n2, int op) {
		int result = 0;
		
		switch (op) {
		case 0:
			result = n1 + n2;
			break;
		case 1:
			result = n1 - n2;
			break;
		case 2:
			result = n1 * n2;
			break;
		case 3:
			result = n1 / n2;
			break;
		}
		
		return result;
	}

}
