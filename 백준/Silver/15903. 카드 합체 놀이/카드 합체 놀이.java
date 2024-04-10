import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		for(int m=0; m<M; m++) {
//			System.out.println(pq);
			Long a = pq.poll();
//			int cnt = 0;
			Long cur = pq.poll();
			Long hap = a + cur;
			pq.add(hap);
			pq.add(hap);
			
//			while(true) {
//				int cur = pq.poll();
//				System.out.println(a + " " + cur);
//				if(cur != a) {
//					int hap = a + cur;
//					pq.add(hap);
//					pq.add(hap);
//					for(int i=0; i<cnt; i++) {
//						pq.add(a);
//					}
//					break;
//				} else {
//					cnt++;
//				}
//			}
		}
		
		
		Long total = 0L;
		while(!pq.isEmpty()) {
			total += pq.poll();
		}
		System.out.println(total);
	}
}