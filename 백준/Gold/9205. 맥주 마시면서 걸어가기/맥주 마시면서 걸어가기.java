import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int parents[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1 ; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			parents = new int[N+2];
			for(int i=0; i<N+2; i++) {
				parents[i] = i;
			}
			int[][] adjList = new int[N+2][N+2]; //인접리스트
			//시작점 0
			Position[] arr = new Position[N+2];
			for(int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				arr[i] = new Position(r,c);
			}
			
			for(int i=0; i<N+1; i++) {
				for(int j=i+1; j<N+2; j++) {
					double dist = distance(arr[i], arr[j]);
//					System.out.println(dist);
					if(dist<=1000) {
						union(i, j);
//						adjList[i][j] = 1;
//						adjList[j][i] = 1;
					}
				}
			}
//			System.out.println(Arrays.toString(parents));
			int sP = find(0);
			int eP = find(N+1);
			if(sP == eP) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
			//도착점 n+1
			
		}
		System.out.println(sb);
	}
	public static double distance(Position a, Position b) {
		double dist = (Math.abs(a.r-b.r) + Math.abs(a.c-b.c));
		return dist;
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
	
	public static void union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		
//		if(aP==bP) {
//			return;
//		} else {
//			parents[bP] = aP;
//			return;
//		}
		
		if (aP < bP) parents[bP] = aP;
		else parents[aP] = bP;
	}
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		} else {
			return parents[a] = find(parents[a]);
		}
	}
}