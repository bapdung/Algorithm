import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int temp;
	public static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상 오위 오 오아래 아래 왼아래 왼 왼위
	public static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	public static List<FireBall>[][] visit;
	public static List<FireBall> fb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		temp = Integer.parseInt(st.nextToken());
//        visit = new ArrayList[N][M];
//        for(int r=0 ; r<N; r++) {
//        	for(int c=0; c<N; c++) {
//        		visit[r][c] = new ArrayList<>();
//        	}
//        }
		fb = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); //행
			int c = Integer.parseInt(st.nextToken()); //열
			int m = Integer.parseInt(st.nextToken()); //질량
			int s = Integer.parseInt(st.nextToken()); //속력
			int d = Integer.parseInt(st.nextToken()); //방향
			fb.add(new FireBall(r - 1, c - 1, m, s, d));
		}
		
		//움직여야하는 횟수만큼 움직임
		for (int i = 1; i <= temp; i++) {
//        	System.out.println(fb);
			move();
		}
//        System.out.println("결과" + fb);
		
		//모든 움직임이 끝난 후 파이어볼의 질량 합 구하기
		int total = 0;
		for (int i = 0; i < fb.size(); i++) {
			total += fb.get(i).m;
		}
		System.out.println(total);

	}
	
	//이동함수
	public static void move() {
		//파이어볼 담을 배열
		visit = new ArrayList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				visit[r][c] = new ArrayList<>();
			}
		}
		
		//기존 파이어볼 개수만큼 행, 열 이동 후 visit 배열에 저장
		for (int i = 0; i < fb.size(); i++) {
			int r = (fb.get(i).r + N + (fb.get(i).s % N) * dr[fb.get(i).d]) % N;
			int c = (fb.get(i).c + N + (fb.get(i).s % N) * dc[fb.get(i).d]) % N;
			visit[r][c].add(new FireBall(r, c, fb.get(i).m, fb.get(i).s, fb.get(i).d));
		}
		
		//기존 파이어볼 리스트 초기화
		fb.clear();
		
		//visit 배열 사이즈 만큼 반복
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				//파이어볼이 두개이상일때
				if (visit[r][c].size() >= 2) {
					int m = 0; //질량 총합
					int s = 0; //속력 총합
					int d = (visit[r][c].get(0).d) % 2; //첫번째 파이어볼 방향 홀짝여부
					boolean flag = true;
					for (int i = 0; i < visit[r][c].size(); i++) {
						m += visit[r][c].get(i).m;
						s += visit[r][c].get(i).s;
						if ((visit[r][c].get(i).d) % 2 != d) { // 홀짝 다른 경우
							flag = false;
						}
					}
					//만약 질량의 합이 4이하인 경우 질량 0되므로 continue;
					if (m / 5 == 0) {
						continue;
					} else if (flag) { //모두 다 홀이거나 짝일때
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 0));
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 2));
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 4));
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 6));
					} else {
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 1));
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 3));
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 5));
						fb.add(new FireBall(r, c, m / 5, s / visit[r][c].size(), 7));
					}
				//파이어볼이 하나만 있을 때
				} else if (visit[r][c].size() == 1) {
					fb.add(visit[r][c].get(0));
				}
			}
		}
	}

	public static class FireBall {
		int r;
		int c;
		int m; // 질량
		int s; // 속력
		int d; // 방향

		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "FireBall [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}
}