/**
 * 1244. [S/W 문제해결 응용] 2일차 - 최대 상금
 * 
 * @author Minchae
 * @date 2023. 11. 17
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static String[] arr;
	static int cnt;
	
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			arr = st.nextToken().split(""); // 숫자판의 정보
			cnt = Integer.parseInt(st.nextToken()); // 교환 횟수
			
			// 교환 횟수는 숫자판의 길이를 넘지 않게 함
			if (cnt > arr.length) {
				cnt = arr.length;
			}
			
			max = 0;
			
			dfs(0, 0);
			
			System.out.println("#" + t + " " + max);
		}

	}
	
	private static void dfs(int start, int depth) {
		// 횟수만큼 바꾼 경우에 최댓값을 구함
		if (depth == cnt) {
			String result = "";
			
			for (String s : arr) {
				result += s;
			}
			
			max = Math.max(max, Integer.parseInt(result));
			
			return;
		}
		
		// 두개의 숫자를 바꿔봄
		for (int i = start; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				swap(i, j);
				dfs(i, depth + 1);
				swap(i, j);
			}
		}
	}
	
	private static void swap(int a, int b) {
		String tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
