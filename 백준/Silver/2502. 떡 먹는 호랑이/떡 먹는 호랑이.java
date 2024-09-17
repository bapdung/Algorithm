import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] fibo = new int[31];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		fibo[1] = 1;
		fibo[2] = 1;
		for(int t=3; t<=D; t++) {
			fibo[t] = fibo[t-1] + fibo[t-2];
		}
		//a(D-2) +b(D-1) = K;

		loop:
		for(int a=1; a<=K; a++) {
			for(int b=1; b<=K; b++) {
				if(a*fibo[D-2] + b*fibo[D-1] == K){
					System.out.println(a + "\n" + b );
					break loop;
				}
			}
		}
	}
}