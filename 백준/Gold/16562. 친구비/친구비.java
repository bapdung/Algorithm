import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static int E;
	public static int money;
	public static int[] parents;
	public static int[] cost;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		money = Integer.parseInt(st.nextToken());
		parents = new int[V+1];
		cost = new int[V+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<V+1; i++) {
			parents[i] = i;
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			if(union(v1,v2)) {
			}
		}
		
//		System.out.println(Arrays.toString(parents));
		List<Integer>[] friends = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) {
			friends[i] = new ArrayList<>();
		}
		for(int i=1; i<V+1; i++) {
			friends[find(i)].add(i);
		}
		int total = 0;
		for(int cur=1; cur<V+1; cur++) {
//			System.out.println(friends[cur].size());
			if(!friends[cur].isEmpty()) {
				int min = Integer.MAX_VALUE;
				for(int i : friends[cur]) {
					min = Math.min(cost[i], min);
				}
				total += min;
			}
		}
		
		if(total <= money) {
			System.out.println(total);
		}else {
			System.out.println("Oh no");
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
	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		}else {
			return parents[a] = find(parents[a]);
		}
	}
	
}