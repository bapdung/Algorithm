import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.lang.Comparable;

public class Main {
    static int N;
    static Data arr[];
    public static void main(String[] args) throws Exception {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        N = Integer.parseInt(br.readLine());
        arr = new Data[N];
        for (int i=0; i<N; i++) {
            int v = Integer.parseInt(br.readLine());
            arr[i] = new Data(i, v);
        }
        Arrays.sort(arr);

        // 변경된 인덱스 중 가장 큰 값 추출
        int max = 0;
        for (int i=0; i<N; i++) {
            int gap = arr[i].idx - i;
            max = Math.max(max, gap);
        }
        System.out.println(max+1);
    }
    public static class Data implements Comparable<Data> {
        int idx;
        int v;
        Data (int i, int v) {
            this.idx = i;
            this.v = v;
        }
        @Override
        public int compareTo(Data o) {
            return this.v - o.v;
        }
    }
}