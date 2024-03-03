import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static int E;
	public static List<Node>[] adjList;
	public static int[] dist;
	public static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[v1].add(new Node(v2, w));
			adjList[v2].add(new Node(v1, w));
		}
		
		dist = new int[V+1];
		int INF = Integer.MAX_VALUE;
		for(int i=2; i<V+1; i++) {
			dist[i] = INF;
		}
		
		visit = new boolean[V+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, dist[1]));
//		visit[1] = true;
		while(!pq.isEmpty()) {
			if(visit[V]) break;
			Node cur = pq.poll();
			visit[cur.v] = true;
			for(Node o : adjList[cur.v]) {
				if(!visit[o.v] && dist[o.v] > dist[cur.v]+o.w) {
					dist[o.v] = dist[cur.v] + o.w;
//					System.out.println(Arrays.toString(dist));
//					visit[o.v] = true;
					pq.offer(new Node(o.v, dist[o.v]));
				}
			}
		}
//		System.out.println(Arrays.toString(dist));
		System.out.println(dist[V]);
		
		
		
	}
	public static class Node implements Comparable<Node>{
		int v;
		int w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
		
	}
}