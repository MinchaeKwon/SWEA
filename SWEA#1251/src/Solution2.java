/**
 * D4 1251. 하나로
 * 95,628 kb  401 ms
 * 
 * @author minchae
 * @date 2024. 8. 29.
 * */

import java.io.*;
import java.util.*;

public class Solution2 {

	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		long w;

		public Edge(int s, int e, long w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}

	static int N;
	static double E;

	static int[] landX;
	static int[] landY;

	static int[] parent;

	static PriorityQueue<Edge> pq;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			landX = new int[N];
			landY = new int[N];

			parent = new int[N];
			pq = new PriorityQueue<>();

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
				parent[i] = -1;
				
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}

					long distX = Math.abs(landX[i] - landX[j]);
					long distY = Math.abs(landY[i] - landY[j]);

					// 연결된 섬의 번호와 거리 저장
					pq.add(new Edge(i, j, distX * distX + distY * distY));
				}
			}

			long answer = kruskal();

			System.out.println("#" + t + " " + Math.round(E * answer));
		}
	}
	
	// 크루스칼 사용
	private static long kruskal() {
		long result = 0;
		int cnt = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (union(cur.s, cur.e)) {
				result += cur.w;
				
				if (++cnt == N - 1) {
					break;
				}
			}
		}
		
		return result;
	}

	private static int find(int x) {
		if (parent[x] < 0) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	private static boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX == rootY) {
			return false;
		}

		if (parent[rootX] <= parent[rootY]) {
			parent[rootX] += parent[rootY];
			parent[rootY] = rootX;
		} else {
			parent[rootY] += parent[rootX];
			parent[rootX] = rootY;
		}

		return true;
	}
}