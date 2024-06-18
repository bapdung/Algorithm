import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc= 1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int check = (1 << n) - 1;
			if((m & check) == check) {
				sb.append("#" + tc + " " +"ON" + "\n");
			}
			else sb.append("#" + tc + " " + "OFF" + "\n");
		}
		System.out.println(sb);
	}
}