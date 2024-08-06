/**
 * 1251. 하나로
 * 
 * @author minchae
 * @date 2024. 8. 6.
 */

import java.io.*;
import java.util.*;

public class Solution {
	
	static class Node implements Comparable<Node> {
		int e;
		long w;
		
		public Node(int e, long w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static int N;
	static double E;
	
	static int[] landX;
	static int[] landY;
	
	static ArrayList<Node>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			landX = new int[N];
			landY = new int[N];
			
			list = new ArrayList[N];
			visited = new boolean[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				landX[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				landY[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			for (int i = 0; i < N; i++) {
				list[i] = new ArrayList<>();
				
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}
					
					long distX = Math.abs(landX[i] - landX[j]);
					long distY = Math.abs(landY[i] - landY[j]);
					
					// 연결된 섬의 번호와 거리 저장
					list[i].add(new Node(j, distX * distX + distY * distY));
				}
			}
			
			long answer = prim();
			
			System.out.println("#" + t + " " + Math.round(E * answer));
		}

	}
	
	// 프림 알고리즘 사용
	private static long prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(0, 0));
		
		int nodeCnt = 1;
		long result = 0; // 최소 해저터널 길이 저장
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 이미 방문된 노드일 경우 다음으로 넘어감
			if (visited[cur.e]) {
				continue;
			}
			
			result += cur.w; // 연결된 섬과의 해저터널 길이 더함
			visited[cur.e] = true; // 연결된 섬 방문 처리
			
			// 모든 섬을 다 방문한 경우
			if (nodeCnt++ == N) {
				return result; // 해저 터널 길이 반환
			}
			
			// 연결된 섬에서 연결된 또 다른 섬 탐색
			for (Node next : list[cur.e]) {
				if (!visited[next.e]) {
					pq.add(next);
				}
			}
		}
		
		return result;
	}

}
