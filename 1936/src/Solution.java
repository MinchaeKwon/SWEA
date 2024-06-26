/**
 * 1936. 1대1 가위바위보
 * 
 * @author Minchae
 * @date 2024. 6. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		if ((A < 3 && A < B) || (A == 3 && B == 1)) {
			System.out.println("B");
		} else {
			System.out.println("A");
		}
	}

}
