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
         
        int[] CC = new int[N];
         
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != INF || map[j][i] != INF) {
                    CC[i] += map[i][j];
                }
            }
        }
         
        Arrays.sort(CC);
         
        return CC[0]; // 최솟값 반환
    }
}