import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.text.Position;

import org.w3c.dom.Node;

public class Main {
    public static int N;
    public static int R;
    public static boolean[] check;
    public static int[] score, time;
    public static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());
        check = new boolean[101];
        score = new int[101];
        time = new int[101];
        pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int student = Integer.parseInt(st.nextToken());
            if(pq.size() < N) {
                if(!check[student]) {
                    pq.add(new Node(i, student, 1));
                    score[student] = 1;
                    time[student] = i;
                    check[student] = true;
                } else {
                    pq.removeIf(n -> (n.idx == student));
                    pq.add(new Node(time[student], student, ++score[student]));
                }
            } else if(pq.size() == N && !check[student]){
                Node n = pq.poll();
                check[n.idx] = false;
                pq.add(new Node(i, student, 1));
                score[student] = 1;
                time[student] = i;
                check[student] = true;

            } else {
                pq.removeIf(n -> (n.idx == student));
                pq.add(new Node(time[student], student, ++score[student]));
            }
        }

        for(int i = 1; i < 101; i++) {
            if(check[i]) sb.append(i + " ");
        }
        System.out.println(sb);

    }
    public static class Node implements Comparable<Node>{
        int time;
        int idx;
        int score;

        public Node(int time, int idx, int score) {
            this.time = time;
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            if(this.score == o.score) {
                return this.time - o.time;
            }
            return this.score - o.score;
        }

        public String toString(){
            return "time:" + time + " idx:" + idx + " score:" + score;
        }
    }
}