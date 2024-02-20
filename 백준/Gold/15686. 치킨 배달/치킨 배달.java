import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static int m;
	public static List<List<Integer>> chick = new ArrayList<>();
	public static List<List<Integer>> dist = new ArrayList<>();
	public static int[][] map;
	public static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = null;
		tokens = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tokens.nextToken());
		m = Integer.parseInt(tokens.nextToken());
		
		//지도 배열저장
		map = new int[n][n];
		for(int r = 0; r<n ; r++) {
			tokens = new StringTokenizer(br.readLine());
			for(int c = 0; c<n ; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] == 2) {//치킨집 탐색해 chick배열에 행열값 저장
					chick.add(new ArrayList<>());
					chick.get(chick.size()-1).add(r);
					chick.get(chick.size()-1).add(c);
				}
			}
			
		}
		//문제 없음
		
		
		for(int r = 0; r<n ; r++) {
			for(int c = 0; c<n ; c++) {
				if(map[r][c] == 1) {
					dist = distSave(r,c);
				}
			}
		}
		
		int[] way = new int[m];
		
		combination(0, 0, way);
		System.out.println(min);
		
	}

	public static List<List<Integer>> distSave(int r, int c) {
		dist.add(new ArrayList<>());
		for(int i = 0; i<chick.size(); i++) {
			int distance = Math.abs((chick.get(i).get(0))- r) + Math.abs((chick.get(i).get(1))- c);
			dist.get(dist.size()-1).add(distance);
		}
		return dist;
	}
	
	
	public static void combination(int cnt, int start, int[] way) {
		//기저조건
		if(cnt == way.length) {
			min = Math.min(min, getDist(way)); //치킨거리의 최솟값
			return;
		}
		for(int i = start; i<chick.size(); i++) {
			way[cnt] = i;
			combination(cnt+1, i+1, way);
		}
	}
	
	//way방법을 가지고 치킨거리 구하기
	public static int getDist(int[] way) {
		int sum = 0;
		for(int i = 0; i< dist.size(); i++) {
			int ckickToHouseMin = Integer.MAX_VALUE;
			for(int j = 0 ; j< way.length ; j++) {
				ckickToHouseMin = Math.min(dist.get(i).get(way[j]), ckickToHouseMin);
			}
			sum += ckickToHouseMin;
		}
		return sum;
			
	}
}