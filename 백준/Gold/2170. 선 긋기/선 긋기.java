import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		Position[] arr = new Position[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[i] = new Position(r, c);
		}

		// r행 기준 정렬
		Arrays.sort(arr, (o1, o2) -> {
			if (o1.r == o2.r) {
				return o1.c - o2.c;
			}
			return o1.r - o2.r;
		});

		int total = 0;
		int low = arr[0].r;
		int high = arr[0].c;
		for (int i = 1; i < N; i++) {
			// System.out.println(total);
			if (high >= arr[i].r) {
				if (arr[i].c > high) {
					high = arr[i].c;
				}
			} else {
				total += high - low;
				low = arr[i].r;
				high = arr[i].c;
			}
		}
		total += high - low;
		System.out.println(total);

	}

	public static class Position {
		int r;
		int c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}