import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static int E;
	public static Edge[] edgeList;
	public static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(v1, v2, e);
		}
		Arrays.sort(edgeList, (o1,o2)->{
			return o1.w - o2.w;
		});
		
		parents = new int[V+1];
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
		
		int total = 0;
		for(Edge e : edgeList) {
			if(union(e.v1, e.v2)) {
				total += e.w;
			}
		}
		System.out.println(total);
		
	}
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP == bP) {
			return false;
		}else {
			parents[bP] = aP;
			return true;
		}
	}
	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		}else {
			return parents[a] = find(parents[a]);
		}
	}
	public static class Edge{
		int v1;
		int v2;
		int w;
		public Edge(int v1, int v2, int w) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
}