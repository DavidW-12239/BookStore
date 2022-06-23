package org.bookStore.utils;

import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static int[] randomNumber(int min,int max,int n){


        if(n>(max-min+1) || max <min){
            return null;
        }

        int[] result = new int[n];

        int count = 0;
        while(count <n){
            int num = (int)(Math.random()*(max-min))+min;
            boolean flag = true;
            for(int j=0;j<count;j++){
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}
