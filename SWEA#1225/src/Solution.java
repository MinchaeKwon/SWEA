/**
 * D3 1225. 암호생성기
 * 21,588 kb  122 ms
 * 
 * @author minchae
 * @date 2024. 8. 16.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			
			q = new LinkedList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			int cnt = 1;
			
			while (true) {
				// 한 사이클이 지난 경우
				if (cnt > 5) {
					cnt = 1;
				}
				
				int cur = q.poll();
				
				cur = cur - cnt < 0 ? 0 : cur - cnt; // 0보다 작아지는 경우 0으로 저장
				q.add(cur);
				
				// 0보다 작아지는 경우 해당 숫자 배열이 암호가 되기 때문에 종료
				if (cur == 0) {
					break;
				}
				
				cnt++; // 감소 수 증가
			}
			
			// 암호 배열 출력
			System.out.print("#" + t + " ");
			while (!q.isEmpty()) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();
		}
	}
}
