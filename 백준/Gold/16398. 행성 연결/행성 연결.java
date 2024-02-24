import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static int[][] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		V = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[V+1][V+1];
		for(int r=1; r<V+1 ; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1 ; c<V+1 ; c++) {
				int w = Integer.parseInt(st.nextToken());
				adjMatrix[r][c] = w;
			}
		}
		
		int[] dist = new int[V+1];
		for(int i=1; i<V+1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[V+1];
		pq.offer(new Edge(1, dist[1]));
		
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(!visit[e.to]) {
				visit[e.to] = true;
				for(int i=1; i<V+1; i++) {
					if(!visit[i] && adjMatrix[e.to][i] != 0 && dist[i] > adjMatrix[e.to][i]) {
						dist[i] =  adjMatrix[e.to][i];
						pq.offer(new Edge(i, dist[i]));
					}
				}
				if(++cnt == V-1) break;
			}
		}
		
		long cost = 0;
		for(int i=1; i<V+1; i++) {
			cost += dist[i];
		}
		System.out.println(cost);
		
		
	}
	public static class Edge implements Comparable<Edge>{
		int to;
		int from;
		int weight;
		public Edge(int to, int from, int weight) {
			super();
			this.to = to;
			this.from = from;
			this.weight = weight;
		}
		
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
		
		
	}
}