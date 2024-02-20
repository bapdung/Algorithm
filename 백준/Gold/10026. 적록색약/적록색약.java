import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static int N;
	public static char[][] arr;
	public static char[][] arr2;
	public static boolean[][] visit;
	public static boolean[][] visit2;
	public static int[] dr = {1,-1,0,0};
	public static int[] dc = {0,0,1,-1};
	public static Queue<int[]> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr= new char[N][N];
		arr2= new char[N][N];
		visit = new boolean[N][N];
		visit2 = new boolean[N][N];
		for(int r=0; r<N; r++) {
			String str = br.readLine();
			for(int c=0; c<N; c++) {
				arr[r][c] = str.charAt(c);
				if(arr[r][c] == 'G') arr2[r][c] = 'R';
				else arr2[r][c] = arr[r][c];
			}
		}
		
		
		q = new ArrayDeque<>();
		q.offer(new int[] {0,0});
		//visit[0][0] = true;
		int cnt1 = 0;
		while(!q.isEmpty()) {
			int[] value = q.poll();
			if(!visit[value[0]][value[1]]) {
				dfs(value[0],value[1],arr[value[0]][value[1]], visit, arr);;
				cnt1++;
			}
		}
		sb.append(cnt1 + " ");
		
		q.offer(new int[] {0,0});
		int cnt2 = 0;
		while(!q.isEmpty()) {
			int[] value = q.poll();
			if(!visit2[value[0]][value[1]]) {
				dfs(value[0],value[1],arr2[value[0]][value[1]], visit2, arr2);;
				cnt2++;
			}
		}
		sb.append(cnt2);
		
		System.out.println(sb);
	}
	public static void dfs(int r, int c, char color, boolean[][] visit, char[][] arr) {
		visit[r][c] = true;
		for(int d=0; d<4; d++) {
			int cr = r + dr[d];
			int cc = c + dc[d];
			if(cr>=0 && cr<N && cc>=0 && cc<N && !visit[cr][cc]) {
				if( arr[cr][cc] == color) {
					//System.out.println(cr + " " +cc);
					visit[cr][cc] = true;
					dfs(cr,cc,color, visit, arr);
				} else {
					q.offer(new int[] {cr, cc});
				}
				
			}
		}
	}
	
}