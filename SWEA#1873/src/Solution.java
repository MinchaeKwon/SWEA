/**
 * D3 1873. 상호의 배틀 필드
 * 33,932 kb  188 ms
 * 
 * @author minchae
 * @date 2024. 8. 23.
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int H, W, N;
	static char[][] map;
	static char[] cmd;
	
	static int sx, sy, dir;
	
	static HashMap<Character, Integer> dirMap;
	static HashMap<Integer, Character> dirRevMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		initMap();
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				
				for (int j = 0; j < W; j++) {
					// 전차 시작점, 방향 저장
					if (dirMap.containsKey(map[i][j])) {
						sx = i;
						sy = j;
						dir = dirMap.get(map[i][j]);
					}
				}
			}
			
			N = Integer.parseInt(br.readLine());
			
			cmd = new char[N];
			cmd = br.readLine().toCharArray();
			
			play(); // 입력 처기
			
			System.out.print("#" + t + " ");
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}

	}
	
	// 게임 시작
	private static void play() {
		for (char c : cmd) {
			switch (c) {
			case 'U':
				move(0);
				break;
			case 'D':
				move(1);
				break;
			case 'L':
				move(2);
				break;
			case 'R':
				move(3);
				break;
			case 'S':
				shell();
				break;
			}
		}
	}
	
	// 방향 바꾸고 한 칸 이동
	private static void move(int moveDir) {
		dir = moveDir;
		
		int nx = sx + dx[dir];
		int ny = sy + dy[dir];
		
		// 범위 벗어나거나 평지가 아닌 경우 방향은 바꿔주고 종료
		if (!isRange(nx, ny) || map[nx][ny] != '.') {
			map[sx][sy] = dirRevMap.get(dir);
			return;
		}
		
		// 방향 바꾸고 다음 칸에 이동 표시
		map[sx][sy] = '.';
		map[nx][ny] = dirRevMap.get(dir);
		
		// 현재 위치 갱신
		sx = nx;
		sy = ny;
	}
	
	// 전차가 현재 바라보고 있는 방향으로 포탄 발사
	private static void shell() {
		int x = sx;
		int y = sy;
		
		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 맵을 벗어나거나 강철 벽인 경우 종료
			if (!isRange(nx, ny) || map[nx][ny] == '#') {
				return;
			}
			
			// 벽돌 벽인 경우 평지로 만들고 종료
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				return;
			}
			
			// 위치 갱신
			x = nx;
			y = ny;
		}
	}

	private static void initMap() {
		dirMap = new HashMap<>();
		dirMap.put('^', 0);
		dirMap.put('v', 1);
		dirMap.put('<', 2);
		dirMap.put('>', 3);
		
		dirRevMap = new HashMap<>();
		dirRevMap.put(0, '^');
		dirRevMap.put(1, 'v');
		dirRevMap.put(2, '<');
		dirRevMap.put(3, '>');
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}

}
