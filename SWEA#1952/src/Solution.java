/**
 * 1952. 수영장
 * 
 * @author minchae
 * @date 2024. 8. 22.
 */

import java.util.*;
import java.io.*;
 
public class Solution {
     
    static int[] price;
    static int[] use;
     
    static int min;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {           
            price = new int[4];
            use = new int[13];
             
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            for (int i = 0; i < 4; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }
             
            st = new StringTokenizer(br.readLine());
             
            for (int i = 1; i <= 12; i++) {
                use[i] = Integer.parseInt(st.nextToken());
            }
             
            min = price[3]; // 초기 최소 금액은 1년 이용권의 금액과 같음
             
            solve(1, 0);
             
            System.out.println("#" + t + " " + min);
        }
    }
 
    private static void solve(int depth, int sum) {
        // 현재 최소값보다 큰 값이 들어올 경우 바로 종료
        if (sum > min) {
            return;
        }
         
        // 12월까지 진행
        if (depth > 12) {
            min = Math.min(min, sum);
            return;
        }
         
        solve(depth + 1, sum + price[0] * use[depth]); // 1일 사용권
        solve(depth + 1, sum + price[1]); // 1달 사용권
        solve(depth + 3, sum + price[2]); // 3달 사용권
    }
     
}