/**
 * 12712. 파리퇴치3
 * 
 * @author Minchae
 * @date 2024. 6. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
	
	static int N, M;
	static int[][] map;
	
	static ArrayList<Integer> candidate;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			candidate = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			catchFly();
			
			Collections.sort(candidate);
			
			System.out.println("#" + t + " " + candidate.get(candidate.size() - 1));
		}

	}
	
	private static void catchFly() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				find(0, i, j);
				find(4, i, j);
			}
		}
	}
	
	private static void find(int start, int x, int y) {
		int sum = map[x][y];
		
		for (int d = start; d < start + 4; d++) {
			int nx = x;
			int ny = y;
			
			for (int m = 1; m < M; m++) {
				nx += dx[d];
				ny += dy[d];
				
				if (!isRange(nx, ny)) {
					break;
				}
				
				sum += map[nx][ny];
			}
		}
		
//		for (int m = 1; m < M; m++) {
//			for (int d = start; d < start + 4; d++) {
//				int nx = x + dx[d] * m;
//				int ny = y + dy[d] * m;
//				
//				if (!isRange(nx, ny)) {
//					break;
//				}
//				
//				sum += map[nx][ny];
//			}
//		}

		candidate.add(sum);
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
