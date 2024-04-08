import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N, K, L;
	static int[][] arr;
	static int[] dr = {0,1,0,-1};//오, 아래, 왼, 위
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			arr[r][c] = 1;
		}
		char[] direction = new char[10001];
		L = Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			direction[t] = dir;
		}
		
		int d = 0;
		int r =0;
		int c=0;
		int time = 0;
		boolean[][] check = new boolean[N][N];
		TreeMap<Integer, Position> set = new TreeMap<>();
		set.put(time, new Position(0,0));
		check[0][0] = true;
		while(true) {
			time++;
			r += dr[d];
			c += dc[d];
			if(r>=0 && r<N && c>=0 && c<N && !check[r][c]) {
				set.put(time, new Position(r,c));
				check[r][c] = true;
				if(arr[r][c] == 0) {
					int first = set.firstKey();
					check[set.get(first).r][set.get(first).c] = false;
					set.remove(first);
				} else {
					arr[r][c] = 0;
				}
			}
			else {
				break;
			}
			if(direction[time]!='\u0000') {
				//방향전환
				if(direction[time]=='L') {
					d = (d+3) % 4;
				}else {
					d = (d+1) % 4;
				}
			}
		}
		System.out.println(time);
		
	}
	public static class Position{
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}