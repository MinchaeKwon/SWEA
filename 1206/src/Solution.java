/**
 * 1206. [S/W 문제해결 기본] 1일차 - View
 * 
 * @author Minchae Gwon
 * @date 2023. 11. 16
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] building = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				building[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = 0; // 조망권이 확보된 세대
			
			// 맨 왼쪽 두 칸과 맨 오른쪽 두 칸에는 건물이 지어지지 않기 때문에 2부터 시작
			for (int i = 2; i < N - 2; i++) {
				// 현재 빌딩 좌우에 있는 두개의 빌딩의 최대 높이를 구함
				int max = Math.max(building[i - 2], Math.max(building[i - 1], Math.max(building[i + 1], building[i + 2])));
				
				 // 최대 높이보다 현재 빌딩의 높이가 더 큰 경우
				if (building[i] - max > 0) {
					result += building[i] - max;
				}
			}
			
			System.out.println("#" + t  + " " + result);
		}
	}

}
