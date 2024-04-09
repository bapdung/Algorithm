import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[][] arr, grow;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				arr[r][c] = 1;
			}
		}
		
		grow = new int[M][N*2-1];
		
		for(int i=0; i<M; i++) {
			int cnt = 0;
//			System.out.println("===");
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<=2; j++) {
				int n = Integer.parseInt(st.nextToken());
				while(n-->0) {
					if(cnt<=N-1) {
						arr[N-1-cnt][0] += j;
//						System.out.println(N-1-cnt + " " + 0);
//						System.out.println(arr[N-1-cnt][0] );
					} else {
						arr[0][(cnt%N)+1] += j;
//						System.out.println(0 + " " + (cnt%N+1));
//						System.out.println(arr[0][(cnt%N)] );
					}
					cnt++;
				}
			}
//			for(int r=0; r<N; r++) {
//				for(int c=0; c<N; c++) {
//					System.out.print(arr[r][c] + " ");
//				}
//				System.out.println();
//			}
			
		}
//		System.out.println();
		
		for(int r=1; r<N; r++) {
			for(int c=1; c<N; c++) {
				arr[r][c] = Math.max(arr[r][0], arr[0][c]);
				arr[r][c] = Math.max(arr[r][c],arr[r-1][c-1]);
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sb.append(arr[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}