import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] check = new boolean[10];
			//0번째부터 9번째를 다 봤느냐 비트마스킹
			int total = (1 << 10) - 1;
			int visited = 0;
			
			int temp = 0;
			int cur = N;
			int cnt = 0;
			loop:
			while(true) {
				cur = N * ++temp;
				String curStr = Integer.toString(cur);
				for(int i=0; i< curStr.length(); i++) {
					int digit = curStr.charAt(i) - '0';
					visited = visited | (1 << digit);
				}
				if(visited == total) break loop;
			}
			
			sb.append("#" + tc + " " + temp * N+ "\n");
		}
		System.out.println(sb);
	}
}