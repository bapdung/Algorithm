import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int fix = Integer.parseInt(st.nextToken());
		int[][] vertex = new int[V+1][2];
		
		//부모 배열 초기화
		parents = new int[V+1];
		for(int i=1; i<V+1; i++) {
			parents[i] = -1;
		}
		
		for(int i=1; i<V+1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			vertex[i][0] = r;
			vertex[i][1] = c;
		}
		
		//이미 연결된 간선 r,c 정보
		int[][] already = new int[fix][2];
		for(int i=0; i<fix; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			if(v1<v2) {
				already[i][0] = v1;
				already[i][1] = v2;
			}
			else {
				already[i][0] = v2;
				already[i][1] = v1;
			}
		}
		
		//간선 정보 담는 배열
		int E = V * (V-1) / 2;
		Node[] edges = new Node[E];

		int idx = 0;
		for(int i=1; i<V; i++) {
			for(int j=i+1; j<=V; j++) {
				int r1 = vertex[i][0];
				int c1 = vertex[i][1];
				int r2 = vertex[j][0];
				int c2 = vertex[j][1];
				double dist = distance(r1,c1, r2, c2);
				edges[idx++] = new Node(i, j, dist);
			}
		}
		Arrays.sort(edges); //간선 가중치 낮은 순으로 정렬
		
		//이미 연결된 간선 유니온파인드 시키기
		int cnt = 0;
		for(int i=0; i<fix; i++) {
			if(union(already[i][0], already[i][1])) {
				cnt++;
			}
		}
		
		//고정된 간선 연결했을 때 이미 모든 정점 연결된 경우
		if(cnt >= V-1) {
			System.out.printf("%.2f", 0);
			System.exit(0);
		}
		
		//설치비용
		double total = 0;
		//최소스패닝 트리 찾기
		for(int i=0; i<E; i++) {
			Node e = edges[i];
			if(union(e.v1, e.v2)) {
				total += e.w;
				if(++cnt == V-1) {
					break;
				}
			}
		}

		System.out.printf("%.2f",total);
		
		
		
		
				
	}
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP == bP) return false;
		parents[aP] += parents[bP];
		parents[bP] = aP;
		return true;
	}
	public static int find(int a) {
		if(parents[a] <0) {
			return a;
		}
		else return parents[a] = find(parents[a]);
	}
	public static double distance(int r1, int c1, int r2, int c2) {
		double dist= Math.sqrt(Math.pow(r1-r2, 2) + Math.pow(c1-c2, 2));
		return dist;
	}
	
	public static class Node implements Comparable<Node>{
		int v1;
		int v2;
		double w;
		
		
		public Node(int v1, int v2, double w) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}


		public int compareTo(Node e) {
			return (int) Double.compare(this.w, e.w);
		}
		
	}
}