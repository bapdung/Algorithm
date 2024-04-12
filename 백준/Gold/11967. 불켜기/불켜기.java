import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[][] check, rst;
    static HashSet<Light>[][] set;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        check = new boolean[N][N];
        set = new HashSet[N][N];
        rst = new boolean[N][N];
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                set[r][c] = new HashSet<>();
            }
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            set[x][y].add(new Light(a,b));
        }
        int cnt = bfs();
        System.out.println(cnt);
    }
    public static int bfs() {
    	check[0][0] = true;
    	rst[0][0] = true;
        Queue<Light> q = new ArrayDeque<>();
        HashSet<Light> curSet = (HashSet<Light>) set[0][0].clone();

        curSet.add(new Light(0,0));
//        int cnt = 1;
        q.offer(new Light(0,0));
        while(!q.isEmpty()) {
            Light cur = q.poll();
            for(int d=0; d<4; d++) {
                int cr = cur.r + dr[d];
                int cc = cur.c + dc[d];
                if(cr>=0 && cr<N && cc>=0 && cc<N && curSet.contains(new Light(cr,cc))) {
                	set[cr][cc].removeAll(curSet);
                	if(set[cr][cc].size()>0) {
                		check = new boolean[N][N];
                		curSet.addAll(set[cr][cc]);
                		check[cr][cc] = true;
                		q.clear();
                		q.offer(new Light(cr,cc));
                		break;
                	} else if(!check[cr][cc]){
                		check[cr][cc] = true;
                		q.offer(new Light(cr,cc));
                	}
                }
            }
        }
       
        return curSet.size();
        
    }
    public static class Light{
        int r;
        int c;
        public Light(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + c;
            result = prime * result + r;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Light other = (Light) obj;
            if (c != other.c)
                return false;
            if (r != other.r)
                return false;
            return true;
        }
        @Override
        public String toString() {
            return "Light [r=" + r + ", c=" + c + "]";
        }
        
    }
}