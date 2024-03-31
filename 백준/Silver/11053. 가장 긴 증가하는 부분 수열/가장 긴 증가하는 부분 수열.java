import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		arr[0] = Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			arr[i] = Integer.MAX_VALUE;
		}
		int max = 1;
		for(int i=1; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			int idx = bs(arr, n);
			if(idx<0) {
				arr[-(idx) - 1] = n;
				max = Math.max(max, -(idx));
			}
		}
		System.out.println(max);
		
		
	
	}
	public static int bs(int[] arr, int n) {
		int start = 0;
		int end = N-1;
		while(start<=end) {
			int mid = (start + end) / 2;
			if(arr[mid]<n) {
				start=mid+1;
			} else if(arr[mid]>n){
				end = mid -1;
			} else {
				return mid;
			}
		}
		return -(start+1);
		
		
	}
}