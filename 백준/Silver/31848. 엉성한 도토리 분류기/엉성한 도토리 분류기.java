import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.text.Position;

import org.w3c.dom.Node;

public class Main {
    public static int N, Q;
    public static int[] hole, dotori;
    public static List<Node> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        hole = new int[N];
        list = new ArrayList<Node>();
        st = new StringTokenizer(br.readLine());
        list.add(new Node(0,0));

        for(int i = 0; i < N; i++) {
            int w = Integer.parseInt(st.nextToken()) + i;
            if(list.get(list.size()-1).w < w){
                list.add(new Node(i+1, w));
            }
        }

        list.remove(0);

        Q = Integer.parseInt(br.readLine());
        dotori = new int[Q];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++) {
            dotori[i] = Integer.parseInt(st.nextToken());
            int idx = (bs(dotori[i]));
            if(idx<0){
                sb.append(list.get(-idx - 1).idx + " ");
            } else{
                sb.append(list.get(idx).idx + " ");
            }
        }


        System.out.println(sb);
    }
    public static int bs(int w){
        int left = 0;
        int right = list.size() - 1;
        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;
            if(list.get(mid).w > w){
                right = mid - 1;
            } else if ((list.get(mid).w < w)){
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -(left + 1);
    }
    public static class Node{
        int idx;
        int w;
        public Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }

        public String toString(){
            return "["+idx+","+w+"]";
        }

    }
}