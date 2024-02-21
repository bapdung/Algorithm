import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int L; // 암호문 길이
	public static int C; // 문자열 길이
	public static char[] arr; // 모음 배열
	public static char[] choosed;
	public static List<char[]> rst;
	public static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		

		arr = new char[C];
		rst = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int c = 0; c<C; c++) {
			arr[c] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		

		
		choosed = new char[L];
		조합(0,0);
		System.out.println(sb);

	}
	public static void 조합(int cnt, int start) {
		if(cnt == L) {
			if(check()) {
				for(int i=0; i<L; i++) {
					sb.append(choosed[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for(int i = start; i< arr.length ; i++) {
			choosed[cnt] = arr[i];
			조합(cnt+1, i+1);
			
		}
	
	}
	public static boolean check() {
		int mCnt = 0;
		int jCnt = 0;
	
		for (int i = 0; i < L; i++) {
			char c = choosed[i];
			switch (c) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				mCnt++;
				break;
			default:
				jCnt++;
			}
		}
		return mCnt>=1 && jCnt>=2;
	}

	
}