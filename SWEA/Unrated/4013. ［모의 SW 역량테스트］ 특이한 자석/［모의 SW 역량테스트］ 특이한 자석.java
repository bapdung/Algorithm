import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    public static int K, right, left;
    public static int[] arr, ways;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        right = 32;
        left = 2;
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++) {
            arr = new int[5];
            K = Integer.parseInt(br.readLine());
            //00?000?0
            for(int r=1; r<=4; r++) {
                st = new StringTokenizer(br.readLine());
                int bit = 0;
                for(int c=7; c>=0; c--) {
                    if(Integer.parseInt(st.nextToken())==1) {
                        bit += Math.pow(2,c);
                    }
                }
                arr[r] = bit;
            }
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                //1 : 시계방향  -1 : 반시계
                int way = Integer.parseInt(st.nextToken());
                ways = new int[5];
                ways[n] = way;
                int l =1;
                boolean flagR = true;
                boolean flagL = true;
                while(flagR || flagL) {
                    //오른쪽바퀴
                    if(flagR) {
                        int cur = n+l;
                        if(cur<=0 || cur>=5) {
                            flagR = false;
                        } else if(((arr[cur-1] & right)>>5) != ((arr[cur] & left)>>1)) {
                            ways[cur] = -ways[cur-1];
                        } else {
                            flagR = false;
                        }
                    }
                    //왼쪽바퀴
                    if(flagL) {
                        int cur = n-l;
                        if(cur<=0 || cur>=5) {
                            flagL = false;
                        } else if(((arr[cur+1] & left)>>1) != ((arr[cur] & right)>>5)) {
                            ways[cur] = -ways[cur+1] ;
                        } else {
                            flagL = false;
                        }
                    }
                    l++;
                }
                 
                //회전
                move();
                 
            }
            int score = 0;
            for(int i=1; i<=4; i++) {
                if((arr[i] & (1<<7)) != 0) {
                    score += (1<<(i-1));
                }
            }
            sb.append("#" + tc + " " + score + "\n");
        }
        System.out.println(sb);
    }
    public static void move() {
        for(int i=1; i<=4; i++) {
            if(ways[i] == -1) { // 반시계
                if((arr[i] & (1<<7)) != 0) {
                    arr[i] = arr[i]<<1;
                    arr[i] -= (1<<8);
                    arr[i] += 1;
                } else {
                    arr[i] = arr[i]<<1;
                }
            } else if(ways[i] == 1) { //시계
                if((arr[i] & 1) != 0) {
                    arr[i] = arr[i]>>1;
                    arr[i]+=(1<<7);
                } else {
                    arr[i] = arr[i]>>1;
                }
            }
        }
    }
}