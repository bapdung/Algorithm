import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[][] arr;
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
		
		
		for(int n = 0; n < M; n++) {
			st = new StringTokenizer(br.readLine(), " "); 
			int zero = Integer.parseInt(st.nextToken()); 
			int one = Integer.parseInt(st.nextToken()); 
			int two = Integer.parseInt(st.nextToken()); 

			// 제일 왼쪽 열 애벌레 키우기 
			for(int i = N-1; i > 0; i--) { 
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					arr[i][0] += 1;
				} else if(two != 0) {
					two--;
					arr[i][0] += 2;
				}
			}

			// 제일 위쪽 행 애벌레 키우기 
			for(int i = 0; i < N; i++) {
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					arr[0][i] += 1;
				} else if(two != 0) {
					two--;
					arr[0][i] += 2;
				}
			}
		}
		
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