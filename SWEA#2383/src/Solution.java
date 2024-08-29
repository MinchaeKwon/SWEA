
/**
 * 2383. 점심 식사시간
 * 24,204 kb  168 ms
 * 
 * @author minchae
 * @date 2024. 8. 30.
 * */

import java.io.*;
import java.util.*;

public class Solution {

	static class Person {
		int x;
		int y;
		int stair; // 선택한 계단 번호
		int dist; // 계단까지의 거리
		int arrive; // 계단 도착 시간

		public Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Stair {
		int x;
		int y;
		int value; // 계단 깊이
		Queue<Person> sq = new LinkedList<>(); // 계단에 있는 사람을 저장할 큐
		
		public Stair(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	static int N;
	static int[][] map;

	static ArrayList<Person> persons;
	static Stair[] stairs;
	
	static boolean[] isIn;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			
			persons = new ArrayList<>();
			stairs = new Stair[2];
			
			answer = Integer.MAX_VALUE;

			int idx = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1) {
						persons.add(new Person(i, j));
					} else if (map[i][j] > 1) {
						stairs[idx++] = new Stair(i, j, map[i][j]);
					}
				}
			}
			
			select(0);

			System.out.println("#" + t + " " + answer);
		}

	}

	private static void select(int depth) {
		if (depth == persons.size()) {
			answer = Math.min(answer, move());
			
			return;
		}
		
		// 계단 선택
		for (int i = 0; i < 2; i++) {
			persons.get(depth).stair = i;
			persons.get(depth).dist = getDist(persons.get(depth), stairs[i]);
			
			select(depth + 1);
		}
	}
	
	private static int move() {
		int time = 0;
		isIn = new boolean[persons.size()]; // 사람이 계단에 들어갔는지 확인
		
		while (true) {
			// 계단 먼저 내려감
			for (Stair s : stairs) {
				int size = s.sq.size();
				
				for (int i = 0; i < size; i++) {
					Person cur = s.sq.poll();
					
					// 계단을 다 내려간 경우 넘어감
					if (cur.arrive + s.value <= time) {
						continue;
					}
					
					// 아직 다 내려가지 못한 경우 다시 큐에 삽입
					s.sq.add(cur);
				}
			}
			
			// 모든 사람이 계단을 내려간 경우 시간 반환
			if (isFinish()) {
				return time;
			}
			
			// 계단에 도착
			for (int i = 0; i < persons.size(); i++) {
				// 이미 계단에 있는 경우 넘어감
				if (isIn[i]) {
					continue;
				}
				
				Person cur = persons.get(i);
				Queue<Person> sq = stairs[cur.stair].sq;
				
				// 계단에 들어갈 수 있으면 큐에 추가
				if (sq.size() < 3 && cur.dist + 1 <= time) {
					sq.add(cur);
					
					cur.arrive = time; // 계단 도착 시간 넣기
					isIn[i] = true;
				}
			}
			
			time++; // 시간 증가
		}
	}
	
	// 사람과 계단 사이의 거리
	private static int getDist(Person p, Stair s) {
		return Math.abs(p.x - s.x) + Math.abs(p.y - s.y);
	}

	// 계단에 있는지 확인
	private static boolean isFinish() {
		for (int i = 0; i < isIn.length; i++) {
			if (!isIn[i]) {
				return false;
			}
		}
		
		return stairs[0].sq.isEmpty() && stairs[1].sq.isEmpty();
	}
}
