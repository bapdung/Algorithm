import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int F, S, G, U, D;
	public static int[] arr;
	public static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken()); //총 층수
		S = Integer.parseInt(st.nextToken()) - 1; // 시작 층
		G = Integer.parseInt(st.nextToken()) - 1; // 도착 층
		U = Integer.parseInt(st.nextToken()); // 올라가는 층
		D = Integer.parseInt(st.nextToken()); //내려가는 층
		
		if(S==G) {
			System.out.println(0);
			System.exit(0);
		}
		
		arr = new int[F];
		check = new boolean[F];
		
		//bfs
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(S);
		check[S] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			cnt ++;
			while(size-->0) {
				int cur = q.poll();
				int up = cur + U;
				int down = cur - D;
				if(up == G || down == G) {
					System.out.println(cnt);
					System.exit(0);
				}
				if(up < F && !check[up]) {
					q.offer(up);
					check[up] = true;
				}
				
				if(down >= 0 && !check[down]) {
					q.offer(down);
					check[down] = true;
				}
			}
		}
		System.out.println("use the stairs");
	}
}