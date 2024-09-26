import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, root;
    public static List<Integer>[] adjList;
    public static int[] parent;
    public static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        adjList = new ArrayList[N];
        check = new boolean[N];
        for(int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            parent[i] = v;
            if(v == -1) {
                root = i;
                continue;
            }
            adjList[v].add(i);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int prun= Integer.parseInt(br.readLine());

        if(parent[prun] != -1) {
            List<Integer> list = new ArrayList<>();
            for(Integer i : adjList[parent[prun]]) {
                if(i != prun){
                    list.add(i);
                }
            }
            adjList[parent[prun]] = list;
        }

        q.add(prun);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(Integer i : adjList[cur]) {
                q.add(i);
            }
            adjList[cur].clear();
            check[cur] = true;
        }

        int cnt = 0;

        for(int i = 0; i < N; i++) {
            if(adjList[i].size() == 0 && !check[i]) cnt++;
        }

        System.out.println(cnt==0 && root != prun && !check[root] ? 1 : cnt);

    }
}