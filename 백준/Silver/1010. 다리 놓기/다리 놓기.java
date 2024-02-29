
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		        // 1~N까지 다리의 이름이 없다고 생각하고
		        // 중복처리해서 나열
		        // 그리고 순서대로 1~N까지 이름 붙여주기

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		Integer[] arr = {1,2,3,4,5};
		Arrays.sort(arr,Collections.reverseOrder());

		for(int t=0; t<T; t++) {
			double N = sc.nextInt();
		    double M = sc.nextInt();
		    if(N > M-N) {
		    	N = M - N;
		    }

		    double a = 1;
		    double b = 1;

		    for(double i=1; i<=N; i++) {
		    	a *= (long)i;
		    }

		    for(double i=M; i>=M-N+1; i--) {
		        b *= (long)i;
		    }

		    System.out.println((long)(b/a));
		}

    }
}