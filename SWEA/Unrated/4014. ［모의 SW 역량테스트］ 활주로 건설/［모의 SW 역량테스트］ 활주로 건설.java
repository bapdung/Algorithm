import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static int N;
	public static int X, rst;
	public static int[][] arr;
	public static int[] install;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			rst = 0;
			
			//행 탐색
			for(int r=0; r<N; r++) {
				install = new int[N];
				boolean flag = true;
				for(int c=1; c<N; c++) {
					if(arr[r][c]-arr[r][c-1] == 1) { //위
						if(install[c-1] == 0) {
							install[c-1] = 1;
						} else {
							flag = false;
							break;
						}
					} else if(arr[r][c]-arr[r][c-1]== -1) { // 아래
						if(install[c] ==0) {
							install[c] = -1;
						} else {
							flag = false;
							break;
						}
					} else if(arr[r][c]-arr[r][c-1] != 0) {//2칸이상의 차이
						flag = false;
						break;
					}
				}
				//검증
				if(flag) {
					flag = validate();
				}
				if(flag) {
					rst++;
				}
			}
			
			//열탐색
			for(int c=0; c<N; c++) {
				install = new int[N];
				boolean flag = true;
				for(int r=1; r<N; r++) {
					if(arr[r][c]-arr[r-1][c] == 1) { //위
						if(install[r-1] == 0) {
							install[r-1] = 1;
						} else {
							flag = false;
							break;
						}
					} else if(arr[r][c]-arr[r-1][c]== -1) { // 아래
						if(install[r] ==0) {
							install[r] = -1;
						} else {
							flag = false;
							break;
						}
					} else if(arr[r][c]-arr[r-1][c] != 0) {//2칸이상의 차이
						flag = false;
						break;
					}
				}
				//검증
				if(flag) {
					
					flag = validate();
				}
				if(flag) {
					rst++;
				}
			}
			sb.append("#"+tc + " " + rst + "\n");
		}
		System.out.println(sb);
	}
	public static boolean validate() {
		for(int i=0; i<N; i++) {
			if(install[i] == 1) {
				int l = 0;
				while(++l<X) {
					if(i-l>= 0 && install[i-l]==0) {
						install[i-l] = 3;
					} else {
						return false;
					}
				}
			} else if(install[i] == -1) {
				int l = 0;
				while(++l<X) {
					if(i+l<N && install[i+l]== 0) {
						install[i+l] = 3;
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
}