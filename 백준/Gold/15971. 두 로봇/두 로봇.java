import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, S, E;
	public static List<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[v1].add(new Node(v2, w));
			adjList[v2].add(new Node(v1, w));
		}
		if (S == E || N == 1) {
			System.out.println(0);
		} else {

			int max = 0;
			Route route = bfs();
			st = new StringTokenizer(route.w);
			while (st.hasMoreTokens()) {
				max = Math.max(max, Integer.parseInt(st.nextToken()));
			}
			System.out.println(route.sum - max);
		}
	}

	public static Route bfs() {
		Queue<Route> q = new ArrayDeque<>();
		q.offer(new Route(S, "", 0));
		boolean[] check = new boolean[N + 1];
		check[S] = true;
		while (!q.isEmpty()) {
			Route r = q.poll();
			for (Node cur : adjList[r.v]) {
				if (!check[cur.v]) {
					if (cur.v == E) {
						return new Route(cur.v, r.w + " " + Integer.toString(cur.w), r.sum + cur.w);
					} else {
						q.offer(new Route(cur.v, r.w + " " + Integer.toString(cur.w), r.sum + cur.w));
						check[cur.v] = true;
					}
				}
			}
		}
		return null;
	}

	public static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}

	public static class Route {
		int v;
		String w;
		int sum;

		public Route(int v, String w, int sum) {
			super();
			this.v = v;
			this.w = w;
			this.sum = sum;
		}
	}
}