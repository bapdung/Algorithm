import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 서지흔
 * @date 24/03/26
 * @link https://www.acmicpc.net/problem/4485
 * @keyword_solution 
 *  - 다익스트라 최단경로
 *  - 2차원 배열이므로 R*N + C 와 같이 1차원으로 변형 후 다익스트라 계산
 * 
 * @input - 동굴의 크기를 나타내는 정수 N이 주어짐 (2 ≤ N ≤ 125) - N = 0인 입력이 주어지면 전체 입력이 종료
 * 
 * @output
 * @time_complex
 * @perf 14700kb, 124ms
 */
public class Main {
	public static int N;
	public static int[][] arr;
	public static int[] dr = {1,-1,0,0};
	public static int[] dc = {0,0,1,-1};
	public static int min;
	public static List<Node>[] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) {
				System.out.println(sb);
				break;
			}
			arr = new int[N][N];
			for(int r=0 ; r<N ; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			adjList = new ArrayList[N*N];
			for(int i=0; i<N*N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for(int r=0 ; r<N ; r++) {
				for(int c=0; c<N; c++) {
					int cur = r*N + c;
					for(int d=0; d<4; d++) {
						int cr = r+ dr[d];
						int cc = c+ dc[d];
						if(cr>=0 && cr<N && cc>=0 && cc<N ) {
							int ccur = cr*N + cc;
							adjList[cur].add(new Node(ccur, arr[cr][cc]));
						}
					}
				}
			}
			
			int[] dist = new int[N*N];
			for(int i=1; i<dist.length; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			dist[0] = arr[0][0];
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			boolean[] visit = new boolean[N*N];
			pq.offer(new Node(0,dist[0]));
			int sum = arr[0][0];
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				visit[node.v] = true;
				for(Node cur : adjList[node.v]) {
					if(!visit[cur.v] && dist[cur.v] > dist[node.v] + cur.w) {
						dist[cur.v] = dist[node.v] + cur.w;
						pq.offer(new Node(cur.v, dist[cur.v]));
					}
				}
			}

			sb.append("Problem " + tc++ + ": " + dist[N*N - 1]+"\n");
		}
	
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
			return this.w - e.w;
		}

		@Override
		public String toString() {
			return "Node [r=" + v/N + "c=" + v%N + ", w=" + w + "]";
		}
		
	}
	

}