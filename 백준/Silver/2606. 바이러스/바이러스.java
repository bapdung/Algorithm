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
	public static List<Integer>[] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(br.readLine());
		int pair = Integer.parseInt(br.readLine());
		
		//인접리스트 초기화
		adjList = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<pair; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		int cnt = bfs(1);
		System.out.println(cnt - 1); //나 자신 제외

	}
	public static int bfs(int a) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[V+1];
		q.offer(a);
		visit[a] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int v1 = q.poll();
			cnt++;
			for(int v2 : adjList[v1]) {
				if(!visit[v2]) {
					visit[v2] = true;
					q.offer(v2);
				}
			}
		}
		return cnt;
	}
}