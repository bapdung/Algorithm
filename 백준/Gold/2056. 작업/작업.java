import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] left, times;
    public static List<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        left = new int[N+1];
        times = new int[N+1];
        adjList = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            adjList[i] = new ArrayList<>();
        }
        times = new int[N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;
            int cnt = Integer.parseInt(st.nextToken());
            for(int c=0;c<cnt;c++){
                int p = Integer.parseInt(st.nextToken());
                left[p]++;
                adjList[i].add(p);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        int[] rst = new int[N+1];
        for(int i=1; i<=N; i++){
            rst[i] = times[i];

            if(left[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int cur = q.poll();

            for(int a : adjList[cur]){
                left[a]--;
                rst[a] = Math.max(rst[a], rst[cur] + times[a]);

                if(left[a] == 0){
                    q.add(a);
                }
            }
        }

        int max = 0;
        for(int i=1; i<=N; i++){
            max = Math.max(max, rst[i]);
        }

        System.out.println(max);
    }
}