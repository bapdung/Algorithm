import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static boolean[] visit;
	public static int[] edge;
	public static int[] sequence;
	public static int sIdx;
	public static List<Integer>[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new boolean[N+1];
		edge = new int[N+1];
		adjList = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if(adjList[from].contains(to)) continue;
			adjList[from].add(to);
			edge[to]++;
		}
		
		
		sequence = new int[N];
		for(int i=0; i<N ; i++) {
			정렬();
		}
		
		for(int i=0; i<N; i++) {
			sb.append(sequence[i] + " ");
		}
		System.out.println(sb);

		
		
	}
	public static void 정렬() {
		for(int i=1; i<N+1; i++) {
			if(edge[i]==0 && !visit[i]) {
				visit[i] = true;
				sequence[sIdx++] = i;
				for(int a : adjList[i]) {
					edge[a]--;
				}
			}
		}
	}
}
