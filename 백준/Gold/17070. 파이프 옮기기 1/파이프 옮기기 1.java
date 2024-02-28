import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr;
	public static int N;
	public static int rst;
	//가로 : 0 세로 : 1 대각선 : 2 :::: ways[현방향] -> {새로운 방향, 행, 열}
	public static int[][][] ways = {{{0,0,1}, {2,1,1}}, {{1,1,0},{2,1,1}}, {{0,0,1}, {1,1,0}, {2,1,1}}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N ;c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(new Pipe(0,0,1));
		System.out.println(rst);
	}
	public static void dfs(Pipe p) {
		if(p.r==N-1 && p.c==N-1) {
			rst++;
			return;
		}
		int d = p.d;
		int r = p.r;
		int c = p.c;
		for(int i=0; i<ways[d].length ; i++) {
			int cr = r + ways[d][i][1];
			int cc = c + ways[d][i][2];
			if(cr>=0 && cc>=0 && cr<N && cc<N) {
				if(check(ways[d][i][0], cr,cc)){
					dfs(new Pipe(ways[d][i][0], cr,cc));
				}
			}
		}
	}
	public static boolean check(int way, int cr, int cc) {
		if(way == 0) {
			if(arr[cr][cc] ==0) return true;
		}else if(way == 1) {
			if(arr[cr][cc] == 0) return true;
		} else {
			if(arr[cr][cc] == 0 && arr[cr-1][cc] == 0 && arr[cr][cc-1] == 0) return true;
		}
		return false;
	}
	public static class Pipe{
		int d;
		int r;
		int c;
		public Pipe(int d, int r, int c) {
			super();
			this.d = d;
			this.r = r;
			this.c = c;
		}
	}
}