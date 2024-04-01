import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int R;
	public static int C;
	public static int[][] arr;
	public static boolean[][] check;
	public static PriorityQueue<Edge> pq = new PriorityQueue<>();
	public static int[] parents;
	public static int[] dr = {0,1,0,-1};
	public static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		check = new boolean[R][C];
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//섬마다 고유번호 부여
		int n = 1;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(arr[r][c] == 1 && !check[r][c]) {
					bfs(new Position(r,c),n++);
				}
			}
		}
		n = n-1;
		parents = new int[n+1];
		
		//연결가능한 다리 pq 삽입
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(arr[r][c] !=0) {
					findBridge(r,c,arr[r][c]);
				}
			}
			
		}
		for(int i=1; i<=n; i++) {
			parents[i] = i;
		}
		
		//크루스칼
		int dist = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(union(cur.v1, cur.v2)) {
				dist += cur.w;
				cnt++;
			}
			if(cnt == n-1) break;
		}
		
		if(cnt == n-1) {
			System.out.println(dist);
		} else {
			System.out.println(-1);
		}
		
		
	}
	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		} else {
			return parents[a] = find(parents[a]);
		}
	}
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP==bP) return false;
		else {
			parents[bP] = aP;
			return true;
		}
	}
	public static void findBridge(int r, int c, int n) {
		for(int d=0; d<2; d++) {
			int l = 1;
			while(true) {
				int cr = r + (l)*dr[d];
				int cc = c + (l++) * dc[d];
				if(cr>=0 && cr<R && cc>=0 && cc<C) {
					if(arr[cr][cc]==n) {
						break;
					}else if(arr[cr][cc] !=0) {
						if(l-2>=2) pq.add(new Edge(n, arr[cr][cc],l-2));
						break;
					}
				} else break;
			}
		}
	}
	public static void bfs(Position p, int n) {
		Queue<Position> q = new ArrayDeque<>();
		q.offer(p);
		arr[p.r][p.c] = n;
		check[p.r][p.c] = true;
		while(!q.isEmpty()) {
			Position cur = q.poll();
			for(int d=0; d<4; d++) {
				int cr = cur.r + dr[d];
				int cc = cur.c + dc[d];
				if(cr>=0 && cr<R && cc>=0 && cc<C && !check[cr][cc] && arr[cr][cc] == 1) {
					check[cr][cc] = true;
					arr[cr][cc] = n;
					q.offer(new Position(cr,cc));
				}
			}
		}
	}
	public static class Edge implements Comparable<Edge>{
		int v1;
		int v2;
		int w;
		public Edge(int v1, int v2, int w) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
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