import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 서지흔
 * @date 24/03/28
 * @link https://www.acmicpc.net/problem/3055
 * @keyword_solution - 다익스트라 최단경로 - 2차원 배열이므로 R*N + C 와 같이 1차원으로 변형 후 다익스트라 계산
 * 
 * @input - 50보다 작거나 같은 자연수 R과 C
 * 
 * @output - 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간 - 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"
 * @time_complex
 * @perf kb, ms
 */
public class Main {
	public static int R;
	public static int C;
	public static char[][] arr;
	public static boolean check[][];
	public static Position start;
	public static Position end;
	public static int[] dr = { 0, 0, 1, -1 };
	public static int[] dc = { 1, -1, 0, 0 };
	public static List<Position> dfdfdf = new ArrayList<>();
	public static Queue<Position> water = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		check = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				arr[r][c] = str.charAt(c);
				if (arr[r][c] == 'D') {
					end = new Position(r, c);
				} else if (arr[r][c] == 'S') {
					start = new Position(r, c);
					arr[r][c] ='.';
				} else if (arr[r][c] == '*') {
					check[r][c] = true;
					water.offer(new Position(r, c));
				} else if (arr[r][c] == 'X') {
					check[r][c] = true;
				}
			}
		}
		
		bfs(start.r, start.c);

	}

	public static void bfs(int r, int c) {
		Queue<Position> q = new ArrayDeque<>();
		int time = 0;
		q.offer(start);
		check[start.r][start.c] = true;
		
		while(!q.isEmpty()) {
			int waterSize = water.size();
			while(waterSize-->0) {
				Position cur = water.poll();
				for(int d=0; d<4; d++) {
					int cr = cur.r + dr[d];
					int cc = cur.c + dc[d];
					if(cr>=0 && cr<R && cc>=0 && cc<C && !check[cr][cc] && arr[cr][cc] == '.'){
						check[cr][cc] = true;
						water.offer(new Position(cr,cc));
					}
				}
			}
			int size = q.size();
			while(size-->0) {
//				System.out.println(q);
				Position cur = q.poll();
				for(int d=0; d<4; d++) {
					int cr = cur.r + dr[d];
					int cc = cur.c + dc[d];
					if(cr>=0 && cr<R && cc>=0 && cc<C && !check[cr][cc]){
						if(cr == end.r && cc == end.c) { //목표지점
							System.out.println(++time);
							return;
						}
						check[cr][cc] = true;
						q.offer(new Position(cr,cc));
					}
						
				}
			}
			time++;
		}
		System.out.println("KAKTUS");
			
	}
	

	public static class Position {
		int r;
		int c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Position [r=" + r + ", c=" + c + "]";
		}
		
		
	}
}