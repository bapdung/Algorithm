import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int a = Integer.parseInt(br.readLine());
			HashSet<Integer> set = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<a; i++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			int cnt = 0;
			int b = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<b; i++) {
				int n = Integer.parseInt(st.nextToken());
				if(set.contains(n)) sb.append(1 + "\n");
				else sb.append(0 + "\n");
			}
		}
		System.out.println(sb);
	}
}