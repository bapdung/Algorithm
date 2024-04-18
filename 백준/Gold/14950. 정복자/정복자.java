import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, T;
	public static int[] parents;
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		List<Node>[] adjList = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Node(v1, v2, w));
		}
		pq.add(new Node(0,1,0));
		
		parents = new int[N+1];
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
		
		boolean[] check = new boolean[N+1];
		check[0] = true;
		int sum = 0;
		int idx = 0;
		List<Node> list = new ArrayList<>();
		while(!pq.isEmpty()) {
			Node e = pq.poll();
			if(check[e.v1] || check[e.v2]) {
				if(union(e.v1, e.v2)) {
//					System.out.println(e.v1 + " " + e.v2);
					if(e.w != 0) {
						sum += (e.w + T*idx++);
					}
//					System.out.println(sum);
					check[e.v1]=true;
					check[e.v2]=true;
					for(Node cur : list) {
						pq.add(cur);
					}
					list.clear();
				} else {
//					list.add(e);
				}
			} else {
				list.add(e);
			}
		}
		
		System.out.println(sum);
		
		
		
		
		
		
		
	}
	public static int find(int a) {
		if(parents[a] == a) return a;
		else {
			return parents[a] = find(parents[a]);
		}
	}
	public static boolean union(int a, int b){
		int aP = find(a);
		int bP = find(b);
//		System.out.println("부모" + aP + " " +bP);
		if(aP != bP) {
			parents[bP] = aP;
			return true;
		}
		return false;
	}
	public static class Node implements Comparable<Node>{
		int v1;
		int v2;
		int w;
		public Node(int v1, int v2, int w) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
		
		
		
	}
}