/**
 * D6 1267. 작업순서
 * 25,472 kb  140 ms
 * 
 * @author minchae
 * @date 2024. 8. 27.
 * */

import java.util.*;
import java.io.*;

public class Solution {
	
	static int V, E;
	
	static ArrayList<Integer>[] list;
	static int[] degree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[V + 1];
			degree = new int[V + 1];
			
			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a].add(b);
				degree[b]++;
			}
			
			System.out.println("#" + t + " " + bfs());
		}
	}
	
	private static String bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		StringBuilder sb = new StringBuilder();
		
		// 진입 차수가 0인 정점 큐에 삽입
		for (int i = 1; i <= V; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			sb.append(cur).append(" ");
			
			for (int next : list[cur]) {
				degree[next]--; // 진입 간선 삭제
				
				// 차수가 0이 된 경우 큐에 삽입
				if (degree[next] == 0) {
					q.add(next);	
				}
			}
		}
		
		return sb.toString();
	}

}
