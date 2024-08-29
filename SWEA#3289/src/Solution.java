/**
 * D4 3289. 서로소 집합
 * 101,456 kb  428 ms
 * 
 * @author minchae
 * @date 2024. 8. 28.
 * */

import java.util.*;
import java.io.*;

public class Solution {

	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parent = new int[n + 1];
			
			// 부모 배열 초기화
			for (int i = 1; i <= n; i++) {
				parent[i] = -1;
			}
			
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (cmd == 0) {
					union(a, b);
				} else {
					sb.append(find(a) == find(b) ? 1 : 0);
				}
			}
			
			System.out.println("#" + t + " " + sb.toString());
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
		
		if (parent[rootX] <= parent[rootY]) { // 크기가 더 큰 트리를 부모로 설정
			parent[rootX] += parent[rootY];
			parent[rootY] = rootX;
		} else {
			parent[rootY] += parent[rootX];
			parent[rootX] = rootY;
		}
		
		return true;
	}

}
