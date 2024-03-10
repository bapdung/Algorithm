import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = null;
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			Days[] days = new Days[N];
			for(int i=0; i<N ; i++) {
				st = new StringTokenizer(br.readLine());
				int sm = Integer.parseInt(st.nextToken());
				int sd = Integer.parseInt(st.nextToken());
				int em = Integer.parseInt(st.nextToken());
				int ed = Integer.parseInt(st.nextToken());
				days[i] = new Days(sm, sd, em, ed);
			}
			
			Arrays.sort(days);
			
			
			PriorityQueue<Days> pq = new PriorityQueue<>();
			for(int i=0; i<N; i++) {
				if(days[i].sm <3) {
					pq.add(days[i]);
				} else if(days[i].sm ==3) {
					if(days[i].sd == 1) {
						pq.add(days[i]);
					}
				}
			}
			
			int em = 0;
			int ed = 0;
			int cnt = 0;
			while(!pq.isEmpty()) {
//				System.out.println(pq);
				Days a = pq.poll();
				pq.clear();
				cnt++;
				em = a.em;
				ed = a.ed;
//				System.out.println(em + " " + ed);
				if(em >11) {
					break;
				} else if(em == 11) {
					if(ed-1==30) break;
				}
				int avail = 0;
				for(Days b : days) {
					if(a.sm == em && a.sd == ed) {
						continue;
					}
					if(em > b.sm) {
						if(b.em == em) {
							if(ed<b.ed) {
								pq.offer(b);
								avail++;
							}
						} else if(b.em>em) {
							pq.offer(b);
							avail++;
						}
						
					} else if(em==b.sm) {
						if(ed==b.sd) {
							pq.offer(b);
							avail++;
						}
						else if(ed>b.sd) {
							if(em<b.em || em == b.em && ed<b.ed) {
								pq.offer(b);
								avail++;
							}
						}
					}
				}
				if(avail == 0) {
					System.out.println(0);
					System.exit(0);
				}
				
			}
			System.out.println(cnt);
			
			
		}
		public static class Days implements Comparable<Days>{
			int sm;
			int sd;
			int em;
			int ed;
			public Days(int sm, int sd, int em, int ed) {
				super();
				this.sm = sm;
				this.sd = sd;
				this.em = em;
				this.ed = ed;
			}
			public int compareTo(Days d) {
				if(this.em == d.em) {
					return d.ed - this.ed;
				}
				return d.em - this.em; 
			}
			@Override
			public String toString() {
				return "Days [sm=" + sm + ", sd=" + sd + ", em=" + em + ", ed=" + ed + "]";
			}
			
			
		}
		
	}