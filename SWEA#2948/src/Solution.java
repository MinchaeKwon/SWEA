/**
 * 2948. 문자열 교집합
 * 
 * @author minchae
 * @date 2024. 8. 1.
 */

import java.io.*;
import java.util.*;

// HashMap 사용 <String, Integer> -> 특정 문자, 문자가 나타난 개수 저장
public class SWEA_2948 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			HashMap<String, Integer> hm = new HashMap<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				String input = st.nextToken();
				hm.put(input, hm.getOrDefault(input, 0) + 1);
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				String input = st.nextToken();
				hm.put(input, hm.getOrDefault(input, 0) + 1);
			}
			
			int cnt = 0;
			
			for (String key : hm.keySet()) {
				// 개수가 2개 이상일 때만 정답 개수 카운트
				if (hm.get(key) == 2) {
					cnt++;
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}

	}

}
