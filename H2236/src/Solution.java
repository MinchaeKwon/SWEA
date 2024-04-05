import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private final static int CMD_INIT = 100;
	private final static int CMD_ADD_SEQ = 200;
	private final static int CMD_SEARCH_SEQ = 300;
	private final static int CMD_ERASE_SEQ = 400;
	private final static int CMD_CHANGE_BASE = 500;

	private final static int MAXL = 61;

	private final static UserSolution usersolution = new UserSolution();

	private static void String2Char(String s, char[] b) {
		int n = s.length();
		for (int i = 0; i < n; ++i)
			b[i] = s.charAt(i);
		for (int i = n; i < b.length; ++i)
			b[i] = '\0';
	}

	private static boolean run(BufferedReader br) throws Exception {
		int Q;

		int mID, mLen, mPos;
		char[] mSeq = new char[MAXL];
		char[] mBegin = new char[MAXL];
		char[] mBase = new char[2];

		int ret = -1, ans;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Q = Integer.parseInt(st.nextToken());

		boolean okay = false;

		for (int q = 0; q <= Q; ++q) {
			int cmd;

			st = new StringTokenizer(br.readLine(), " ");

			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				usersolution.init();
				okay = true;
				break;
			case CMD_ADD_SEQ:
				mID = Integer.parseInt(st.nextToken());
				mLen = Integer.parseInt(st.nextToken());
				String2Char(st.nextToken(), mSeq);
				if (okay)
					ret = usersolution.addSeq(mID, mLen, mSeq);
				ans = Integer.parseInt(st.nextToken());
				if (ans != ret)
					okay = false;
				break;
			case CMD_SEARCH_SEQ:
				mLen = Integer.parseInt(st.nextToken());
				String2Char(st.nextToken(), mBegin);
				if (okay)
					ret = usersolution.searchSeq(mLen, mBegin);
				ans = Integer.parseInt(st.nextToken());
				if (ans != ret)
					okay = false;
				break;
			case CMD_ERASE_SEQ:
				mID = Integer.parseInt(st.nextToken());
				if (okay)
					ret = usersolution.eraseSeq(mID);
				ans = Integer.parseInt(st.nextToken());
				if (ans != ret)
					okay = false;
				break;
			case CMD_CHANGE_BASE:
				mID = Integer.parseInt(st.nextToken());
				mPos = Integer.parseInt(st.nextToken());
				String2Char(st.nextToken(), mBase);
				if (okay)
					ret = usersolution.changeBase(mID, mPos, mBase[0]);
				ans = Integer.parseInt(st.nextToken());
				if (ans != ret)
					okay = false;
				break;
			default:
				okay = false;
				break;
			}
		}

		return okay;
	}

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		// System.setIn(new java.io.FileInputStream("/Users/minchae/Documents/GitHub/SWEA/res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;

			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}
