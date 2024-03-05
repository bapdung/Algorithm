import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static int E;
	public static int center;
	public static int[] parents;
	public static List<Integer>[] adjList;
	public static Node[] edge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		center = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[V+1];
		
		parents = new int[V+1];
		for(int i=0; i<V+1; i++) {
			parents[i] = i;
		}
		
		edge = new Node[E+center];
		for(int i=0;i<V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//센터 연결
		st = new StringTokenizer(br.readLine());
		for(int i=0;i <center; i++) {
			edge[i] = new Node(0,Integer.parseInt(st.nextToken()), 0);
		}
		
		for(int i=center ; i<E+center; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edge[i] = new Node(v1,v2,w);
		}
		
		Arrays.sort(edge, (o1, o2)->{
			return o1.w - o2.w;
		});
		
		int cnt = 0;
		int total = 0;
		for(int i=0; i<E+center ; i++) {
			int v1 = edge[i].v1;
			int v2 = edge[i].v2;
			if(union(v1,v2)) {
				total += edge[i].w;
				if(++cnt == V) break;
			}
		}
		System.out.println(total);
		
	}
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP==bP) return false;
		parents[bP] = aP;
		return true;
	}
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	public static class Node{
		int v1;
		int v2;
		int w;
		
		public Node(int v1, int v2, int w) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
}