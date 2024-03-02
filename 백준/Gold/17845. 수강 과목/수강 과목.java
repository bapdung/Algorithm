import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int limit;
	public static int N;
	public static Class[] arr;
	public static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		limit = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new Class[N+1];
		for(int i=1; i<N+1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int value = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			arr[i] = new Class(value, time);
		}
		memo = new int[N+1][limit+1];
		System.out.println(dp(N, limit));
	}
	public static int dp(int i, int v) {
		if(i>=1 && v>=1 && memo[i][v]==0) {
			int unselected = dp(i-1,v);
			if(v-arr[i].time>=0) {
				int selected = dp(i-1, v-arr[i].time) + arr[i].value;
				memo[i][v] = Math.max(unselected, selected);
			}else {
				memo[i][v] = unselected;
			}
		}
		return memo[i][v];
	}
	public static class Class{
		int value;
		int time;
		public Class(int value, int time) {
			super();
			this.value = value;
			this.time = time;
		}
	}
}