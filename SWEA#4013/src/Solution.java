/**
 * 4013. 특이한 자석
 * 
 * @author minchae
 * @date 2024. 10. 2.
 */

import java.io.*;
import java.util.*;
 
public class Solution {
     
    static int K;
    static int[][] map;
    static int[] rotateDir;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            K = Integer.parseInt(br.readLine());
             
            map = new int[4][8]; // 톱니바퀴 4개의 번호 저장, N극 0, S극  1
             
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                 
                for (int j = 0; j < 8; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                 
                int num = Integer.parseInt(st.nextToken()) - 1; // 회전시킬 톱니바퀴 번호
                int dir = Integer.parseInt(st.nextToken()); // 1 시계방향, -1 반시계방향
                 
                rotateDir = new int[4];
                 
                move(num, dir);
                rotate();
            }
             
            int answer = 0;
             
            for (int i = 0; i < 4; i++) {
                answer += map[i][0] == 0 ? 0 : Math.pow(2, i);
            }
             
            System.out.println("#" + t + " " + answer);
        }
    }
 
    private static void move(int num, int dir) {
        rotateDir[num] = dir;
         
        int prev = num - 1;
        int next = num + 1;
         
        // 범위 벗어나지 않고, 방향이 정해지지 않고, 서로 다른 극인 경우
        if (prev >= 0 && rotateDir[prev] == 0 && map[num][6] != map[prev][2]) {
            move(prev, dir * -1); // 반대 방향으로 회전
        }
         
        if (next <= 3 && rotateDir[next] == 0 && map[num][2] != map[next][6]) {
            move(next, dir * -1);
        }
    }
     
    // 저장된 방향으로 회전
    private static void rotate() {
        for (int i = 0; i < 4; i++) {
            if (rotateDir[i] == 0) {
                continue;
            }
             
            int[] tmp = new int[8];
             
            for (int j = 0; j < 8; j++) {
                int idx = j + rotateDir[i];
                 
                if (idx == -1) {
                    idx = 7;
                } else if (idx == 8) {
                    idx = 0;
                }
                 
                tmp[idx] = map[i][j];
            }
             
            map[i] = tmp;
        }
    }
}
