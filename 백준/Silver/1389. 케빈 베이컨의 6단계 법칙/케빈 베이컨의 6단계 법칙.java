import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] temp;
    public static List<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        temp = new int[N+1];
        adjList = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        int min = Integer.MAX_VALUE;
        int idx = 0;

        for(int i=N; i>=1; i--){
            int cnt = bfs(i);
//            System.out.println(cnt);
            if(min >= cnt){
                idx = i;
                min = cnt;
            }
        }
        System.out.println(idx);


    }
    public static int bfs(int i){
        int cnt = 0;
        int bacon = 0;
        boolean[] check = new boolean[N+1];
        check[i]= true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(i);
        while(!q.isEmpty()){
            int size = q.size();
            cnt++;
            while(size-->0) {
                int cur = q.poll();
                bacon += cnt;
                for (Integer j : adjList[cur]) {
                    if (!check[j]) {
                        check[j] = true;
                        q.offer(j);
                    }
                }
            }
        }

        return bacon;
    }
}