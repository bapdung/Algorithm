import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
//이분탐색을 이용해 최소거리의 최댓값 찾기
//초기설정은 거리의 중간값
//그 값 기준으로 탐색하면서 제거해야할 돌맹이 개수 세기(제거하지 않는다면 거리 idx 설정)
public class Main {
	public static int D;
	public static int N;
	public static int M;
	public static int[] dist;
	public static List<Integer> rst = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+2];
		for(int i=1; i<N+1; i++) {
			dist[i] = Integer.parseInt(br.readLine());
		}
		dist[N+1] = D;
		Arrays.sort(dist);
//		System.out.println(Arrays.toString(dist));
		
		int firmin = 0;
//		for(int i=1; i<N+2; i++) {
//			firmin = Math.min((dist[i] - dist[i-1]), firmin);
//		}
//		
//		//제거할수 있는 돌맹이가 0개일때
//		if(M==0) {
//			System.out.println(firmin);
//			System.exit(0);
//		}
		
		int start = 0;
		int end = D;
		
		while(start<=end) {
			int idx = 0;
			int cnt = 0;
			int mid = (end + start) / 2;
//			System.out.println("===");
//			System.out.println(mid + " " + start + " " + end);
//			System.out.println(mid);
			for(int i=1; i<N+1; i++) {
				if(cnt>M) break;
				if(dist[i] - dist[idx] < mid) {
					cnt++;
				} else {
					idx = i;
				}
			}
//			System.out.println(cnt +" " + idx + " " +N+1);
//			System.out.println(dist[N+1] + " " + dist[idx]);
			if(cnt>M) {
				end = mid - 1;
			} else {
				firmin = mid;
				start = mid+1;
			}
//			System.out.println(start + " " + end);
		}
		System.out.println(firmin);
	}
}