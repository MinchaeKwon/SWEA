/**
 * 5684 운동
 * 
 * @author minchae
 * @date 2024. 7. 24.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static class Node {
		int e;
		int c;
		
		public Node(int e, int c) {
			this.e = e;
			this.c = c;
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	
	static int N, M;
	static ArrayList<Node>[] map;
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new ArrayList[N];
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				map[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken()) - 1;
				int e = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				
				map[s].add(new Node(e, c));
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i);
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	private static void bfs(int start) {
		Queue<int[]> q = new LinkedList<>(); // {시작점, 도로의 합}
		boolean[] visited = new boolean[N];
		int[] dist = new int[N]; // 특정 도시까지의 최소 거리
		
		Arrays.fill(dist, INF); // 초기화
		
		q.add(new int[] {start, 0});
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 다시 시작점으로 돌아온 경우
			if (start == cur[0]) {
				answer = Math.min(answer, dist[start]);
			}
			
			// 현재 저장된 최솟값보다 큰 경우 다음으로 넘어감
			if (cur[1] > answer) {
				continue;
			}
			
			for (Node next : map[cur[0]]) {
				// 해당 도시에 저장된 거리보다 작을 경우 값 갱신하고 큐에 삽입
				if (cur[1] + next.c < dist[next.e]) {
					dist[next.e] = cur[1] + next.c;
					q.add(new int[] {next.e, cur[1] + next.c});
				}
			}
		}
	}
	
}
