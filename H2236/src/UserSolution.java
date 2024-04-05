/**
 * DNA Sequence
 * 
 * @author Minchae
 * @date 2024. 4. 5.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class UserSolution {
	
	HashMap<Integer, String> idMap; // 식별 번호에 해당하는 염기서열 저장
	HashMap<String, Integer> seqMap; // 염기서열에 해당하는 식별 번호 저장
	HashMap<String, ArrayList<Integer>> prefixMap; // prefix에 해당 하는 식별 번호 리스트 저장
	
	void init() {
		idMap = new HashMap<>();
		seqMap = new HashMap<>();
		prefixMap = new HashMap<>();
	}
	
	void print() {
		for (int id : idMap.keySet()) {
			System.out.println(id + " - " + idMap.get(id));
		}
	}

	int addSeq(int mID, int mLen, char[] mSeq) {
		String seq = String.valueOf(mSeq).trim();
		
		if (idMap.containsKey(mID)) {
			return 0;
		}
		
		if (seqMap.containsKey(seq)) {
			return 0;
		}
		
		idMap.put(mID, seq);
		seqMap.put(seq, mID);
		
		int size = 3;
		
		while (size <= mLen) {
			String prefix = seq.substring(0, size);
			
			if (prefixMap.containsKey(prefix)) {
				prefixMap.get(prefix).add(mID);
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(mID);
				prefixMap.put(prefix, list);
			}
			
			size += 3;
		}
		
//		System.out.println("\n prefix 출력");
//		for (String prefix : prefixMap.keySet()) {
//			System.out.println(prefix + " " + prefixMap.get(prefix)+ " ");
//		}
		
//		System.out.println("\n추가");
//		print();
		
		return 1;
	}

	int searchSeq(int mLen, char[] mBase) {
		String compare = String.valueOf(mBase).trim();
		
		if (prefixMap.containsKey(compare)) {
			int cnt = prefixMap.get(compare).size();
			
			if (cnt == 0) {
				return -1;
			} else {
				return cnt == 1 ? prefixMap.get(compare).get(0) : cnt;	
			}
		} else {
			return -1;
		}
	}

	int eraseSeq(int mID) {
		// mID인 염기서열이 없는 경우 0 반환
		if (!idMap.containsKey(mID)) {
			return 0;
		}
		
		String seq = idMap.get(mID);
		
		idMap.remove(mID);
		seqMap.remove(seq);
		
		int size = 3;
		
		while (size <= seq.length()) {
			String prefix = seq.substring(0, size);
			
			prefixMap.get(prefix).remove(Integer.valueOf(mID));
			
			size += 3;
		}
		
//		System.out.println("\n삭제");
//		print();
		
		return 1;
	}

	int changeBase(int mID, int mLen, char mBase) {
		// 1. mID인 염기서열이 없는 경우 0 반환
		if (!idMap.containsKey(mID)) {
			return 0;
		}
		
		String seq = idMap.get(mID); // mID에 해당하는 염기서열
		
		// 2. 염기서열의 길이가 mLen보다 같거나 작은 경우 바꿀 수 없음
		if (seq.length() <= mLen) {
			return 0;
		}
		
		// 3. mLen번째 문자가 mBase인 경우 바꿀 수 없음
		if (seq.charAt(mLen) == mBase) {
			return 0;
		}
		
		char[] arr = seq.toCharArray();
		arr[mLen] = mBase;
		
		String change = String.valueOf(arr);
		
		// 4. mLen번째를 mBase로 변경했을 때 이미 존재하는 염기서열인 경우 바꿀 수 없음
		if (seqMap.containsKey(change)) {
			return 0;
		}
		
		eraseSeq(mID); // 원래 있던 염기서열 삭제
		addSeq(mID, change.length(), change.toCharArray()); // 변경된 염기서열 삽입
		
//		System.out.println("\n변경");
//		print();
		
		return 1;
	}
}
