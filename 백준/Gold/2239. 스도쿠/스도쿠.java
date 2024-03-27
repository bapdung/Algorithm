import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	public static int[][] arr;
	public static StringBuilder sb;
	public static boolean flag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		arr = new int[9][9];
		

		for(int r=0; r<9; r++) {
			String str = br.readLine();
			for(int c=0; c<9; c++) {
				arr[r][c] = (int) (str.charAt(c) - '0');
			}
		}

		dfs(0);
		
		System.out.println(sb);
	
	}
	public static void dfs(int a) {
		if(a == 81) {
			flag = true;
			for(int r=0; r<9; r++) {
				for(int c=0; c<9; c++) {
					sb.append(arr[r][c]);
				}
				sb.append("\n");
			}
			return;
		}
		int r = a / 9;
		int c = a % 9;
		if(arr[r][c]>0) {
			dfs(a+1);
		} else {
			if(flag) return;
			for(int i=1; i<=9; i++) {
				if(check(r,c, i)) {
					arr[r][c] = i;
					dfs(a+1);
					arr[r][c] = 0;
				}else {
					continue;
				}
			}
		}
	}
	public static boolean check(int cr, int cc, int num) {
		for(int i=0; i<9; i++) {
			if(arr[i][cc]== num || arr[cr][i] == num) {
				return false;
			}
		}
		int sr = cr/3 * 3;
		int sc = cc - cc %3;
		for(int r=sr; r<sr+3; r++) {
			for(int c=sc; c<sc+3; c++) {
				if(arr[r][c]==num) {
					return false;
				}
			}
		}
		return true;
	}
}