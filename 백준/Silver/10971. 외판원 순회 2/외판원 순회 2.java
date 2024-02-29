import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] adjMatrix;
	public static boolean[] isEmpty;
	public static int[] choosed;
	public static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		for(int r=0; r<N ; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0 ;c<N ;c++) {
				adjMatrix[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		isEmpty = new boolean[N];
		choosed = new int[N];
		
		Permutation(0);
		System.out.println(min);
		
	}
	public static void Permutation(int cnt) {
		if(cnt == N) {
			road();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!isEmpty[i]) {
				choosed[cnt] = i;
				isEmpty[i] = true;
				Permutation(cnt+1);
				isEmpty[i] = false;
			}
		}
	}
	
	public static void road(){
		int total = 0;
		for(int i=0; i<N; i++) {
			int v1 = choosed[i % N];
			int v2 = choosed[(i+1) % N];
			if(adjMatrix[v1][v2] == 0) {
				return;
			}
			total += adjMatrix[v1][v2];
		}
		min = Math.min(total, min);
	}
}