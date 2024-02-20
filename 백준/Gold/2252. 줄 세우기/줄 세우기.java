import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @author 서지흔
 * @date 2024.02.20
 * @link https://www.acmicpc.net/problem/2252
 * @keyword_solution  
 * 학생을 키 순서대로 줄 세우는 문제
 * 절대적인 키의 값은 주어지지 않고 상대적인 키의 비교값만 주어짐
 * 위상정렬을 이용해 풀이
 * (진입차수가 0인 아이들을 q에 넣어주며 그에 따라 진입차수를 관리)
 * @input 
 * N(1 ≤ N ≤ 32,000)
 * M(1 ≤ M ≤ 100,000)
 * A B (A학생이 B학생보다 키가 작다 = B학생보다 앞에 서야 한다.)
 * @output   
 * 최소 치킨거리 출력
 * @time_complex  
 * O(N)
 * @perf 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] degree = new int[N+1]; //진입차수 배열
		List<Integer>[] adjList = new ArrayList[N+1]; //인접리스트
		
		for(int i=1; i<N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//순서를 관리할 큐 선언
		Queue<Integer> q = new ArrayDeque<>();
		
		
		//간선을 통해 인접리스트, 진입차수 배열 만들기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			degree[to]++;
		}
		
		//진입차수가 0인 학생 큐에 offer
		for(int i=1; i<N+1; i++) { 
			if(degree[i] == 0) {
				q.offer(i);
			}
		}
		
		
		while(!q.isEmpty()) {
			int from = q.poll(); //앞에 서는 학생
			sb.append(from + " ");
			
			for(int to : adjList[from]) { //앞학생과 연결된 학생들
				degree[to]--; //이미 앞에 섰으니 앞 학생이랑 연결된 학생들의 진입차수 1씩 감소
				if(degree[to] == 0) q.offer(to); //감소 후 진입차수가 0이라면 q에 offer
			}
		}

		System.out.println(sb);
		
	}
}