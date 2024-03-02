import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N; // 땅범위(NxN)
	public static int M; // 초기 나무수
	public static int T; // 시간
	public static List<Tree>[][] trees;
	public static int[][] food;
	public static int[] dr = { 0, 0, 1, -1, 1, 1, -1, -1 };
	public static int[] dc = { 1, -1, 0, 0, 1, -1, -1, 1 };
	public static int[][] winter;
	public static List<Tree> remove = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		trees = new ArrayList[N][N];
		winter = new int[N][N];
		food = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				winter[r][c] = Integer.parseInt(st.nextToken());
				food[r][c] = 5;
				trees[r][c] = new ArrayList<>();
			}
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees[r - 1][c - 1].add(new Tree(age));
		}

		for (int t = 0; t < T; t++) {
			spring();
			fall();
			winter();
		}

		int num = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(!trees[r][c].isEmpty()) num += trees[r][c].size();
			}
		}
		System.out.println(num);

	}

	public static void spring() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!trees[r][c].isEmpty()) {
					// 오름차순
					Collections.sort(trees[r][c], (o1, o2) -> {
						return o1.age - o2.age;
					});
					int newF = 0;
					for (Tree cur : trees[r][c]) {
						if (cur.age <= food[r][c]) {
							food[r][c] -= cur.age;
							cur.age = cur.age + 1;
						} else {
							newF += cur.age / 2;
							remove.add(cur);
						}
					}
//					}
					if (!remove.isEmpty()) {
						for (int i = remove.size() - 1; i >= 0; i--) {
							trees[r][c].remove(remove.get(i));
						}
						remove.clear();
					}
					food[r][c] += newF; // 여름
				}
			}
		}
	}

	public static void fall() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!trees[r][c].isEmpty()) {
					for (Tree cur : trees[r][c]) {
						if (cur.age % 5 == 0) {
							for (int d = 0; d <8; d++) {
								int cr = r + dr[d];
								int cc = c + dc[d];
								if (cr >= 0 && cr < N && cc >= 0 && cc < N) {
									trees[cr][cc].add(new Tree(1));
								}
							}
						}
					}
				}

			}
		}
	}

	public static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				food[r][c] += winter[r][c];
			}
		}
	}

	public static class Tree {
		int age;

		public Tree(int age) {
			super();
			this.age = age;
		}

		@Override
		public String toString() {
			return "Tree [age=" + age + "]";
		}
	}
}