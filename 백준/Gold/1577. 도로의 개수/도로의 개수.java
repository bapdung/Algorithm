import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[][] arr = new long[N + 1][M + 1];

		boolean[][] horizontal = new boolean[N + 1][M]; // 가로선
		boolean[][] vertical = new boolean[N][M + 1]; // 세로선
		int temp = Integer.parseInt(br.readLine());
		for (int i = 0; i < temp; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			if (c1 == c2) {
				vertical[Math.min(r1, r2)][c1] = true;
			} else {
				horizontal[r1][Math.min(c1, c2)] = true;
			}

		}

		for (int r = 1; r < N + 1; r++) {
			if (vertical[r - 1][0]) {
				break;
			}
			arr[r][0] = 1;
		}
		for (int c = 1; c < M + 1; c++) {
			if (horizontal[0][c - 1]) {
				break;
			}
			arr[0][c] = 1;
		}


		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				int r1 = r - 1;
				int c1 = c - 1;

				arr[r][c] = arr[r][c1] + arr[r1][c]; //가로 + 세로
				if (horizontal[r][c1]) {
					arr[r][c] -= arr[r][c1];
				}
				if (vertical[r1][c]) {
					arr[r][c] -= arr[r1][c];
				}

			}
		}

		System.out.println(arr[N][M]);

	}
}