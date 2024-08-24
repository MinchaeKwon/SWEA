/**
 * 5644. 무선 충전
 * 
 * 
 * @author minchae
 * @date 2024. 8. 23.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Battery {
		Pair pos;
		int C; // 충전 범위
		int P; // 성능
		
		public Battery(Pair pos, int C, int P) {
			this.pos = pos;
			this.C = C;
			this.P = P;
		}
	}
	
	// 이동 X, 상우하좌
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	
	static int M, A;
	
	static int[] aDir;
	static int[] bDir;
	
	static Battery[] BC;
	
	static Pair aMove;
	static Pair bMove;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			aDir = new int[M + 1];
			bDir = new int[M + 1];
			BC = new Battery[A];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= M; i++) {
				aDir[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= M; i++) {
				bDir[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				
				BC[i] = new Battery(new Pair(x, y), C, P);
			}
			
			// 사용자 A, B 초기 위치
			aMove = new Pair(0, 0);
			bMove = new Pair(9, 9);
			
			int answer = 0;
			
			// 초기 위치도 포함해서 반복
			for (int i = 0; i <= M; i++) {
				answer += move(i);
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	// A와 B가 이동 방향으로 이동
	private static int move(int n) {
		aMove.x += dx[aDir[n]];
		aMove.y += dy[aDir[n]];
		
		bMove.x += dx[bDir[n]];
		bMove.y += dy[bDir[n]];
		
		int result = 0;
		
		for (int i = 0; i < A; i++) {
			int tmp = 0;
			
			for (int j = 0; j < A; j++) {
				int aCharge = getCharge(aMove, i);
				int bCharge = getCharge(bMove, j);
				
				// 1. 같은 곳을 선택한 경우 더 큰 값 선택
				// 		만약 사용자가 같은 칸에 있는데 같은 곳을 선택한 경우 균등하게 분배해서 충전하기 때문에 총 충전 양은 max로 구한 값과 똑같음
				// 2. 다른 곳을 선택한 경우 각각의 충전 양 더함
				tmp = i == j ? Math.max(aCharge, bCharge) : aCharge + bCharge;
				
				result = Math.max(result, tmp);
			}
		}
		
		return result;
	}
	
	// n번째 AP와의 거리 구하고 충전 양 계산
	private static int getCharge(Pair cur, int n) {
		int dist = Math.abs(BC[n].pos.x - cur.x) + Math.abs(BC[n].pos.y - cur.y);
		
		// 충전 범위 내에 있는 경우
		if (dist <= BC[n].C) {
			return BC[n].P;
		}
		
		return 0;
	}

}
