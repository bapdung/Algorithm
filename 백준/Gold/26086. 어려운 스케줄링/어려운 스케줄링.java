import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static int N,Q,K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[Q][2];
		int idx = 0;
		
		TreeSet<Integer> set = new TreeSet<>();
		
		//1일때 idx 찾기
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int rule = Integer.parseInt(st.nextToken());
			if(rule == 0) {
				arr[i][1]=Integer.parseInt(st.nextToken());
			}else if(rule == 1) {
				idx = i;
				arr[i][0]=1;
			} else {
				arr[i][0]=2;
			}
		}
		
		//마지막으로 1나올때까지는 treeSet관리
		for(int i=0; i<idx; i++) {
			if(arr[i][0]==0) {
				set.add(arr[i][1]);
			}
		}
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            dq.addLast(iterator.next());
        }
		boolean flag = true;
        for(int i=idx; i<Q; i++) {
//        	System.out.println(i +" "+dq);
        	if(arr[i][0] == 0) {
        		if(flag) dq.addFirst(arr[i][1]);
        		else dq.addLast(arr[i][1]);
        	} else if(arr[i][0] == 2) {
        		flag=!flag;
        	}
        }
        if(flag) {
	        for(int i=1;i<K; i++) {
	        	dq.pollFirst();
	        }
	        System.out.println(dq.pollFirst());
        } else {
        	for(int i=1;i<K; i++) {
	        	dq.pollLast();
	        }
	        System.out.println(dq.pollLast());
        }
	}
}