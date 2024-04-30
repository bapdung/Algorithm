import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[] parents;
	public static List<Integer>[] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N+1];
		List<Integer>[] 위원회 = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			adjList[i] = new ArrayList<>();
			위원회[i] = new ArrayList<>();
		}
		
		parents = new int[N+1];
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjList[v1].add(v2);
			adjList[v2].add(v1);
			union(v1, v2);
		}
		
		for(int i=1; i<=N; i++) {
			
			위원회[find(i)].add(i);
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N ; i++) {
			if(위원회[i].size() > 0) {
				int min = Integer.MAX_VALUE;
				int idx = 0;
				for(Integer cur : 위원회[i]) {
					int cnt = bfs(cur);
					if(min >= cnt) {
						min = cnt;
						idx = cur;
					}
				}
				pq.add(idx);
			}
		}
		sb.append(pq.size() + "\n");
		while(!pq.isEmpty()) {
			sb.append(pq.poll() + "\n");
		}
		System.out.println(sb);
	}
	public static int bfs(int n) {
		int cnt = 0;
		boolean[] check = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(n);
		check[n] = true;
		while(!q.isEmpty()) {
			cnt++;
			int size = q.size();
			while(size-->0) {
				int p = q.poll();
				for(Integer cur : adjList[p]) {
					if(!check[cur]) {
						check[cur] = true;
						q.offer(cur);
					}
				}
			}
		}
		return cnt;
	}
	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		} else {
			return parents[a] = find(parents[a]);
		}
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
}