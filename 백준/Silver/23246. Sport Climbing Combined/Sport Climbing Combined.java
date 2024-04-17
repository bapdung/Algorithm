import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		Person[] arr = new Person[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int backNumber = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[i] = new Person(backNumber, a*b*c, a+b+c);
		}
		int rank = 1;
		Arrays.sort(arr);
		
		System.out.println(arr[0].backNum + " " + arr[1].backNum + " " + arr[2].backNum);
	}
	public static class Person implements Comparable<Person>{
		int backNum;
		int score;
		int sum;
		public Person(int backNum, int score, int sum) {
			super();
			this.backNum = backNum;
			this.score = score;
			this.sum = sum;
		}
		@Override
		public int compareTo(Person o) {
			if(this.score == o.score) {
				if(this.sum == o.sum) {
					return this.backNum - o.backNum;
				}
				return this.sum - o.sum;
			}
			return this.score - o.score;
		}
		
		
	}
}