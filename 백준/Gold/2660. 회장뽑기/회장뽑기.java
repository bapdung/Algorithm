import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a < 0)
				break;
			adjList[a].add(b);
			adjList[b].add(a);
		}
		Person[] rst = new Person[N];

		for (int i = 1; i <= N; i++) {
			rst[i - 1] = new Person(i, bfs(i));
		}
		
		Arrays.sort(rst);
		
		int flag = rst[0].score;
		int cnt = 0;
		for(Person cur : rst) {
			if(cur.score == flag) {
				sb1.append(cur.idx + " ");
				cnt++;
			} else {
				break;
			}
		}
		sb2.append(flag + " " + cnt);
		System.out.println(sb2 + "\n" + sb1);

	}

	public static int bfs(int i) {
		int temp = -1;
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] check = new boolean[N + 1];
		q.offer(i);
		check[i] = true;
		while (!q.isEmpty()) {
			temp++;
			int size = q.size();
			while (size-- > 0) {
				int p = q.poll();
				for (int cur : adjList[p]) {
					if (!check[cur]) {
						q.offer(cur);
						check[cur] = true;
					}
				}
			}

		}
		return temp;
	}

	public static class Person implements Comparable<Person> {
		int idx;
		int score;

		public Person(int idx, int score) {
			super();
			this.idx = idx;
			this.score = score;
		}

		public int compareTo(Person a) {
			if (a.score == this.score) {
				return this.idx - a.idx;
			} else {
				return this.score - a.score;
			}
		}
	}

}