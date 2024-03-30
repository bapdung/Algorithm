import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int R;
	public static int C;
	public static int K;
	public static int[][] arr;
	public static int[] dr = {0,0,-1,1};
	public static int[] dc = {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int maxD = 0;
		arr = new int[R+1][C+2];
		for(int r=1; r<=R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1 ;c<C+1; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				maxD = Math.max(maxD, arr[r][c]);
			}
		}
		
		int start = 1;
		int end = maxD;
		int minD = maxD;
		while(start<=end) {
			int mid = (start + end) / 2;
			int remove = bfs(mid);
			if(remove<K) {
				start = mid + 1;
			} else if(remove>=K) {
				end = mid - 1;
				minD = mid;
			}
		}
		System.out.println(minD);
		
		
		
	}
	public static int bfs(int dist) {
		int remove = 0;
		boolean[][] check = new boolean[R+1][C+2];
		Queue<Position> q = new ArrayDeque<>();
		q.offer(new Position(0,0));
		check[0][0] = true;
		while(!q.isEmpty()) {
			Position cur = q.poll();
			for(int d=0; d<4; d++) {
				int cr = cur.r + dr[d];
				int cc = cur.c + dc[d];
				if(cr>=0 && cr<R+1 && cc>=0 && cc<C+2 && !check[cr][cc]) {
					if(arr[cr][cc] <= dist) {
						check[cr][cc] = true;
						q.offer(new Position(cr,cc));
						if(arr[cr][cc] != 0) remove++;
					}
				}
			}
			
		}
		
		return remove;
		
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