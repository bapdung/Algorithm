import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//1~N번정점까지 이동 (특정 정점 꼭 지나야 함)
public class Main {
	public static int V;
	public static int E;
	public static List<Node>[] adjList;
	public static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		dist = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		boolean[] check = new boolean[V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			check[v1] = true;
			check[v2] = true;
			int w = Integer.parseInt(st.nextToken());
			adjList[v1].add(new Node(v2, w));
			adjList[v2].add(new Node(v1, w));
		}

		st = new StringTokenizer(br.readLine());
		int V1 = Integer.parseInt(st.nextToken());
		int V2 = Integer.parseInt(st.nextToken());
		
		if(!(check[1] && check[V1] && check[V2] && check[V])) {
			System.out.println(-1);
			System.exit(0);
		}
		
		
		dist[V1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(V1, dist[V1]));
		boolean[] visit = new boolean[V + 1];
		visit[V1] = true;

		int case1 = 0; // 1-> V1 -> V2 -> N;
		int case2 = 0; // 1-> V2 -> V1 -> N;
		boolean flag1 = true;
		boolean flag2 = true;
		
		//v1->v2가는 최소거리
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Node o : adjList[cur.v]) {
				if (dist[o.v] > dist[cur.v] + o.w) {
					dist[o.v] = dist[cur.v] + o.w;
					visit[o.v] = true;
					pq.add(new Node(o.v, dist[o.v]));
				}

			}
		}
		if (dist[V2] == Integer.MAX_VALUE) {
			System.out.println(-1);
			System.exit(0);
		} else {
			case1 += dist[V2];
			case2 += dist[V2];
		}
		
//		System.out.println(case1 + " " +case2);

		// 1에서 v1, 1에서 v2 가는 최소거리;
		dist = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;
		pq = new PriorityQueue<>();
		pq.add(new Node(1, dist[1]));
		visit = new boolean[V + 1];
		visit[1] = true;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Node o : adjList[cur.v]) {
				if (dist[o.v] > dist[cur.v] + o.w) {
					dist[o.v] = dist[cur.v] + o.w;
					visit[o.v] = true;
					pq.add(new Node(o.v, dist[o.v]));
				}

			}
		}
		if (dist[V1] == Integer.MAX_VALUE) {
			flag1 = false;
		} else if (dist[V2] == Integer.MAX_VALUE) {
			flag2 = false;
		}
		int oneS = dist[V1];
		case1 += oneS;
		int oneE = dist[V2];
		case2 += oneE;
		
//		System.out.println(case1 + " " +case2);
		
		// V1에서 V+1, V2에서 V+1;
		dist = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[V] = 0;
		pq = new PriorityQueue<>();
		pq.add(new Node(V, dist[V]));
		visit = new boolean[V + 1];
		visit[1] = true;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Node o : adjList[cur.v]) {
				if (dist[o.v] > dist[cur.v] + o.w) {
					dist[o.v] = dist[cur.v] + o.w;
					visit[o.v] = true;
					pq.add(new Node(o.v, dist[o.v]));
				}

			}
		}
		if (dist[V1] == Integer.MAX_VALUE) {
			flag2 = false;
		} else if (dist[V2] == Integer.MAX_VALUE) {
			flag1 = false;
		}
		int lastS = dist[V1];
		int lastE = dist[V2];
		case1 += lastE;
		case2 += lastS;
		
//		System.out.println(case1 + " " +case2);

		if (!flag2 && !flag1) {
			System.out.println(-1);
		} else {
			int min = Math.min(case1, case2);
			System.out.println(min);
		}

	}

	public static class Node implements Comparable<Node> {
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