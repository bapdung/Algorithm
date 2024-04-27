import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, D, cr, cc;
	public static int[] dr = {-1,0,1,0};
	public static int[] dc = {0,1,0,-1};
	public static boolean [][] check;
	public static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		cr = Integer.parseInt(st.nextToken());
		cc = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		check = new boolean[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<M ; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if(arr[r][c] == 1) check[r][c]= true;
			}
		}
		
		int cnt = 0;
		while(true) {
//			System.out.println(cr + " " + cc + " " + D);
			//1
			if(!check[cr][cc]) {
				check[cr][cc] = true;
				cnt++;
			}
			//청소되지 않은 칸 존재
			if(check(cr,cc)) {
				D = (D +4 - 1) % 4;
				int nr = cr + dr[D];
				int nc = cc + dc[D];
				if(nr>=0 && nr<N && nc>=0 && nc<M &&arr[nr][nc] != 1 && !check[nr][nc]) {
					cr = nr;
					cc = nc;
				}
			}
			//청소되지 않은 칸 존재X
			else {
				int nr = cr - dr[D];
				int nc = cc - dc[D];
				if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc] != 1) {
					cr = nr;
					cc = nc;
				}else {
					break;
				}
			}
		}
		System.out.println(cnt);
		
		
	}
	public static boolean check(int r,int c) {
		for(int d=0; d<4; d++) {
			int curr = r + dr[d];
			int curc = c + dc[d];
			if(curr>=0 && curr<N && curc>=0 && curc<M) {
				if(!check[curr][curc]) return true;
			}
		}
		return false;
	}
}