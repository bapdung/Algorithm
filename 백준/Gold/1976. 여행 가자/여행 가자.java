import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int[] parents;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parents = new int[N];
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(st.nextToken())==1) {
					union(i,j);
				}
			}
		}
		
		boolean flag = true;
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken())-1;
		int firstParent = find(first);
		for(int i=1; i<M; i++) {
			int v = Integer.parseInt(st.nextToken())-1;
			int vP = find(v);
			if(vP != firstParent) {
				flag = false;
				break;
			}
		}
		if(flag == false) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
	}
	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		}else {
			return parents[a] = find(parents[a]);
		}
	}
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP == bP) {
			return false;
		}else {
			parents[bP] = aP;
			return true;
		}
	}
}