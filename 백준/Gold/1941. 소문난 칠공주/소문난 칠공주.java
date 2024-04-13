import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static int[][] arr;
	public static int[] choosed = new int[7];
	public static boolean[][] check = new boolean[5][5];
	public static Position startP;
	public static int[] dr = {1,-1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static int rst = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[5][5];
		for(int r=0; r<5; r++) {
			String str = br.readLine();
			for(int c=0; c<5; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		조합(0,0);
		System.out.println(rst);
		
	}
	public static void 조합(int cnt, int start) {
		if(cnt==7) {
			bfs();
			return;
		}
		for(int i=start; i<25; i++) {
			if(cnt == 0) {
				startP = new Position(i/5,i%5);
			}
			check[i/5][i%5]=true;
			조합(cnt+1, i+1);
			check[i/5][i%5]=false;
		}
	}
	public static void bfs() {
		boolean[][] bfsCheck = new boolean[5][5];
		int cnt = 0;
		int 이다솜파 = 0;
		Queue<Position> q = new ArrayDeque<>();
		q.offer(startP);
		bfsCheck[startP.r][startP.c] = true;
		while(!q.isEmpty()) {
			Position cur = q.poll();
			if(arr[cur.r][cur.c] == 'S') 이다솜파++;
			cnt++;
			for(int d=0; d<4; d++) {
				int cr = cur.r + dr[d];
				int cc = cur.c + dc[d];
				if(cr>=0 && cr<5 && cc>=0 && cc<5 && check[cr][cc] && !bfsCheck[cr][cc]) {
					bfsCheck[cr][cc] = true;
					q.offer(new Position(cr,cc));
				}
			}
		}
		if(cnt==7 &&이다솜파>=4) rst++;
	}
	public static class Position{
		int r;
		int c;
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}