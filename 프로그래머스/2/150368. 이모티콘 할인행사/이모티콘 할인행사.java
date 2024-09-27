import java.util.*;
import java.io.*;

class Solution {
    public static int[] discountEmozi, emozi, rst;
    public static int[][] user;
    public static double[] isSelected;
    public static boolean[] isChecked;
    public static int emoziCnt, userCnt;
    public int[] solution(int[][] users, int[] emoticons) throws IOException{
        int[] answer = {};
        rst = new int[2];
        emozi = emoticons;
        user = users;
        emoziCnt = emoticons.length;
        userCnt = users.length;
        isSelected = new double[emoziCnt];
        discountEmozi = new int[emoziCnt];
        
        순열(0);
        
        answer = rst;
        
        return answer;
    }
    
    public static void 순열(int cnt){
        if(cnt == emoziCnt){
            금액계산();
            return;
        }
        for(int i=1; i<5; i++){
            isSelected[cnt] = i * 10;
            순열(cnt+1);
        }
    }
    public static void 금액계산(){
        // System.out.println(Arrays.toString(isSelected));
        for(int i = 0; i < emoziCnt; i++){
            discountEmozi[i] = (int) ((100 - (isSelected[i]* 1)) * (double) emozi[i]) / 100;
        }
        // System.out.println(Arrays.toString(discountEmozi));
        
        사용자구매();
    }
    
    public static void 사용자구매(){
        int totalPrice = 0;
        int totalSub = 0;
        for(int i=0 ; i<userCnt; i++){
            int sum = 0;
            for(int d=0; d< emoziCnt; d++){
                if(user[i][0]<=isSelected[d]){
                    sum+= discountEmozi[d]; 
                }
            }
            // System.out.println(sum);
            if(sum >= user[i][1]){//서비스 가입
                totalSub++;
            } else { //구매
                totalPrice += sum;
            }
        }
        // System.out.println(totalSub + " " + totalPrice);
        
        //결과 갱신
        if(rst[0] < totalSub){
            rst[0] = totalSub;
            rst[1] = totalPrice;
        } else if(rst[0] == totalSub){
            if(rst[1] < totalPrice){
                rst[1] = totalPrice;
            }
        }
    }
}