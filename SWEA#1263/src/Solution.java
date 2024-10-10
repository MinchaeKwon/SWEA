/**
 * 1263. 사람 네트워크2
 * 
 * @author minchae
 * @date 2024. 10. 10.
 * 
 * 실행 시간
 * 1,296 ms
 */

import java.io.*;
import java.util.*;
 
public class Solution {
     
    static final int INF = 987654321;
     
    static int N;
    static int[][] map;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
         
            N = Integer.parseInt(st.nextToken());
             
            map = new int[N][N];
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                     
                    if (i == j) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = num == 1 ? num : INF;
                    }
                }
            }
             
            int answer = floyd();
         
            System.out.println("#" + t + " " + answer);
        }
    }
 
    private static int floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for  (int j = 0; j < N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
         
        int result = Integer.MAX_VALUE;
         
        for (int i = 0; i < N; i++) {
        	int sum = 0;
        	
            for (int j = 0; j < N; j++) {
                if (map[i][j] != INF || map[j][i] != INF) {
                    sum += map[i][j];
                }
            }
            
            result = Math.min(result, sum);
        }
         
        return result; // 최솟값 반환
    }
}