import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static char[][] star = new char[5][9];
    public static boolean[] visit = new boolean[13];
    public static List<int[]> emptyList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int r=0; r<5; r++){
            String str = br.readLine();
            
            for(int c=0; c<9; c++) {
                char ch = str.charAt(c);
                if(ch == '.') {
                    star[r][c] = '.';
                    
                }
                else if(ch == 'x') {
                    emptyList.add(new int[] {r,c});
                    
                } else {
                    star[r][c] = ch;
                    visit[(int)(star[r][c] - 'A') + 1] = true;
                }
            }
        }
//        for(int r=0 ; r<5; r++) {
//            for(int c=0 ; c<9; c++) {
//                System.out.print(star[r][c] + "\t");
//            }
//            System.out.println();
//        }
        
        dfs(0);
        
        
    }
    public static void dfs(int cnt) {
    	if(cnt == emptyList.size()) {
    		if(check(star)) {
    			for(int r=0 ; r<5; r++) {
    	            for(int c=0 ; c<9; c++) {
    	                System.out.print(star[r][c]);
    	            }
    	            System.out.println();
    	        }
    			System.exit(0);
    		}
    		return;
    	}
    	int r = emptyList.get(cnt)[0];
		int c = emptyList.get(cnt)[1];
    	for(int i=1; i<visit.length; i++) {
    		if(!visit[i]) {
    			visit[i] = true;
    			star[r][c] = (char) (64 + i);
    			dfs(cnt+1);
    			visit[i] = false;
    		}
    		
    	}
    }
    
    public static boolean check(char[][] arr) {
    	int sum1 =0;
    	int sum2 =0;
    	for(int i=0; i<9; i++) {
    		if(i%2 == 1) {
    			sum1 += arr[1][i] - 'A' + 1;
    			sum2 += arr[3][i] - 'A' + 1;
    		}
    	}
    	if(sum1 != 26 || sum2 != 26) return false;
    	sum1 = 0;
    	sum2=0;
    	for(int i=0; i<4; i++) {
    		sum1 += arr[i][4-i] - 'A' + 1;
    		sum2 += arr[i][4+i] - 'A' + 1;
    	}
    	if(sum1 != 26 || sum2 != 26) return false;
    	sum1 = 0;
    	sum2 = 0;
    	for(int i=0; i<4; i++) {
    		sum1 += arr[4-i][4-i] - 'A' + 1;
    		sum2 += arr[4-i][4+i] - 'A' + 1;
    	}
    	if(sum1 != 26 || sum2 != 26) return false;
    	return true;
    }
}