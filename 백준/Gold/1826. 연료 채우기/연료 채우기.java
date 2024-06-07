import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int N, end, gas;
	public static int start = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Node(idx, w));
			
		}
		st = new StringTokenizer(br.readLine());
		end = Integer.parseInt(st.nextToken());
		gas = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());
		int ans = 0;
		
		while(gas<end) {
			while(!pq.isEmpty() && pq.peek().idx <= gas) {
				fuels.add(pq.poll().w);
			}
			
			if(fuels.isEmpty()) {
				System.out.println(-1);
				return;
			}
			
			ans++;
			gas += fuels.poll();
		}
		
		System.out.println(ans);
		
		
	}
	public static class Node implements Comparable<Node>{
		int idx;
		int w;
		
		public Node(int idx, int w) {
			super();
			this.idx = idx;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
				return this.idx - o.idx;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", w=" + w + "]";
		}
		
		
	}
}