import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] cups;
	public static int candy;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			candy = Integer.parseInt(st.nextToken()); //현재 사탕위치
			int turn = Integer.parseInt(st.nextToken());
			cups = new int[N+1]; //1부터 N까지의 종이컵
			for(int t=1; t<=turn; t++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				swap(a,b);
			}
			sb.append(candy + "\n"); 
		System.out.println(sb);
	}
	public static void swap(int a, int b) {
		if(candy == a) {
			candy = b;
		}
		else if(candy == b) {
			candy = a;
		}
		int temp = cups[a];
		cups[a] = cups[b];
		cups[b] = temp;
		
	}
}