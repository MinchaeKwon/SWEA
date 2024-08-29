/**
 * D4 3124. 최소 스패닝 트리
 * 115,692 kb  1,958 ms
 * 
 * @author minchae
 * @date 2024. 8. 29.
 * */

import java.util.*;
import java.io.*;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;
		
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int V, E;
	static int[] parent;
	
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parent = new int[V + 1];
			pq = new PriorityQueue<>();
			
			for (int i = 1; i <= V; i++) {
				parent[i] = -1;
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				pq.add(new Edge(A, B, C));
			}
			
			long answer = 0;
			
			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				
				if (union(cur.s, cur.e)) {
					answer += cur.w;
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
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
