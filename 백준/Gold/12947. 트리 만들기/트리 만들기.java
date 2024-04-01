import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		int right = N;
		int left = N;
		for(int i=1; i<=N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(n==1) {
				left = i-1 + (left-N);
				max = Math.max(left+right, max);
				left = N-i;
				right = N-i;
			}
		}
		max = Math.max(left+right, max);
		System.out.println(max);
	}
}