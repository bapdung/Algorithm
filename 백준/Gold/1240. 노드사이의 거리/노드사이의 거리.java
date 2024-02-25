import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static boolean[] visit;
	public static List<Node>[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		 adjList = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i=1; i<=V-1; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[v1].add(new Node(v2,w));
			adjList[v2].add(new Node(v1,w));
		}
		for(int i=0; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int total = 0;
			visit = new boolean[V+1];
			visit[v1] = true;
			dfs(v1, v2, total);
		}
		
		
		
	}
	public static void dfs(int a, int b, int total) {
		if(a == b) {
			System.out.println(total);
			return;
		}
		for(Node n : adjList[a]) {
			if(!visit[n.v]) {
				visit[n.v] = true;
				dfs(n.v, b, total + n.w);
			}
		}
		
	}
	public static class Node{
		int v;
		int w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		
	}
}