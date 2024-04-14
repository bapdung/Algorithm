import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static int[][] arr = new int[8][8];
	public static boolean[] checkRow = new boolean[8];
	public static int[] dr = { 1, -1, 0, 0, 1, 1, -1, -1 , 0};
	public static int[] dc = { 0, 0, -1, 1, 1, -1, 1, -1 , 0};
	public static boolean flag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int r = 0; r < 8; r++) {
			String str = br.readLine();
			for (int c = 0; c < 8; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		bfs();
		if(flag) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}

	}
	public static void bfs() {
		Queue<Position> q = new ArrayDeque<>();
		q.offer(new Position(7,0));
		boolean[][] check = new boolean[8][8];
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				Position cur = q.poll();
				for(int d=0; d<9; d++) {
					int cr = cur.r + dr[d];
					int cc = cur.c + dc[d];
					if(cnt<8 && cr>=-cnt && cr<8-cnt && cc>=0 && cc<8) {
						if(cr+cnt==0 && cc==7 && !(cnt==0 && arr[0][7]=='#')) {
							flag = true;
							break;
						}
						if(cr>=0) {
							if(arr[cr][cc]=='.'){
								if(cr>=1 && arr[cr-1][cc]=='.') {
									q.offer(new Position(cr-1,cc));
								}else if(cr==0) {
									q.offer(new Position(cr-1,cc));
								}
								
							}
						}else {
							q.offer(new Position(cr-1, cc));
						}
					}else if(cnt>=8 && cr>=-8 && cr<0 && cc>=0 && cc<8 && !check[cr+8][cc]) {
						check[cr+8][cc]=true;
						if(cr+8==0 && cc==7) {
							flag = true;
							break;
						}
						q.offer(new Position(cr,cc));
					}
				}
			}
			cnt++;
		}
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