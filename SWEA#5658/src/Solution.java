/**
 * 5658. 보물상자 비밀번호
 * 18,608 kb  102 ms
 *
 * @author minchae
 * @date 2024. 9. 6.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, K;
	static String num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			num = br.readLine();
			
			ArrayList<Integer> password = new ArrayList<>();
			
			// 4로 나눈 수만큼 회전
			for (int r = 0; r <= N / 4; r++) {
				int s = 0;
				int e = N / 4;
				
				// 각 변에 있는 숫자를 비밀번호로 만듦
				for (int i = 0; i < 4; i++) {
					String tmp = num.substring(s, e);
					
					int convert = Integer.parseInt(tmp, 16); // 16진수를 10진수로 변환
					
					// 포함되어 있지 않은 경우에만 추가
					if (!password.contains(convert)) {
						password.add(convert);
					}
					
					// 다음 숫자
					s = e;
					e += N / 4;
				}
				
				// 한 칸 시계 방향으로 이동
				char tmp = num.charAt(num.length() - 1);
				num = tmp + num.substring(0, num.length() - 1);
			}
			
			Collections.sort(password, Collections.reverseOrder());
			System.out.println("#" + t + " " + password.get(K - 1)); // 인덱스가 0부터 시작이기 때문에 -1 해줌
		}
	}

}
