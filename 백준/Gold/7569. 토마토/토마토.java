import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int C;
	public static int R;
	public static int H;
	public static int[][][] box;
	public static boolean[][][] check;
	public static int[] dh = {0,0,0,0,1,-1};
	public static int[] dr = {0,0,1,-1,0,0};
	public static int[] dc = {1,-1,0,0,0,0};
	public static int day = -1;
//	public static List<Position> red = new ArrayList<>();
	public static Queue<Position> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[H][R][C]; //높이, 행렬
		check = new boolean[H][R][C];
		boolean can = true;
		int cnt = 0;
		for(int h=0; h<H; h++) {
			for(int r=0; r<R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<C; c++) {
					int cur = Integer.parseInt(st.nextToken());
					if(can && cur==0) can = false;
					if(cur == 0) cnt++;
					if(cur == 1) {
						q.offer(new Position(h,r,c));
						check[h][r][c] = true;
					}
					if(cur == -1) check[h][r][c] = true;
					box[h][r][c] = cur;
				}
			}
		}
		
		//다 익어있는 상태
		if(can) {
			System.out.println(0);
			System.exit(0);
		}
		
		int red = bfs();
		if(red == cnt) {
			System.out.println(day);
		} else {
			System.out.println(-1);
		}
		
		
	}
	public static int bfs() {
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			day++;
			while(size-->0) {
				Position cur = q.poll();
				for(int d=0; d<6; d++) {
					int ch = cur.h + dh[d];
					int cr = cur.r + dr[d];
					int cc = cur.c + dc[d];
					if(ch>=0 && ch<H && cr>=0 && cr<R && cc>=0 && cc<C && !check[ch][cr][cc]) {
						check[ch][cr][cc] = true;
						cnt++;
						q.offer(new Position(ch, cr, cc));
					}
				}
			}
		}
		return cnt;
	}
	
	public static class Position{
		int h;
		int r;
		int c;
		
		public Position(int h, int r, int c) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
		}	
	}
}