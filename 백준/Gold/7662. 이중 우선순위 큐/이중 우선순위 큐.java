import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			TreeSet<Num> set = new TreeSet<>();
			int N = Integer.parseInt(br.readLine());
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				char rule = st.nextToken().charAt(0);
				if(rule == 'I') {
					long num = Long.parseLong(st.nextToken());
					set.add(new Num(num, i));
				} else {
					int sign = Integer.parseInt(st.nextToken());
					if(set.isEmpty()) continue;
					if(sign==-1) set.remove(set.first());
					else set.remove(set.last());
				}
//				System.out.println(set);
			}
			
			if(set.size()>1) {
				sb.append(set.last().a + " " + set.first().a + "\n");
			} else if(set.size()==1) {
				long a = set.first().a;
				sb.append(a + " " + a + "\n");
			} else {
				sb.append("EMPTY\n");
			}
			
		}
		System.out.println(sb);
	}
	public static class Num implements Comparable<Num>{
		long a;
		int idx;
		public Num(long a, int idx) {
			super();
			this.a = a;
			this.idx = idx;
		}
		@Override
		public int compareTo(Num o) {
			if(this.a == o.a) {
				return this.idx - o.idx;
			}
			return Long.compare(this.a, o.a);
		}
		@Override
		public String toString() {
			return " " + a + " ";
		}
		
	}
}