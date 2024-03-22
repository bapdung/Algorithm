import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = null;
		StringBuilder sb = new StringBuilder();
		tokens = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		
		int[][] arr = new int[n][n];
		int[][] sumArr = new int[n][n];
		
		for(int r = 0; r<n; r++) {
			tokens = new StringTokenizer(br.readLine());
			for(int c = 0; c<n; c++) {
				arr[r][c] = Integer.parseInt(tokens.nextToken());
				if(r==0&& c==0) {
					sumArr[r][c] = arr[r][c];
				} else if(r == 0){
					sumArr[r][c] = sumArr[r][c-1] + arr[r][c];
				} else if(c == 0){
					sumArr[r][c] = sumArr[r-1][c] + arr[r][c];
				} else{
					sumArr[r][c] = sumArr[r-1][c] + sumArr[r][c-1] - sumArr[r-1][c-1] + arr[r][c];
				}
			}
		}
		for(int i = 0; i<m; i++) {
			tokens = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(tokens.nextToken()) - 1;
			int y1 = Integer.parseInt(tokens.nextToken()) - 1;
			int x2 = Integer.parseInt(tokens.nextToken()) - 1;
			int y2 = Integer.parseInt(tokens.nextToken()) - 1;
			int sum = 0;
			if(x1==0 && y1==0) {
				sum = sumArr[x2][y2];
			} else if(x1 == 0){
				sum = sumArr[x2][y2] - sumArr[x2][y1-1];
			} else if(y1 == 0){
				sum = sumArr[x2][y2] - sumArr[x1-1][y2];
			} else{
				sum = sumArr[x2][y2] - sumArr[x2][y1-1] - sumArr[x1-1][y2] + sumArr[x1-1][y1-1];
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb);	
	}

}