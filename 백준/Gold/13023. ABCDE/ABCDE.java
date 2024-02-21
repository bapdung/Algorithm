import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int flag = 0;
	public static List<Integer>[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N];
		for(int i=0; i<N ; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		
		
		for(int i=0; i<N ; i++) {
			boolean[] visit = new boolean[N];
			//visit[i] = true;
			dfs(i, 1, visit);
			if(flag == 1) break;
		}
		System.out.println(flag);
	}
	public static void dfs(int start, int cnt, boolean[] visit) {
		if(cnt == 5) {
			flag = 1;
			return;
		}
		visit[start] = true;
		for(Integer v : adjList[start]) {
			if(!visit[v]) {
				//visit[v] = true;
				dfs(v, cnt+1, visit);
				//visit[v]= false;
				
			}
		}
		visit[start] = false;
	}
}