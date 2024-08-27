/**
 * 2382. 미생물 격리
 * 50,712 kb  359 ms
 * 
 * @author minchae
 * @date 2024. 8. 27.
 * */

import java.util.*;
import java.io.*;

public class Solution {

	static class Node implements Comparable<Node> {
		int x;
		int y;
		long cnt;
		int dir;
		
		public Node(int x, int y, long cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

		// 미생물 개수를 기준으로 내림차순 정렬
		@Override
		public int compareTo(Node o) {
			return Long.compare(o.cnt, this.cnt);
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M, K;
	
	static Node[][] map;
	
	static PriorityQueue<Node> pq;
	static PriorityQueue<Node> move;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new Node[N][N];
			pq = new PriorityQueue<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				map[x][y] = new Node(x, y, cnt, dir);
				pq.add(map[x][y]);
			}
			
			// M시간동안 진행
			while (M-- > 0) {
				move();
			}
			
			int answer = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == null) {
						continue;
					}
					
					answer += map[i][j].cnt;
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	// 모든 군집 이동
	private static void move() {
		move = new PriorityQueue<>();
		
		// 미생물 개수가 많은 군집부터 진행됨
		while (!pq.isEmpty()) {
			moveMicro(pq.poll());
		}
		
		add(move);
	}
	
	// 군집 한 개 이동
	private static void moveMicro(Node cur) {
		map[cur.x][cur.y] = null; // 이동시키므로 맵을 비워줌
		
		cur.x += dx[cur.dir];
		cur.y += dy[cur.dir];
		
		// 약품이 칠해진 구역으로 이동한 경우 이동 방향 바꾸고 미생물 반으로 줄임
		if (!isRange(cur.x, cur.y)) {
			cur.dir += (cur.dir % 2 == 0) ? 1 : -1;
			cur.cnt /= 2;
		}
		
		move.add(cur);
	}
	
	// 이동 후의 군집 맵에 추가
	private static void add(PriorityQueue<Node> move) {
		// 미생물이 많은 군집부터 진행
		while (!move.isEmpty()) {
			Node cur = move.poll();
			
			if (map[cur.x][cur.y] == null) {
				// 비어있던 경우에만 큐에 삽입 (방향에 미생물 개수가 가장 많은 군집의 방향이 저장되어야 하기 때문)
				// 미생물 개수가 많은 군집이 저장됨
				map[cur.x][cur.y] = cur;
				pq.add(cur);
			} else {
				// 군집이 이미 있던 경우에는 미생물 개수만 더해줌
				map[cur.x][cur.y].cnt += cur.cnt;
			}
		}
	}

	// 약품이 칠해진 구역인지 확인
	private static boolean isRange(int x, int y) {
		return x >= 1 && x < N - 1 && y >= 1 && y < N - 1;
	}
	
}
