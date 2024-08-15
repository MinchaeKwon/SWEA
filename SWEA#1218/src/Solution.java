/**
 * D4 1218. 괄호 짝짓기
 * 19,388 kb  128 ms
 * 
 * @author minchae
 * @date 2024. 8. 14.
 */

import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Character, Character> hm = new HashMap<>();
		hm.put('(', ')');
		hm.put('[', ']');
		hm.put('{', '}');
		hm.put('<', '>');

		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			char[] input = br.readLine().toCharArray();
			
			Stack<Character> stack = new Stack<>();
			boolean valid = true;
			
			for (char c : input) {
				if (hm.containsKey(c)) { // 열린 괄호인 경우
					stack.push(c);
				} else { // 닫힌 괄호인 경우
					// 스택에서 꺼낸 것이 짝이 맞지 않는 경우 올바른 괄호가 아님
					// 올바른 괄호라면 스택 마지막에 저장된 괄호는 짝이 맞는 괄호
					if (c != hm.get(stack.pop())) {
						valid = false;
						break;
					}
				}
			}
			
			System.out.println("#" + t + " " + (valid ? 1 : 0));
		}

	}

}
