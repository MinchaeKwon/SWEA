/**
 * D5 1247. 최적 경로
 * 19,856 kb  266 ms
 * 
 * @author minchae
 * @date 2024. 8. 23.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// 두 고객 사이의 거리 구하기
		public int getDist(Node o) {
			return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
		}
	}
	
	static int N;
	static boolean[] visited;
	
	static Node company, home;
	static Node[] customer;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			company = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			customer = new Node[N];
			
			for (int i = 0; i < N; i++) {
				customer[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			visited = new boolean[N];
			answer = Integer.MAX_VALUE;
			
			dfs(0, company, 0); // 회사에서 시작
			
			System.out.println("#" + t + " " + answer);
		}
	}

	private static void dfs(int depth, Node cur, int sum) {
		// 현재 최소값보다 큰 경우 종료
		if (answer < sum) {
			return;
		}
		
		// 모든 고객을 다 방문한 경우
		if (depth == N) {
			answer = Math.min(answer, sum + cur.getDist(home));
			return;
		}
		
		// 고객들을 방문하면서 거리를 계산 -> 모든 가능한 경로를 살피는 것
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			
			visited[i] = true;
			dfs(depth + 1, customer[i], sum + cur.getDist(customer[i]));
			visited[i] = false;
		}
	}
	
}
