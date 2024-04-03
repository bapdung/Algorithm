import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int R, C;
    public static char[][] arr;
    public static boolean[][] check;
    public static int[] dr = {0,0,1,-1}; //우좌하상
    public static int[] dc = {1,-1,0,0};
    public static Position red,blue,hole;
    public static Position curRed, curBlue;
    public static boolean flagR, flagB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        check = new boolean[R][C];
        for(int r=0; r<R; r++) {
            String str = br.readLine();
            for(int c=0; c<C; c++) {
                arr[r][c] = str.charAt(c);
                if(arr[r][c] == '#') {
                    check[r][c] = true;
                } else if(arr[r][c] =='B') {
                    blue = new Position(r,c);
                } else if(arr[r][c] == 'R') {
                    red = new Position(r,c);
                } else if(arr[r][c] == 'O') {
                    hole = new Position(r,c);
                }
            }
        }
        int temp = bfs();
        System.out.println(temp);
    }
    public static int bfs() {
//    	System.out.println("hole : " + hole.r + " " + hole.c);
        Queue<RedBlue> q = new ArrayDeque<>();
        boolean[][][][] visit = new boolean[R][C][R][C];
        visit[red.r][red.c][blue.r][blue.c] = true;
        q.add(new RedBlue(red, blue));
        int cnt = 0;
        while(!q.isEmpty()) {
            if(cnt++==10) break;
            int size = q.size();
//            System.out.println(q);
            while(size-->0) {
                RedBlue rb = q.poll();
                Position curRed = rb.red;
                Position curBlue = rb.blue;
                
                for(int d=0; d<4; d++) { //우좌하상
                	flagR = false;
                    flagB = false;
                	Position red = move(d, curRed, flagR, 'R');
                    Position blue = move(d, curBlue, flagB, 'B');
                    //구슬은 같은 위치에 있을 수 없음
                	if(d==0 && curRed.r == curBlue.r) {//우
                		if(curRed.c<curBlue.c && red.c == blue.c) {
                			red.c = red.c - 1;
                		}else if(curRed.c>curBlue.c && red.c == blue.c) {
                			blue.c = blue.c - 1;
                		}
                	} else if(d==1 && curRed.r == curBlue.r) {//좌
                		if(curRed.c<curBlue.c && red.c == blue.c) {
                			blue.c = blue.c + 1;
                		}else if(curRed.c>curBlue.c && red.c == blue.c) {
                			red.c = red.c + 1;
                		}
                	} else if(d==2 && curRed.c == curBlue.c) {//하
                		if(curRed.r<curBlue.r && red.r == blue.r) {
                			red.r = red.r - 1;
                		}else if(curRed.r>curBlue.r && red.r==blue.r) {
                			blue.r = blue.r - 1;
                		}
                	} else if(d==3 && curRed.c == curBlue.c) {//상
                		if(curRed.r<curBlue.r && red.r == blue.r) {
                			blue.r = blue.r + 1;
                		}else if(curRed.r>curBlue.r && red.r==blue.r) {
                			red.r = red.r + 1;
                		}
                	}
//                	if(d==3 && !flagR && flagB) {
//                		System.out.println(red.r + " " + red.c + " " + blue.r + " " + blue.c);
//                	}
                    //빨간구슬 안빠지고 파란구슬도 안 빠졌을때
                    if(!flagR && !flagB && !visit[red.r][red.c][blue.r][blue.c]) {
                    	visit[red.r][red.c][blue.r][blue.c] = true;
                    	q.offer(new RedBlue(red, blue));
                    } 
                    //빨간구슬 빠지고 파란구슬 안 빠졌을 때
                    else if(flagR && !flagB) {
                    	return cnt;
                    }
                }
            }
        }
        return -1;
    }
    public static Position move(int d, Position cur, boolean flag, char color) {
    	
    	Position rst = null;
    	int l = 1;
        while(true) {
        	int row = cur.r + l*dr[d];
        	int col = cur.c + (l++)*dc[d];
        	if(row>=0 && row<R && col>=0 && col<C && !check[row][col]) {
        		if(arr[row][col] == 'O') {
        			flag = true;
        		}
        	} else {
        		row = row - dr[d];
        		col = col- dc[d];
        		rst = new Position(row, col);
        		break;
        	}
        }
        if(color == 'R') {
        	flagR = flag;
        } else {
        	flagB = flag;
        }
        return rst;
    }
    public static class RedBlue{
        Position red;
        Position blue;
        public RedBlue(Position red, Position blue) {
            super();
            this.red = red;
            this.blue = blue;
        }
		@Override
		public String toString() {
			return "RedBlue [red=" + red.r + " "+red.c + ", blue=" + blue.r + " " + blue.c + "]";
		}
        
        
    }
    public static class Position{
        int r;
        int c;
        public Position(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
        
    }
}