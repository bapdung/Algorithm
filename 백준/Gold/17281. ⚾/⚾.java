import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static boolean[] visit = new boolean[10];
	public static int[] choosed = new int[9];
	public static int[][] people;
	public static int N;
	public static int score;
	public static int maxScore;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N][10];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<10 ; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		choosed[3] = 1;
		visit[1] = true;
		maxScore = 0;
		순열(0);
		System.out.println(maxScore);
	}
	public static void 순열(int cnt) {
		if(cnt == 9) {
			//System.out.println(Arrays.toString(choosed));
			야구();
			return;
		}
		
		for(int i=1; i<=9; i++) {
			if(!visit[i]) {
				choosed[cnt] = i;
				visit[i] = true;
				if(cnt == 2) 순열(cnt+2);
				else 순열(cnt+1);
				visit[i] = false;
			}
		}
	}
	public static void 야구() {
		int turn = 0;
		score = 0;
		int[] ground = new int[3];
		for(int n=0; n<N; n++) {
			int out = 0;
			while(out<3) {
				int now = choosed[turn]; //현재 이닝 타자번호
				turn = (turn + 1) % 9; //다음 타자 turn 갱신
				int hit = people[n][now];
				switch(hit) {
				case 1:
				case 2:
				case 3:
				case 4:
					move(ground, hit);
					break;
				case 0:
					out+= 1;
					break;
					
				}
			} //이닝 끝
			Arrays.fill(ground, 0);
		} //모든 이닝 끝
		maxScore = Math.max(score, maxScore);
	}
	public static void move(int[] ground, int hit) {
		if(hit != 4) {
			for(int i=2; i>=0; i--) {
				if(i>=3-hit) {
					score += ground[i];
					ground[i] = 0;
				}
				else {
					ground[i+hit] = ground[i];
					ground[i] = 0;
				}			
			}
			ground[hit-1] = 1;
		}
		else {
			for(int i=0; i<3; i++) {
				score += ground[i];
				ground[i] = 0;
			}
			score+=1;
		}
		return;
	}
}