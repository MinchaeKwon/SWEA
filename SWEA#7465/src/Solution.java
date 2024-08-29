/**
 * D4 7465. 창용 마을 무리의 개수
 * 26,420 kb  162 ms
 * 
 * @author minchae
 * @date 2024. 8. 28.
 * */

import java.util.*;
import java.io.*;

public class Solution {

	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N + 1];
			
			// 부모 배열 초기화
			for (int i = 1; i <= N; i++) {
				parent[i] = -1;
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			int cnt = 0;
			
			for (int i = 1; i <= N; i++) {
				// 음수를 가진 노드가 한 무리의 루트 노드
				if (parent[i] < 0) {
					cnt++;
				}
			}
			
			System.out.println("#" + t + " " + cnt);
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
