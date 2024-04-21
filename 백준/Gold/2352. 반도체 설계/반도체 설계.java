import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[] arr, lis;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lis = new int[N];
		Arrays.fill(lis, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			int idx = bs(arr[i]);
			lis[-idx-1]=arr[i];
		}
		for(int i=0; i<N; i++) {
			if(lis[i] == Integer.MAX_VALUE) {
				System.out.println(i);
				System.exit(0);
			}
		}
		System.out.println(N);
		
		
	}
	public static int bs(int n) {
		int start = 0;
		int end = N-1;
		while(start<=end) {
			int mid = (start+end)/2;
			if(lis[mid]>n) {
				end = mid-1;
			} else {
				start = mid+1;
			}
		}
		return -start - 1;
	}
}