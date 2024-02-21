import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static int[] parent;
	public static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int tc = 0;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) {
				break;
			}
			cnt = N;
			++tc;
			Map<String, Integer> name = new HashMap<>();
			parent = new int[N]; //부모
			
			//부모담는 배열 -1로 초기화
			for(int i=0; i<N ; i++) {
				parent[i] = -1;
			}
			
			String[] uni = new String[N];  
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String n1 = st.nextToken();
				name.put(n1, i);
				String n2 = st.nextToken();
				uni[i] = n2;
			}
			for(int i=0; i<N; i++) {
				union(i, name.get(uni[i]));
			}
			
			sb.append(tc + " " + cnt + "\n");
			
		}
		System.out.println(sb);
		
	}
	
	//a를 루트로 하며 합집합 만들기
	public static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		if(aParent != bParent) {
			parent[aParent] += parent[bParent];
			parent[bParent] = aParent;
			cnt--;
		}
	}
	
	//부모배열 이 음수가 아니면 부모가 있다는 뜻으로 재귀태우기
	public static int find(int a) {
		if(parent[a] <0) {
			return a;
		}
		else {
			return parent[a] = find(parent[a]);
		}
	}
	
}