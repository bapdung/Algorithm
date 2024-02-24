import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//물대기
//가상의 정점 만든 후 우물 비용을 간선 취급
public class Main {
	public static int V;
	public static int[][] adjMatrix;
	public static int[] water;
	public static int[] cost;
	public static int idx;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		V = Integer.parseInt(br.readLine());
		water = new int[V+1]; //우물설치비용 배열
		idx = 0;
		for(int i=1; i<V+1; i++) {
			water[i] = Integer.parseInt(br.readLine());
		}
	
		adjMatrix = new int[V+1][V+1]; //인접행렬(물길러오는 비용)
		for(int r=1; r<V+1; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<V+1; c++) {
				adjMatrix[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//가상의 노드
		for(int c=1; c<V+1; c++) {
			adjMatrix[0][c] = water[c];
		}
		
		System.out.println(MST());

	}
	
	public static int MST() {
		int total = 0; //비용
		cost = new int[V+1]; //비용배열 초기화
		for(int i=1; i<V+1; i++) {
			cost[i] = Integer.MAX_VALUE;
		}
		
		//mst에 사용할 pq
		PriorityQueue<Node> pq = new PriorityQueue<>();

		//MST
		boolean[] visit = new boolean[V+1];
		int cnt = 0;
		pq.offer(new Node(0, cost[0]));
		while(!pq.isEmpty()) {
			Node e = pq.poll();
			if(!visit[e.v]) {
				visit[e.v] = true;
				for(int v=1; v<V+1; v++) {
					if(!visit[v] && adjMatrix[e.v][v] != 0 && cost[v]>adjMatrix[e.v][v]) {
						cost[v] =  adjMatrix[e.v][v];
						pq.offer(new Node(v, cost[v]));
					}
				}
				if(++cnt == V) {
					break;
				}
			}
		}
		
		//비용체크
		for(int i=0; i<V+1; i++) {
			total += cost[i];
		}
		return total;
	}
	public static class Node implements Comparable<Node>{
		int v;
		int w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node e) {
			return this.w - e.w;
		}
		
	}
}