import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer>[] adjList = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		int[] edge = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjList[v1].add(v2);
			edge[v2]++;
		}
		
		
		TreeSet<Node> set = new TreeSet<>();
		for(int i=1; i<N+1; i++) {
			set.add(new Node(i, edge[i]));
		}
		while(!set.isEmpty()) {
			Node cur = set.first();
			set.remove(cur);
			sb.append(cur.v + " ");
			for(Integer v : adjList[cur.v]) {
				set.remove(new Node(v, edge[v]));
				set.add(new Node(v, --edge[v]));
			}
		}
		System.out.println(sb);
		
	}
	public static class Node implements Comparable<Node>{
		int v;
		int w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Node e) {
			if(e.w == this.w) {
				return this.v - e.v;
			}
			return this.w - e.w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + v;
			result = prime * result + w;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (v != other.v)
				return false;
			if (w != other.w)
				return false;
			return true;
		}
	}
}