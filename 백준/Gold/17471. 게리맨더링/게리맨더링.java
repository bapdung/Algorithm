import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int V;
	public static int[] people;
	public static List<Integer>[] adjList;
	public static boolean[] choosed;
	public static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		V = Integer.parseInt(br.readLine());
		people = new int[V+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<V+1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		adjList = new ArrayList[V+1]; //인접리스트
		for(int i=1; i<V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j=0; j<num; j++) {
				int v2 = Integer.parseInt(st.nextToken());
				adjList[i].add(v2);
			}
		}
		choosed = new boolean[V+1];
		부분집합(1);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
		
		
		
		
		
	}
	public static void 부분집합(int cnt) {
		if(cnt == V+1) {
			//System.out.println(Arrays.toString(choosed));
			makeGroup();
			return;
		}
		choosed[cnt] = true;
		부분집합(cnt+1);
		choosed[cnt] = false;
		부분집합(cnt+1);
	}
	public static void makeGroup() {
		int num1=0;
		int num2=0;
		int sum1=0;
		int sum2=0;
		List<Integer> group1 = new ArrayList<>();
		List<Integer> group2 = new ArrayList<>();
		
		for(int i=1; i<V+1; i++) {
			if(choosed[i]) {
				group1.add(i);
				num1++;
				sum1 += people[i];
			}else {
				group2.add(i);
				num2++;
				sum2 += people[i];
			}
		}
		
		if(num1==0 || num2 == 0) {
			return;
		}
//		System.out.println(group1.toString());
//		System.out.println(group2.toString());
//		System.out.println(sum1);
//		System.out.println(sum2);
//		System.out.println();
		if(check(group1) && check(group2)) {
			min = Math.min(Math.abs(sum1 - sum2), min);
		}
	}
	public static boolean check(List<Integer> group) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[V+1];
		visit[group.get(0)] = true;
		q.offer(group.get(0));
		int cnt = 0;
		while(!q.isEmpty()) {
			int v1 = q.poll();
			cnt++;
			for(int v2 : adjList[v1]) {
				if(group.contains(v2) && !visit[v2]) {
					q.offer(v2);
					visit[v2]= true;
				}
			}
		}
		if(cnt == group.size()) return true;
		else return false;
	}
	
	
}