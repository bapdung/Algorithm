import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int R, C, T;
    public static char[][] arr;
    public static int[] dr = {0, 0, -1, 1};
    public static boolean[][] check, fireCheck;
    public static Queue<Node> q, fire;
    public static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            arr = new char[R][C];
            check = new boolean[R][C];

            q = new ArrayDeque<>();
            fire = new ArrayDeque<>();

            for (int r = 0; r < R; r++) {
                String str = br.readLine();
                for (int c = 0; c < C; c++) {
                    if (str.charAt(c) == '@') {
                        q.add(new Node(r, c));
                        arr[r][c] = '.';
                    } else if (str.charAt(c) == '.') { // 빈공간
                        arr[r][c] = '.';

                    } else if (str.charAt(c) == '*') { // 불
                        check[r][c] = true;
                        arr[r][c] = '*';
                        fire.add(new Node(r, c));

                    } else if (str.charAt(c) == '#') { // 벽
                        check[r][c] = true;
                        arr[r][c] = '#';
                    }
                }
            }

            int rst = bfs();
            if (rst == -1) {
                sb.append("IMPOSSIBLE\n");
            } else {
                sb.append(rst + "\n");
            }
        }
        System.out.println(sb);

    }

    public static int bfs() {
        int time = 0;
        while (!q.isEmpty()) {

            // 불 번짐
            int fireSize = fire.size();
            while (fireSize-- > 0) {
                Node f = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int cr = f.r + dr[d];
                    int cc = f.c + dc[d];
                    if (cr >= 0 && cr < R && cc >= 0 && cc < C && !check[cr][cc]) {
                        check[cr][cc] = true;
                        fire.add(new Node(cr, cc));
                    }
                }
            }

            // 이동
            int qSize = q.size();
            while (qSize-- > 0) {
                Node cur = q.poll();
                for (int d = 0; d < 4; d++) {
                    int cr = cur.r + dr[d];
                    int cc = cur.c + dc[d];
                    if (cr >= 0 && cr < R && cc >= 0 && cc < C && !check[cr][cc] && arr[cr][cc] == '.') {
                        q.add(new Node(cr, cc));
                        check[cr][cc] = true;
                    } else if (cr < 0 || cr >= R || cc < 0 || cc >= C) {
                        return time + 1;
                    }
                }
            }
            time++;
        }

        return -1;
    }

    public static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node [r=" + r + ", c=" + c + "]";
        }
    }
}