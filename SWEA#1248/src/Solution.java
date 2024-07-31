/**
 * D5 1248 [S/W 문제해결 응용] 3일차 - 공통조상
 * 
 * @author minchae
 * @date 2024. 7. 30.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static class Node {
		int num;
		int parent = 0;
		int left = 0;
		int right = 0;
		
		public Node(int num) {
			this.num = num;
		}
	}

	static int V, E;
	
	static Node[] nodes;
	static boolean[] visited;
	
	static int common; // 공통 조상 저장
	static int answer; // 공통 조상의 서브 트리 개수 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			nodes = new Node[V + 1];
			visited = new boolean[V + 1];
			
			common = 0;
			answer = 0;
			
			for (int i = 1; i <= V; i++) {
				nodes[i] = new Node(i);
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < E; i++) {
				int n1 = Integer.parseInt(st.nextToken()); // 부모
				int n2 = Integer.parseInt(st.nextToken()); // 자식
				
				if (nodes[n1].left == 0) { // 왼쪽에 노드가 없는 경우
					nodes[n1].left = n2; // 왼쪽 자식 노드로 추가
				} else if (nodes[n1].right == 0) { // 오른쪽에 노드가 없는 경우
					nodes[n1].right = n2; // 오른쪽 자식 노드로 추가
				}
				
				// 부모 노드 저장
				nodes[n2].parent = n1;
			}
			
			// 두 노드의 가장 가까운 공통 조상 찾기
			findParent(v1);
			findParent(v2);
			
			// 공통 조상의 서브 트리 개수 구하기
			getCount(common);
			
			System.out.println("#" + t + " " + common + " " + answer);
		}
	}
	
	// 노드의 조상 찾기
	private static void findParent(int v) {
		int parent = nodes[v].parent;
		
		// 루트 노드여서 부모가 없거나 이미 가까운 공통 조상을 발견한 경우 종료
		if (parent == 0 || common != 0) {
			return;
		}
		
		// 이미 방문했던 노드인 경우 다른 노드와의 공통 조상이라는 뜻
		if (visited[parent]) {
			common = parent;
		}
		
		visited[parent] = true; // 방문 처리
		
		findParent(parent); // 부모로 거슬러 올라감
	}
	
	// 특정 노드의 서브 트리 개수 구하기
	private static void getCount(int v) {
		answer++;
		
		int left = nodes[v].left;
		int right = nodes[v].right;
		
		if (left != 0 && right != 0) {
			// 양쪽에 자식 노드가 있는 경우
			getCount(left);
			getCount(right);
		} else if (left != 0) {
			// 왼쪽에만 있는 경우
			getCount(left);
		} else if (right != 0) {
			// 오른쪽에만 있는 경우
			getCount(right);
		}
	}
	
}
