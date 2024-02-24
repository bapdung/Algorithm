import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] rule = new int[N];
		for(int i=N-1; i>=0; i--) {
			rule[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			q.offerLast(i);
		}
		//1,2,3,4,5(위 -> 아래)
		
		ArrayDeque<Integer> rst = new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			switch(rule[i]) {
			case 1:
				rst.offerFirst(q.pollFirst());
				break;
			case 2:
				if(rst.size()>=1) {
					int a = rst.pollFirst();
					rst.offerFirst(q.pollFirst());
					rst.offerFirst(a);
				}
				break;
			case 3:
				if(rst.size()>=1) {
					rst.offerLast(q.pollFirst());
				}
				break;
			}
		}
		while(!rst.isEmpty()) {
			sb.append(rst.pollFirst() + " ");
		}
		System.out.println(sb);
	}
}