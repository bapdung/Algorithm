import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int R, C, min;
    public static int[][] arr, curCnt;
    public static boolean[][] check;
    public static int[] dr = {0,0,-1,1};
    public static int[] dc = {-1,1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        curCnt = new int[R][C];
        for (int r = 0; r < R; r++) {
            for(int c=0 ; c<C ; c++) {
                curCnt[r][c] = Integer.MAX_VALUE;
            }
        }
        curCnt[0][0] = 0;

        check = new boolean[R][C];

        min = Integer.MAX_VALUE;

        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for(int c=0 ; c<C ; c++) {
                arr[r][c] = str.charAt(c) - '0';
            }
        }

        // dfs(0,0,0);
        // check[0][0] = true;
        bfs();
        System.out.println(curCnt[R-1][C-1]);
    }
    public static void bfs(){
        Queue<Position> q = new ArrayDeque<>();
        q.add(new Position(0, 0, 0));

        while(!q.isEmpty()){
            Position cur = q.poll();
            for(int d=0; d<4; d++) {
                int cr = cur.r + dr[d];
                int cc = cur.c + dc[d];
                if(cr >=0 && cr<R && cc >=0 && cc < C && curCnt[cr][cc]> cur.cnt + arr[cr][cc]){
                    curCnt[cr][cc] = cur.cnt + arr[cr][cc];
                    q.add(new Position(cr, cc, curCnt[cr][cc]));

                }
            }
        }

    }


    public static void dfs(int r, int c, int cnt){
        if(r == R-1 && c == C-1){
            min = Math.min(min, cnt);
            return;
        }

        if(cnt >= min) return;

        for(int d=0; d<4; d++){
            int cr = r + dr[d];
            int cc = c + dc[d];
            if(cr>=0 && cr<R && cc>=0 && cc<C && !check[cr][cc]){
                if(arr[cr][cc]==1){
                    check[cr][cc] = true;
                    dfs(cr,cc,cnt+1);
                    check[cr][cc] = false;
                } else {
                    check[cr][cc] = true;
                    dfs(cr,cc,cnt);
                    check[cr][cc] = false;
                }
            }
        }
    }

    public static class Position{
        int r;
        int c;
        int cnt;

        public Position(int r, int c, int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}