import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//짧은 간선 빼주기 쉽게 하기 위해서 인접행렬로 두기 (+밀도 그래프)
		int[][] adjMatrix = new int[V+1][V+1];
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjMatrix[v1][v2] = i;
			adjMatrix[v2][v1] = i;
		}
		
		//턴만큼 MST 구하기
		for (int k = 0; k < K; k++) {
			int[] dist = new int[V + 1];
			int inf =10000 * 1000 * 999 / 2; //최대 간선 길이
			Arrays.fill(dist, inf); //초기화
			dist[1] = 0;

			PriorityQueue<Node> pq = new PriorityQueue<>();
			boolean[] visit = new boolean[V + 1]; //방문 트리 관리
			pq.offer(new Node(1, dist[1])); //첫번째값 초기화
			while (!pq.isEmpty()) {
				Node v1 = pq.poll();
				if (!visit[v1.v1]) {
					visit[v1.v1] = true;
					for(int v2=1; v2<V+1; v2++) {
						if (!visit[v2] && adjMatrix[v1.v1][v2] != 0 && adjMatrix[v1.v1][v2] < dist[v2]) {
							dist[v2] = adjMatrix[v1.v1][v2];
							pq.offer(new Node(v2, dist[v2]));
						}
					}
				}
			}
			int score = 0;
			int min = E+1;
			int idx = 0;
			for(int i=2; i<V+1; i++) {
				if(dist[i] != inf) {
					score += dist[i];
					if(min>dist[i]) {
						idx = i;
						min = dist[i];
					}
				}
				else {
					score = 0;
					break;
				}
			}
			//가지치기 mst 0일떄
			if(score == 0) {
				for(int j=k ; j<K; j++) {
					sb.append(0 + " ");
				}
				break;
			}
			
			//0아닐때
			sb.append(score + " ");
			
			//짧은 간선 없애기
			for(int v2=1; v2<V+1; v2++) {
				if(adjMatrix[idx][v2] == min) {
					adjMatrix[idx][v2] = 0;
					adjMatrix[v2][idx] = 0;
					break;
				}
			}
			
		}
		System.out.println(sb);
	}

	public static class Node implements Comparable<Node> {
		int v1;
		int v2;
		int weight;

		public Node(int from, int weight) {
			super();
			this.v1 = from;
			this.weight = weight;
		}
		public Node(int v1, int v2, int weight) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node e) {
			return this.weight - e.weight;
		}

	}
}