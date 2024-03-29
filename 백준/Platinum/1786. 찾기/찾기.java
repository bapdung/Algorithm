import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		String text = br.readLine();
		String str = br.readLine();
		int[] partial = new int[str.length()];
		int temp = 0;
		
		//KMP 알고리즘
		//j=0, i=1부터 시작
		//1.i,j위치 값 같으면 
		//부분문자열배열[i] = 부분문자열배열[i-1]+1;
		//j++, i++
				
		//2. i,j위치 값 다르면
		//부분문자열배열[i]=0
		//j=0, i++
		int j = 0;
		for(int i=1; i<str.length(); i++) {
			if(str.charAt(i) == str.charAt(j)) {
				partial[i] = partial[i-1]+1;
				j++;
			} else {
				partial[i] = 0;
				j=0;
			}
		}
		
		int[] partial1 = getPi(str);
		
		kmp(partial1, text, str);
		
//		System.out.println(str);
//		System.out.println(Arrays.toString(partial));
		
//		int check =0;
//		//돌면서 일치하지 않는 지점이 생긴다면
//		//그 지점의 index-1값의 부분문자열배열 값 확인
//		//그 값을 기준으로 접미==접두이므로 이 부분은 제외한 나머지부분만 문자열 비교해주면 된다
//		
//		for(j=0; j<text.length(); j++) {
//			while(check!=0 && text.charAt(j) != str.charAt(check)) {
//				check = partial[check-1];
//			}
//			if(text.charAt(j) == str.charAt(check)) {
//				if(check == str.length()-1) {
//					rst.add(j-str.length()+2);
//					check = 0;
//				}else {
//					check++;
//				}
//			}
//		}
		
//		while(j<text.length()) {
//			System.out.println(check + "j : " + j);
//			for(int i=check; i<str.length(); i++) {
//				if(text.charAt(j) != str.charAt(i) && i!=0) {
//					int equalNum = partial[i-1];
//					check = equalNum;
//					break;
//				}
//				if(i==str.length()-1 && text.charAt(j) == str.charAt(i)) {
//					rst.add(j-str.length()+2);
//					temp++;
//					int equalNum = partial[i-2];
//					check = equalNum;
//					j++;
//					break;
//				}
//				j++;
//				if(j == text.length()) break;
//			}
//		}
		sb.append(list.size() +"\n");
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i) + " ");
		}
		System.out.println(sb);
	}
	
	public static void kmp(int[] pi, String str, String pattern) {
		int j = 0;
		int strLen = str.length();
		int patternLen = pattern.length();
		
		for(int i = 0; i < strLen; i++) {
			while(j > 0 && str.charAt(i) != pattern.charAt(j)) { // 다르면 같았던 다음 접미사로 바로 건너뛰기 
				j = pi[j - 1];
			}
			
			if(str.charAt(i) == pattern.charAt(j)) { // 인덱스가 같은 문자 두 개가 같다.
				if(j + 1 == patternLen) { // pattern 문자열 전부가 같다면
					list.add(i - patternLen + 2); // 전체 문자열 중 패턴 문자열과 같은 문자열의 시작 인덱스를 구해야 하므로(게다가 1부터 시작함)
					j = pi[j]; // 초기화를 시켜줘야 함. 자기 자신은 어차피 0이라 0으로 직접 설정해도 되긴 할듯 
				}
				else { // 바로 다음번째 문자를 가지고 비교해야 하므로 
					j++;
				}
			}
		}
	}
	static int[] getPi(String str) {
		int j = 0; // 접두사 시작 인덱스 
		int n = str.length();
		int[] pi = new int[n];
		
		for(int i = 1; i < n; i++) { // 접미사 시작 인덱스 
			while(j > 0 && str.charAt(j) != str.charAt(i)) { // j > 0 인 이유는 최소 두 글자부터 비교하는 것이 맞아
				j = pi[j - 1]; // 다르면 이전의 문자열에서 접두사 - 접미사 같은 최대 길로 이동시킨다.
			}
			
			if(str.charAt(j) == str.charAt(i)) { // 같으면 
				pi[i] = ++j;
			}
		}
		
		return pi;
	}
}