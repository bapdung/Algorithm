import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int dr[] = {0,0,1,-1,0,0};
	public static int dc[] = {0,0,0,0,1,-1};
	public static int df[] = {1,-1,0,0,0,0};
	public static int se[][] = new int[2][3];
	public static char[][][] build;
	public static int R;
	public static int C;
	public static int floor;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			floor = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(floor == 0 && R==0 && C==0) break;
			build= new char[floor][R][C];
			for(int f=0; f<floor ; f++) {
				for(int r=0; r<R; r++) {
					String str = br.readLine();
					for(int c=0; c<C ;c++) {
						build[f][r][c] = str.charAt(c);
						if(build[f][r][c] == 'S') {
							se[0][0] = f;
							se[0][1] = r;
							se[0][2] = c;
						} else if(build[f][r][c] == 'E') {
							se[1][0] = f;
							se[1][1] = r;
							se[1][2] = c;
						}
					}
				}
				br.readLine();
			}
			int rst = bfs();
			if(rst == 0) {
				sb.append("Trapped!" + "\n");
			} else {
				sb.append("Escaped in " + rst + " minute(s).\n");
			}
			
		}
		System.out.println(sb);
	}
	public static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visit = new boolean[floor][R][C];
		q.offer(new int[] {se[0][0], se[0][1],se[0][2]});
		visit[se[0][0]][se[0][1]][se[0][2]] = true;
		int min = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size ; i++) {
				int[] pin = q.poll();
				for(int d=0; d<6; d++) {
					int cf = pin[0] + df[d];
					int cr = pin[1] + dr[d];
					int cc = pin[2] + dc[d];
					if(cf>=0 && cf<floor && cr>=0 && cr<R && cc>=0 && cc<C && !visit[cf][cr][cc]) {
						if(build[cf][cr][cc] == '.') {
							visit[cf][cr][cc] = true;
							q.offer(new int[] {cf,cr,cc});
						}
						else if(build[cf][cr][cc] == 'E') {
							return ++min;
						}
					}
				}
			}
			min++;
		}
		return 0;
		
	}
	
}