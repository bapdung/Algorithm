import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int R;
	public static int C;
	public static int D;
	public static int[][] arr;
	public static int max;
	public static int[][] boy = new int[3][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[R + 1][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		조합(0, 0);
		System.out.println(max);
	}

	public static void 조합(int start, int cnt) {
		if (cnt == 3) {
			공격();
			return;
		}
		for (int i = start; i < C; i++) {
			arr[R][i] = 1;
			boy[cnt][0] = R;
			boy[cnt][1] = i;
			조합(i + 1, cnt + 1);
			arr[R][i] = 0;
		}
	}

	public static void 공격() {
		int kill = 0;
		boolean[][] visit = new boolean[R][C];
		for (int temp = 0; temp < R; temp++) {
			PriorityQueue<Distance>[] pq = new PriorityQueue[3];
			for(int i=0; i<3; i++) {
				pq[i] = new PriorityQueue<Distance>();
			}
			for (int b = 0; b < 3; b++) { //궁수 세명
				for (int r = R - 1 - temp; r >= 0; r--) {
					for (int c = 0; c < C; c++) {
						if (arr[r][c] == 1 && !visit[r][c]) {
							int dist = distance(boy[b][0], boy[b][1], r, c) - temp;
							if (dist <= D) {
								pq[b].add(new Distance(r,c,dist));
							}
						}
					}
				}
			}
			for(int i=0; i<3; i++) {
				if(!pq[i].isEmpty()) {
					int cr = pq[i].peek().r;
					int cc = pq[i].peek().c;
					if(!visit[cr][cc] && arr[cr][cc] == 1) {
						visit[cr][cc] = true;
						kill++;
					}
				}
			}
		}
		max = Math.max(max, kill);

	}

	public static int distance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

	public static class Distance implements Comparable<Distance>{
		int r;
		int c;
		int dist;

		public Distance(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		public void setR(int r) {
			this.r = r;
		}

		public void setDist(int dist) {
			this.dist = dist;
		}
		
		@Override
		public String toString() {
			return "Distance [r=" + r + ", c=" + c + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(Distance d) {
			if(this.dist == d.dist) {
				return this.c - d.c;
			}
			return this.dist - d.dist;
		}

	}
}