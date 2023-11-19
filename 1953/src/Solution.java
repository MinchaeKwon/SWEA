/**
 * 1953. [모의 SW 역량테스트] 탈주범 검거
 * 
 * @author Minchae
 * @date 2023. 11. 19
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	
	// 상우하좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// 터널 종류별로 4방향에 대해 뚫려 있는지 여부
	static final int[][] type = {	
			{0, 0, 0, 0},
			{1, 1, 1, 1}, // 모든 방향 가능
			{1, 0, 1, 0},	
			{0, 1, 0, 1},	
			{1, 1, 0, 0},	
			{0, 1, 1, 0},	
			{0, 0, 1, 1},	
			{1, 0, 0, 1},	
	};
	
	static int N, M, R, C, L;
	static int[][] map; // 숫자 1 ~ 7은 해당 위치의 터널 구조물 타입, 숫자 0 은 터널이 없는 장소

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 세로 위치
			C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 가로 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#" + t + " " + bfs());
		}

	}
	
	private static int bfs() {
		int answer = 1; // 탈주범이 위치할 수 있는 장소의 개수
		int time = 1;

		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Pos(R, C));
		visited[R][C] = true;
		
		while (!q.isEmpty()) {
			// 시간이 다 지나면 탐색 중지
			if (time == L) {
				break;
			}
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
				
				// 터널별로 갈 수 있는 방향이 전부 다름
				int[] dirs = type[map[cur.x][cur.y]];
				
				for (int dir = 0; dir < dirs.length; dir++) {
					// 갈 수 없는 경우에는 다음으로 넘어감
					if (dirs[dir] == 0) {
						continue;
					}
					
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];
					
					// 범위를 벗어나거나 다음 칸에 터널이 아예 없거나 이미 방문한 경우
					if (!isRange(nx, ny) || map[nx][ny] == 0 || visited[nx][ny]) {
						continue;
					}
					
					// 다음 칸에서 터널이 이어지는 경우에만 진행
					if (type[map[nx][ny]][(dir + 2) % 4] == 1) {
						visited[nx][ny] = true;
						q.add(new Pos(nx, ny));
						
						answer += 1;
					}
				}
			}
			
			time += 1;
		}
		
		return answer;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
