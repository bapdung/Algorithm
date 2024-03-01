import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static int R;
	public static int C;
	public static Position hole;
	public static int time;
	public static int[][] arr;
	public static int[][][] ways = { { {} }, 
			{ { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } },//상하좌우
			{ { -1, 0 }, { 1, 0 }, { 0, 0 }, { 0, 0 } }, //상하ㅇㅇ
			{ { 0, 0 }, { 0, 0 }, { 0, -1 }, { 0, 1 } }, //ㅇㅇ좌우
			{ { -1, 0 }, { 0, 0 }, { 0, 0 }, { 0, 1 } }, //상ㅇㅇ우
			{ { 0, 0 }, { 1, 0 }, { 0, 0 }, { 0, 1 } }, //ㅇ하ㅇ우
			{ { 0, 0 }, { 1, 0 }, { 0, -1 }, { 0, 0 } }, //ㅇ하좌ㅇ
			{ { -1, 0 }, { 0, 0 }, { 0, -1 }, { 0, 0 } } }; //상ㅇ좌ㅇ

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arr = new int[R][C];
			int hr = Integer.parseInt(st.nextToken());
			int hc = Integer.parseInt(st.nextToken());
			hole = new Position(hr, hc);
			time = Integer.parseInt(st.nextToken());
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
//			for (int r = 0; r < R; r++) {
//				for (int c = 0; c < C; c++) {
//					System.out.print(arr[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			sb.append("#" + tc + " " + bfs() + "\n");
		}
		System.out.println(sb);
	}

	public static int bfs() {
		Queue<Position> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[R][C];
		q.offer(hole);
		visit[hole.r][hole.c] = true;
		int cnt = 0;
		int t = 0;
		while (!q.isEmpty()) {
			if (t == time) {
				break;
			}
			int size = q.size();
			while (size-- > 0) {
				Position cur = q.poll();
				cnt++;
				int rule = arr[cur.r][cur.c];
				for (int i = 0; i < 4; i++) {
					int cr = cur.r + ways[rule][i][0];
					int cc = cur.c + ways[rule][i][1];
					if (cr >= 0 && cr < R && cc >= 0 && cc < C && !visit[cr][cc] && arr[cr][cc] != 0) {
						if(check(i, cr, cc)) {
							visit[cr][cc] = true;
							q.offer(new Position(cr, cc));
						}
					}
				}
			}
			t++;
		}
		return cnt;
	}
	public static boolean check(int i, int cr, int cc) {
		switch (i) {
		case 0: //상
			if(arr[cr][cc] == 1 || arr[cr][cc] == 2 || arr[cr][cc] == 5 || arr[cr][cc]==6) {
				return true;
			}
			else return false;
		case 1://하
			if(arr[cr][cc] == 1 || arr[cr][cc] == 2 || arr[cr][cc] == 4 || arr[cr][cc]==7) {
				return true;
			}
			else return false;
		case 2://좌
			if(arr[cr][cc] == 1 || arr[cr][cc] == 3 || arr[cr][cc] == 4 || arr[cr][cc]==5) {
				return true;
			}
			else return false;
		case 3://우
			if(arr[cr][cc] == 1 || arr[cr][cc] == 3 || arr[cr][cc] == 6 || arr[cr][cc]==7) {
				return true;
			}
			else return false;
		}
		return false;
	}

	public static class Position {
		int r;
		int c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
