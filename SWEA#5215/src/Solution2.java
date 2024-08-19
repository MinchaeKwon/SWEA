/**
 * D3 5215. 햄버거 다이어트
 * Next Permutation
 * 
 * @author minchae
 * @date 2024. 8. 19.
 */

import java.util.*;
import java.io.*;

public class Solution2 {
	
	static int N, L; // 재료의 수, 제한 칼로리
	static int[] score, K; // 맛에 대한 점수, 칼로리
	
	static int[] mask;
	static int[] selected;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			score = new int[N];
			K = new int[N];
			
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				score[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int r = 1; r <= N; r++) {
				mask = new int[N];
				selected = new int[r]; // 선택된 재료 번호 저장
				
				// 뽑을 개수만큼 1 저장
				for (int i = N - 1; i >= N - r; i--) {
					mask[i] = 1;
				}
				
				do {
					// 선택된 재료 번호 저장
					int idx = 0;
					
					for (int i = 0; i < N; i++) {
						if (mask[i] == 1) {
							selected[idx++] = i;
						}
					}
					
					int cal = 0; // 칼로리 합
					int sum = 0; // 점수의 합
					
					// 선택된 재료의 칼로리와 점수 합 구하기
					for (int i = 0; i < selected.length; i++) {
						cal += K[selected[i]];
						sum += score[selected[i]];
					}
					
					// 제한 칼로리 이하인 경우 최댓값 갱신
					if (cal <= L) {
						answer = Math.max(answer, sum);
					}
				} while (np(mask));
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	private static boolean np(int[] arr) {
		int N = arr.length;
		
		int i = N - 1; // step1) 꼭대기 찾기
		
		// 맨 뒤에서부터 확인
		while (i > 0 && arr[i - 1] >= arr[i]) {
			--i;
		}
		
		// 맨 앞까지 갔는데 작은 수가 없는 경우 만들 수 있는 가장 큰 순열을 만든 것 -> 종료
		if (i == 0) {
			return false;
		}
		
		// step2) 꼭대기 앞 교환 위치에 교환할 값(i - 1위치 값보다 큰 값 중 가장 작은 값) 자리 차지
		int j = N - 1; // 맨 뒤부터 시작
		
		// p[i - 1]보다 큰 값을 만날 때까지 반복
		while (arr[i - 1] >= arr[j]) {
			--j;
		}
		
		swap(arr, i - 1, j); // step3) 두 위치의 수 교환
		
		// step4) 꼭대기부터 맨 뒤까지 오름차순의 형태로 만듦
		int k = N - 1;
		
		while (i < k) {
			swap(arr, i++, k--);
		}
	
		return true;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}