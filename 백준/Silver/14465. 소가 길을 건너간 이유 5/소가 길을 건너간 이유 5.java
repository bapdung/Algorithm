import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.text.Position;

import org.w3c.dom.Node;

public class Main {
    public static int N, K, B;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 0; i < B; i++) {
            int garo = Integer.parseInt(br.readLine());
            arr[garo] = 1;
        }

        int min = Integer.MAX_VALUE;
        //슬라이딩윈도우를 실행
        int l = 1;
        int r = K;
        int sum = 0;
        for(int i=1; i<=r; i++){
            sum += arr[i];
        }
        min = Math.min(min, sum);

        while(++r<=N){
            sum += arr[r];
            sum -= arr[l++];
            min = Math.min(min, sum);
        }
        System.out.println(min);
    }
}