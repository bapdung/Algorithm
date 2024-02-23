import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int dish = Integer.parseInt(st.nextToken()); //접시 개수
		int fish = Integer.parseInt(st.nextToken()); //스시 종류
		int num = Integer.parseInt(st.nextToken()); //연속으로 먹는 개수
		int coupon = Integer.parseInt(st.nextToken()); //쿠폰
		
		int[] dishes = new int[dish];
		
		for(int i=0; i<dish; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
		int[] visit = new int[fish+1];
		int cnt = 0;
		for(int i=0; i<num; i++) {
			int f = dishes[i];
			if(visit[f]==0) {
				cnt++;
			}
			visit[f]++;
			
		}
		if(visit[coupon] == 0 && cnt == num) {
			System.out.println(num+1);
			System.exit(0);
		}
		
		int start = 0;
		int end = num-1;
		while(end < dish-1 + num -1) {
			//System.out.println(start + " " + end);
			visit[dishes[start]]--;
			if(visit[dishes[start++]] == 0) {
				cnt--;
			}
			if(visit[dishes[++end % dish]] == 0) {
				cnt++;
			}
			visit[dishes[end % dish]]++;
			//System.out.println(cnt);
			if(visit[coupon] == 0) {
				max = Math.max(cnt+1, max);
			}
			else {
				max = Math.max(cnt, max);
			}
		}
		//System.out.println(start + " "  + end);
		System.out.println(max);
		
		
	}
}