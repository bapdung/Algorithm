import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int TC, N, M, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //마을에 있는 집의 개수
			M = Integer.parseInt(st.nextToken()); //돈을 훔칠 연속된 집의 개수
			K = Integer.parseInt(st.nextToken()); //자동방범장치 작동하는 최소 돈의 양
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int cnt = 0;
			int sum = 0;
			for(int i=0; i<M; i++) {
				sum += arr[i];
			}
			if(sum <K) cnt++;
			
			int right = M-1;
			for(int left=1; left<=N-1; left++) {
				sum -= arr[left-1];
				sum+= arr[(++right) % N];
				if(sum <K) cnt++;
			}
			
			if(N == M && cnt == N) sb.append(1 + "\n");
			else if (N == M && cnt == 0) sb.append(0+ "\n");
			else sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}
}