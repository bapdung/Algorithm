import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int K;
	public static int R;
	public static int C;
	public static int[][] board;
	public static int[] dr = { 0, 0, 1, -1 };
	public static int[] dc = { 1, -1, 0, 0 };
	public static int[] mr = { -1, -2, -2, -1, 1, 2, 2, 1 };
	public static int[] mc = { -2, -1, 1, 2, 2, 1, -1, -2 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		System.out.println(-1);
	}

	public static void bfs() {
		Queue<Monkey> q = new ArrayDeque<>();
		boolean[][][] visit = new boolean[R][C][K+1];
		q.offer(new Monkey(0, 0, K));
		visit[0][0][K] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
//			System.out.println(q);
			int size = q.size();
			while (size-- > 0) {
				Monkey m = q.poll();
				if (m.r == R - 1 && m.c == C - 1) {
					System.out.println(cnt);
					System.exit(0);
					break;
				}
//				visit[m.r][m.c][m.temp] = true;
				for (int d = 0; d < 4; d++) {
					int cr = m.r + dr[d];
					int cc = m.c + dc[d];
					if (cr >= 0 && cr < R && cc >= 0 && cc < C && !visit[cr][cc][m.temp] && board[cr][cc] != 1) {
						visit[cr][cc][m.temp] = true;
						q.offer(new Monkey(cr, cc, m.temp));
					}
				}
				if (m.temp > 0) {
					for (int d = 0; d < 8; d++) {
						int cr = m.r + mr[d];
						int cc = m.c + mc[d];
						if (cr >= 0 && cr < R && cc >= 0 && cc < C && !visit[cr][cc][m.temp-1]
								&& board[cr][cc] != 1) {
							visit[cr][cc][m.temp-1] = true;
							q.offer(new Monkey(cr, cc, m.temp - 1));
						}
					}
				}
			}
			cnt++;
		}
	}

	public static class Monkey {
		int r;
		int c;
		int temp;

		public Monkey(int r, int c, int temp) {
			super();
			this.r = r;
			this.c = c;
			this.temp = temp;
		}

		@Override
		public String toString() {
			return "Monkey [r=" + r + ", c=" + c + ", temp=" + temp + "]";
		}

	}
}