import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static int E;
	public static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		Node[] edges = new Node[E];
		long total = 0;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Node(v1,v2,w);
			total += w;
		}
		Arrays.sort(edges);
		parents = new int[V+1];
		Arrays.fill(parents, -1);
		
		int cnt = 0;
		long mstSum = 0;
		for(int i=0; i<E; i++) {
			Node o = edges[i];
			int v1 = o.v1;
			int v2 = o.v2;
			int w = o.weight;
			if(union(v1,v2)) {
				mstSum += w;
				if(++cnt == V-1) {
					System.out.println(total - mstSum);
					System.exit(0);
				}
			}
		}
		System.out.println(-1);
		
		
		
	}
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP == bP) return false;
		else {
			parents[aP] += parents[bP];
			parents[bP] = aP;
			return true;
		}
	}
	public static int find(int a) {
		if(parents[a] <0) {
			return a;
		}
		else return parents[a] = find(parents[a]);
	}
	public static class Node implements Comparable<Node>{
		int v1, v2, weight;

		public Node(int v1, int v2, int weight) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
		
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
}