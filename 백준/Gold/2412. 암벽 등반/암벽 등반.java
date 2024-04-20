import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, T, cnt;
	public static int d[] = {1,-1,-2,2,0};
	public static Queue<Position> q;
	public static HashMap<Position, Boolean> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		List<Position> arr = new ArrayList<>();
		map = new HashMap<>();
		boolean flag = false;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr.add(new Position(r,c));
			map.put(arr.get(i), false);
			if(c==T) flag = true;
		}
		if(!flag) {
			System.out.println(-1);
		}
//		System.out.println(map);
		
		q = new ArrayDeque<>();
		q.offer(new Position(0,0));
		cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
//			System.out.println(q);
			while(size-->0) {
				Position cur = q.poll();
				check(cur.r, cur.c);
//				System.out.println("check 후 " +q);
			}
		}
		System.out.println(-1);
		
	}
	public static void check(int r, int c) {
		for(int dr=0; dr<5; dr++) {
			for(int dc=0; dc<5; dc++) {
				int cr = r + d[dr];
				int cc = c + d[dc];
				Position p = new Position(cr,cc);
				if(map.containsKey(p)){
					if(p.c==T) {
						System.out.println(cnt);
						System.exit(0);
					}
					q.add(p);
					map.remove(p);
				}
			}
		}
	}
	public static class Position{
		int r;
		int c;
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Position [r=" + r + ", c=" + c + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
	}
	public static boolean check(int r1, int c1, int r2, int c2) {
		int r = Math.abs(r1-r2);
		int c = Math.abs(c1-c2);
		if(r<=2 && c<=2) {
			return true;
		}
		return false;
	}
}