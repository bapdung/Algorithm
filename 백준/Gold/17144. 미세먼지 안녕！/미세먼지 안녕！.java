import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int R,C,T;
	public static int[][] arr, air;
	public static int[] dr = {-1,0,1,0};
	public static int[] dc = {0,1,0,-1};
	public static Queue<Dust> dust = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		air = new int[2][2];
		int idx = 0;
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if(arr[r][c] == -1) {
					air[idx][0] = r;
					air[idx++][1] = c;
				} else if(arr[r][c] != 0) {
					dust.add(new Dust(r,c,arr[r][c]));
				}
				
			}
		}
		int cnt = 0;
		for(int t=1; t<=T; t++) {
			
			확산();
			정화();
			//q배열에 먼지 추가
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(arr[r][c]>0) {
						if(t==T) {
							cnt+= arr[r][c];
						}
						dust.add(new Dust(r,c,arr[r][c]));
					}
				}
			}
		}
		System.out.println(cnt);
		
	}
	public static void 확산() {
		while(!dust.isEmpty()) {
			Dust cur = dust.poll();
			int cnt = 0;
			int cw = 0;
			for(int d=0; d<4; d++) {
				int cr = cur.r + dr[d];
				int cc = cur.c + dc[d];
				if(cr>=0 && cr<R && cc>=0 && cc<C && arr[cr][cc]!=-1) {
					cw = cur.w / 5;
					arr[cr][cc] += cw;
					cnt++;
				}
			}
			arr[cur.r][cur.c] -= (cw * cnt);
		}
	}
	public static void 정화() {
		//위순환
		int r = air[0][0];
		int c = air[0][1];
		for(int d=0; d<4; d++) {
			int l = 1;
			while(true) {
				r += dr[d];
				c += dc[d];
				if(r>=0 && r<=air[0][0] && c>=0 && c<C) {
					if(arr[r][c] !=-1) {
						if(arr[r-dr[d]][c-dc[d]] != -1) {
							arr[r-dr[d]][c-dc[d]] = arr[r][c];
						}
					} else {
						arr[r-dr[d]][c-dc[d]] = 0;
					}
				} else {
					r = r - dr[d];
					c = c - dc[d];
					break;
				}
			}
		}
		//아래순환
		dr[0] *= -1;
		dr[2] *= -1;
		r = air[1][0];
		c = air[1][1];
		for(int d=0; d<4; d++) {
			int l = 1;
			while(true) {
				r += dr[d];
				c += dc[d];
				if(r>=air[1][0] && r<R && c>=0 && c<C) {
					if(arr[r][c] !=-1) {
						if(arr[r-dr[d]][c-dc[d]] != -1) {
							arr[r-dr[d]][c-dc[d]] = arr[r][c];
						}
					} else {
						arr[r-dr[d]][c-dc[d]] = 0;
					}
				} else {
					r = r - dr[d];
					c = c - dc[d];
					break;
				}
			}
		}
		dr[0] *= -1;
		dr[2] *= -1;
	}
	
	public static class Dust{
		int r;
		int c;
		int w;
		public Dust(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
}