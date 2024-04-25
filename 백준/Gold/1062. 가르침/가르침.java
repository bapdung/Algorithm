import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static int N, K;
	public static HashSet<Integer> set;
	public static HashSet<Integer> checkWord;
	public static boolean[] check = new boolean[26];
	public static int max = 0;
	public static HashSet<Integer>[] words;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		set = new HashSet<>();
		words = new HashSet[N];
		set = new HashSet<>();
		set.add(0);
		set.add(2);
		set.add(8);
		set.add(19);
		set.add(13);
		check[0] = true;
		check[2] = true;
		check[8] = true;
		check[19] = true;
		check[13] = true;
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			words[i] = new HashSet<>();
			for(int s=4; s<str.length()-4; s++) {
				int n = str.charAt(s) - 'a';
				if(!set.contains(n)) words[i].add(n);
			}
		}
		
		if(K-5<0) {
			System.out.println(0);
		} else {
			조합(0, 1);
			System.out.println(max);
		}
		
	}
	public static void 조합(int cnt, int start) {
		if(cnt == K-5) {
			available();
			return;
		}
		for(int i=start; i<26; i++) {
			if(!check[i]) {
				set.add(i);
				조합(cnt+1, i+1);
				set.remove(i);
			}
		}
	}
	public static void available() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			Iterator<Integer> iterator = words[i].iterator();
			cnt++;
			while(iterator.hasNext()) {
				if(!set.contains(iterator.next())) {
					cnt--;
					break;
				}
			}
		}
		max = Math.max(cnt, max);
	}
}