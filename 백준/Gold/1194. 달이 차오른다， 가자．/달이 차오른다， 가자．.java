import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int R;
	public static int C;
	public static char[][] arr;
	public static int[] se;
	public static Position start;
	public static List<Position> end = new ArrayList<>();
	public static boolean[][][] check;
	public static int[] dr = {1,-1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static int min = Integer.MAX_VALUE;
	public static boolean flag = false;
	public static HashSet<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		check = new boolean[R][C][64];

		for(int r=0; r<R; r++) {
			String str = br.readLine();
			for(int c=0; c<C; c++) {
				arr[r][c] = str.charAt(c);
				if(arr[r][c] == '0') {
					arr[r][c] = '.';
					start = new Position(r,c, 0);
				} else if(arr[r][c] == '1') {
					end.add(new Position(r,c, 0));
				} else if(arr[r][c] == '#') {
					for(int i=0; i<64; i++) {
						check[r][c][i] = true;
					}
				}
			}
		}
		
		int cnt = bfs();
		System.out.println(cnt);
		
	}
	public static int bfs() {
		Queue<Position> q = new ArrayDeque<>();
		q.add(start);
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				Position cur = q.poll();
				for(int d=0; d<4; d++) {
					int cr = cur.r + dr[d];
					int cc = cur.c + dc[d];
					if(cr>=0 && cr<R && cc>=0 && cc<C && !check[cr][cc][cur.key]) {
						int diffa = arr[cr][cc] - 'a';
						int diffA = arr[cr][cc] - 'A';
						if(arr[cr][cc] == '1') {
							return ++cnt;
						} else if(diffa>=0 && diffa<=5) {
							int key = (1<<diffa);
							key = cur.key | key;
							check[cr][cc][key] = true;
							q.offer(new Position(cr, cc, key));
							
						} else if(diffA>=0 && diffA<=5) {
							int door = (1<<diffA);
							door = cur.key & door;
							if(door > 0) {
								check[cr][cc][cur.key] = true;
								q.offer(new Position(cr,cc, cur.key));
							}
							
						} else {
							check[cr][cc][cur.key] = true;
							q.offer(new Position(cr, cc, cur.key));
						}
					}
				}
			}
			cnt++;
		}
		return -1;
	}
	public static class Position{
		int r;
		int c;
		int key;
		public Position(int r, int c, int key) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}
}