import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, S;
	public static boolean[] check;
	public static List<Integer>[] adjList;
	public static boolean flag = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjList[v1].add(v2);
		}
		check = new boolean[N + 1];

		S = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S; i++) {
			int n = Integer.parseInt(st.nextToken());
			check[n] = true;
		}

		dfs(1);
		if (flag) {
			System.out.println("Yes");
		} else
			System.out.println("yes");
	}

	public static void dfs(int n) {
		if (check[n]) {
			return;
		}
		if (adjList[n].size() == 0) {
			flag = false;
			return;
		}
		for (Integer v : adjList[n]) {
			dfs(v);
		}
	}
}