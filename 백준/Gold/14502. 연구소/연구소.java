import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int R;
	public static int C;
	public static int zeroCnt;
	public static int max = 0;
	public static int[][] arr;
	public static List<Position> virus = new ArrayList<>();
	public static Position[] wall = new Position[3];
	public static int[] dr = {0,0,1,-1};
	public static int[] dc = {1,-1,0,0};
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		for(int r=0 ;r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C ; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if(arr[r][c] == 2) {
					virus.add(new Position(r,c));
				}
				else if(arr[r][c] == 0) {
					zeroCnt++; //원래 감염되지 않은 땅
				}
			}
		}
		
		combination(0,0);
		System.out.println(max);
		
	}
	public static void combination(int cnt, int start) {
		if(cnt == 3) {
			bfs();
			return;
		}
        //벽 설치 조합
		 for(int i = start; i < R * C; i++) { //모든 원소 고려
		        int r = i / C; // 행 계산
		        int c = i % C; // 열 계산
		        if(arr[r][c] == 0) { 
		            wall[cnt] = new Position(r, c);
		            arr[r][c] = 3;
		            combination(cnt + 1, i + 1); 
		            arr[r][c] = 0;
		        }
		    }
	}
    //바이러스 퍼뜨리기
	public static void bfs() {
		Queue<Position> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[R][C];
		for(Position p : virus) {
			q.offer(p);
			visit[p.r][p.c] = true;
		}
		
		int cnt = 0;
		while(!q.isEmpty()) {
//			System.out.println(q);
			Position p = q.poll();
			for(int d=0; d<4; d++) {
				int cr = p.r + dr[d];
				int cc = p.c + dc[d];
				if(cr>=0 && cr<R && cc>=0 && cc<C && !visit[cr][cc] && arr[cr][cc] == 0) {
					cnt++;
					visit[cr][cc] = true;
					q.offer(new Position(cr,cc));
				}
			}
		}
        //원래 감염되지 않은 장소에서 감염된 장소만큼 빼주고 벽도 빼준다
		max = Math.max(max, zeroCnt - cnt - 3);
	}
	public static class Position{
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