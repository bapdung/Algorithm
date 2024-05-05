import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		long[] rst = new long[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
		long min = Long.MAX_VALUE;
		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;
			while (left < right) {
				long sum = arr[i] + arr[left] + arr[right];
				if (sum > 0) {
					if (sum < min) {
						min = sum;
						rst[0] = arr[i];
						rst[1] = arr[left];
						rst[2] = arr[right];
					}
					right -= 1;
				} else if (sum < 0) {
					if (-sum < min) {
						min = -sum;
						rst[0] = arr[i];
						rst[1] = arr[left];
						rst[2] = arr[right];
					}
					left += 1;
				} else {
					System.out.println(arr[i] + " " + arr[left] + " " + arr[right]);
					System.exit(0);
				}
			}

		}
		System.out.println(rst[0] + " " + rst[1] + " " + rst[2]);
	}
}