import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] arr;
	public static int size = 2;
	public static Position shark;
	public static List<Position> fish = new ArrayList<>();
	public static int[][] dist;
	public static int[] dr = { -1, 0, 0, 1 };
	public static int[] dc = { 0, -1, 1, 0 };
	public static int cnt = 0;
	public static int total = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int n = Integer.parseInt(st.nextToken());
				arr[r][c] = n;
				if (arr[r][c] == 9) {
					shark = new Position(r, c);
					arr[r][c] = 0;
				}
			}
		}
		//먹을 수 있는 물고기 있을때까지 반복
		do {
		} while (bfs());
		
		//경과시간
		System.out.println(total);
	}

	public static boolean bfs() {
		Queue<Position> q = new ArrayDeque<>();
		PriorityQueue<Position> pq = new PriorityQueue<>();
		q.offer(new Position(shark.r, shark.c));
		boolean[][] visit = new boolean[N][N];
		visit[shark.r][shark.c] = true;
		int temp = 1; // 경과시간(거리)
		while (!q.isEmpty()) {
			int qSize = q.size();
			while (qSize-- > 0) {
				Position cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int cr = cur.r + dr[d];
					int cc = cur.c + dc[d];
					if (cr >= 0 && cr < N && cc >= 0 && cc < N && !visit[cr][cc] && arr[cr][cc] <= size) {
						if (arr[cr][cc] == 0 || arr[cr][cc] == size) { //빈칸이거나 같은 사이즈일때
							visit[cr][cc] = true;
							q.offer(new Position(cr, cc));
						} else { //먹을 수 있는 물고기 있을 때
							pq.offer(new Position(cr, cc));
						}
					}
				}
			}
			//먹을 수 있는 물고기 생겼을때
			if (!pq.isEmpty()) {
				Position p = pq.poll();
				if (++cnt == size) {//사이즈 n일때 먹은 물고기 카운트n이면 카운트 리셋 후 사이즈업
					cnt = 0;
					size++;
				}
				arr[p.r][p.c] = 0;
				total += temp;
				shark.r = p.r;
				shark.c = p.c;
				return true;
			}
			temp++;
		}
		return false;
	}

	public static class Position implements Comparable<Position> {
		int r;
		int c;
		int d;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Position(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Position o) {
			if (this.r == o.r) {
				return this.c - o.c;
			}
			return this.r - o.r;
		}

	}
}