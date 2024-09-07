import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N, rst;
    public static int[][] arr;
    public static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        selected = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        rst = 0;
        부분집합(0);
        System.out.println(rst);
    }

    public static void 부분집합(int cnt) {
        if (cnt == N) {
            checkSubset();
            return;
        }
        selected[cnt] = true;
        부분집합(cnt + 1);


        selected[cnt] = false;
        부분집합(cnt + 1);
    }

    public static void checkSubset() {
        int curDay = 0;
        int sumProfit = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                int endDay = i + arr[i][0];

                if (endDay > N) {
                    return;
                }

                if (curDay <= i) {
                    curDay = endDay;
                    sumProfit += arr[i][1];
                } else {
                    return;
                }
            }
        }
        rst = Math.max(rst, sumProfit);
    }
}