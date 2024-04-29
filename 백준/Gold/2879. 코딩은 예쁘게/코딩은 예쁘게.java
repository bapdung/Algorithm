import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[] arr;
	public static int[] rst;
	public static int[] now;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		rst = new int[N];
		now = new int[N]; // 1:추가, -1:삭제, 0:변화x
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] check = new boolean[N];
		int chkCnt = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<N; i++) {
			rst[i] = Integer.parseInt(st.nextToken());
			now[i] = rst[i]-arr[i];
			if(now[i] == 0) {
				check[i] = true;
				chkCnt++;
			}
		}
		if(chkCnt == N) {
			System.out.println(0);
			System.exit(0);
		}
		int sign = 1;
		int cnt = 0;
		while(true) {
//			System.out.println(Arrays.toString(now));
			int idx = 0;
			cnt++;
			for(int i = 0; i<N; i++) {
				if(now[i] > 0) {
					sign = 1;
					idx = i;
					break;
				} else if(now[i]<0) {
					sign = -1;
					idx = i;
					break;
				}
			}
			for(int i = idx; i<N; i++) {
				if(sign * now[i] > 0) {
					now[i] -= sign;
					if(now[i] == 0) {
						if(++chkCnt == N) {
							System.out.println(cnt);
							return;
						}
					}
				}else break;
			}
		}
	}
}