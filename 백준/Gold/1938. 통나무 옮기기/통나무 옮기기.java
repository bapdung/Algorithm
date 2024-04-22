import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static Position train;
	public static Position end;
	public static int N;
	public static int[][] arr;
	public static int[] dr = {-1,0,1,0};
	public static int[] dc = {0,1,0,-1};
	public static boolean[][][] check;
	//0 : 가로 1:세로
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Position> trainQ = new PriorityQueue<>();
		PriorityQueue<Position> endQ = new PriorityQueue<>();
		arr = new int[N][N];
		check = new boolean[N][N][2];
		for(int r=0; r<N; r++) {
			String str = br.readLine();
			for(int c=0; c<N; c++) {
				if(str.charAt(c) == 'B') {
					trainQ.add(new Position(r,c));
				}else if(str.charAt(c) == 'E') {
					endQ.add(new Position(r,c));
				}else {
					arr[r][c] = (int)(str.charAt(c) - '0');
					if(arr[r][c] == 1) {
						check[r][c][0] = true;
						check[r][c][1] = true;
					}
				}
			}
		}
		
		Position a = trainQ.poll();
		Position b = trainQ.poll();
		if(a.r == b.r) train = new Position(b.r, b.c, 0);
		else train = new Position(b.r, b.c, 1);
		
		a = endQ.poll();
		b = endQ.poll();
		if(a.r == b.r) end = new Position(b.r, b.c, 0);
		else end = new Position(b.r, b.c, 1);
				
		int rst = bfs();
		System.out.println(rst);
	}
	public static int bfs() {
		Queue<Position> q = new ArrayDeque<>();
		check[train.r][train.c][train.d] = true;
		q.offer(train);
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			while(size-->0){
				Position cur = q.poll();
				if(cur.d == 0) { //가로인 상황
					for(int d=0; d<4; d++) {
						int cr = cur.r + dr[d];
						int cc= cur.c + dc[d];
						if(cr>=0 && cr<N && cc>=0 && cc<N && !check[cr][cc][cur.d]) {
							//양옆체크
							if(cc-1>=0 && arr[cr][cc-1] != 1 && cc+1<N && arr[cr][cc+1] != 1) {
								if(isEnd(cr,cc,cur.d)) {
									return cnt;
								}
								check[cr][cc][cur.d] = true;
								q.offer(new Position(cr,cc,cur.d));
							}
						}
					}
					if(!check[cur.r][cur.c][1]) {
						if(cur.r-1>=0 && cur.r+1<N && arr[cur.r-1][cur.c]!=1 && arr[cur.r+1][cur.c]!=1
							&& arr[cur.r-1][cur.c-1] != 1 && arr[cur.r-1][cur.c+1]!=1 && arr[cur.r+1][cur.c-1]!=1 && arr[cur.r+1][cur.c+1]!=1) {
							if(isEnd(cur.r, cur.c, 1)) {
								return cnt;
							}
							check[cur.r][cur.c][1] = true;
							q.offer(new Position(cur.r, cur.c, 1));
						}
					}
				} else { //세로인 상황
					for(int d=0; d<4; d++) {
						int cr = cur.r + dr[d];
						int cc= cur.c + dc[d];
						if(cr>=0 && cr<N && cc>=0 && cc<N && !check[cr][cc][cur.d]) {
							//양옆체크
							if(cr-1>=0 && arr[cr-1][cc] != 1 && cr+1<N && arr[cr+1][cc] != 1) {
								if(isEnd(cr,cc,cur.d)) {
									return cnt;
								}
								check[cr][cc][cur.d] = true;
								q.offer(new Position(cr,cc,cur.d));
							}
						}
					}
					if(!check[cur.r][cur.c][0]) {
						if(cur.c-1>=0 && cur.c+1<N && arr[cur.r][cur.c-1]!=1 && arr[cur.r][cur.c+1]!=1
								&& arr[cur.r-1][cur.c-1] != 1 && arr[cur.r-1][cur.c+1]!=1 && arr[cur.r+1][cur.c-1]!=1 && arr[cur.r+1][cur.c+1]!=1) {
							if(isEnd(cur.r, cur.c, 0)) {
								return cnt;
							}
							check[cur.r][cur.c][0] = true;
							q.offer(new Position(cur.r, cur.c, 0));
						}
					}
				}	
			}
		}
		return 0;
	}
	public static boolean isEnd(int r, int c, int d){
		if(end.r == r && end.c == c && end.d == d) {
			return true;
		}
		return false;
	}
	
	public static class Position implements Comparable<Position>{
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
		public String toString() {
			return "Position [r=" + r + ", c=" + c + " d=" +d + "]";
		}
		@Override
		public int compareTo(Position o) {
			if(this.r == o.r) {
				return this.c-o.c;
			}
			return this.r-o.r;
		}
	}
}