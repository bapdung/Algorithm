import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] parents;
	public static int V;
	public static int E;
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parents = new int[V+1];
		
		for(int i=1; i<V+1; i++) {
			parents[i] = Integer.parseInt(br.readLine()) * (-1);
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			union(v1,v2);
			int p1 = find(v1);
			int p2 = find(v2);
			if(parents[p2]> parents[p1]) {
				sb.append(Math.abs(parents[p1]) + "\n");
			}else {
				sb.append(Math.abs(parents[p2]) + "\n");
			}
		}
		System.out.println(sb);
	
		
	}
 	public static boolean union(int a, int b) {
 		int aP = find(a);
 		int bP = find(b);
 		if(aP == bP) {
 			return false;
 		}
 		else {
 			parents[aP] += parents[bP];
 			parents[bP] = aP;
 			return true;
 		}
 	}
 	public static int find(int a) {
 		if(parents[a] <0) {
 			return a;
 		}
 		else {
 			return parents[a] = find(parents[a]);
 		}
 	}
}