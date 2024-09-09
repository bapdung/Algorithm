import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int K;
    public static int M;
    public static int T;
    public static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {

            sb.append("Scenario "+ t +":\n");

            N = Integer.parseInt(br.readLine());
            parents = new int[N];
            for(int i=0; i<N; i++) {
                parents[i] = i;
            }

            K = Integer.parseInt(br.readLine());
            for(int k=0; k<K; k++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                union(n1, n2);
            }

            M = Integer.parseInt(br.readLine());
            for(int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                if(find(n1) == find(n2)){
                    sb.append(1 + "\n");
                } else {
                    sb.append(0 + "\n");
                }
            }
            sb.append("\n");

        }
        System.out.println(sb);
    }
    public static void union(int a, int b){
        int aP = find(a);
        int bP = find(b);
        if(aP != bP){
            parents[aP] = bP;
        }
    }

    public static int find(int a){
        if(parents[a] == a){
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}