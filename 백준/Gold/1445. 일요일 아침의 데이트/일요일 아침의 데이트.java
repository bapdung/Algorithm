import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, bCnt;
	public static char[][] arr;
	public static boolean[][][][] check;
	public static boolean[][] checkBG;
	public static int[] dr = { 0, 0, 1, -1 };
	public static int[] dc = { 1, -1, 0, 0 };
	public static Position start;
	public static List<Position> garbages = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		checkBG = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				arr[r][c] = str.charAt(c);
				if (arr[r][c] == 'S') {
					start = new Position(r, c);
				} else if ( arr[r][c] == 'g') {
					garbages.add(new Position(r,c));
				}
			}
		}
		
		//옆길 체크
		for(Position p : garbages) {
			for(int d=0; d<4; d++) {
				int cr = p.r + dr[d];
				int cc = p.c + dc[d];
				if(cr>=0 && cr<N && cc>=0 && cc<M && arr[cr][cc] == '.') {
					arr[cr][cc] = 'b';
					bCnt++;
				}
			}
		}
		
		PriorityQueue<Position> gogo = new PriorityQueue<>();
		gogo.add(start);
		checkBG[start.r][start.c] = true;
		int resultg = 0;
		int resultb = 0;
		gg:while(!gogo.isEmpty()) {
			Position now = gogo.poll();
			for(int i = 0 ; i < 4 ; i++) {
				int row = now.r + dr[i];
				int col = now.c + dc[i];
				int g = now.gCnt;
				int b = now.bCnt;
				if(row<0 || col <0 || row>= N || col >= M)
					continue;
				if(checkBG[row][col])
					continue;
				if(arr[row][col] == 'F') {
					resultg = g;
					resultb = b;
					break gg;
				}
				if(arr[row][col] == 'g')
					g++;
				if(arr[row][col] == 'b')
					b++;
				checkBG[row][col] = true;
				gogo.offer(new Position(row, col, b, g));
			}
		}
		System.out.println(resultg +" " + resultb);
	}

	public static class Position implements Comparable<Position>{
		int r;
		int c;
		int bCnt = 0;
		int gCnt = 0;
		
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Position(int r, int c, int bCnt, int gCnt) {
			super();
			this.r = r;
			this.c = c;
			this.bCnt = bCnt;
			this.gCnt = gCnt;
		}

		@Override
		public int compareTo(Position o) {
			if(this.gCnt == o.gCnt) {
				return this.bCnt - o.bCnt;
			}
			return this.gCnt - o.gCnt;
		}
	}
}