package com.example.websocketdemo;

import org.springframework.web.servlet.FlashMap;

import java.util.*;

public class longestwords {
    public static void main(String[] args) {
        String[] strings = new String[]{"apple", "iOS", "dog", "nana", "man", "good", "goodman"};
        String res = longestWord(strings);
        System.out.println(res);
    }

    public static String longestWord(String[] words) {
        String res = "";
        if (words.length == 0) {
            return res;
        }
        List<String> allWords= Arrays.asList(words);
       Set<String> set = new HashSet<>(allWords);
        allWords.sort((a, b)->
             a.length() == b.length() ? (a + b).compareTo(b + a) : b.length() - a.length()
        );

        int flag=-1;
        for (String word : allWords) {
            int len=word.length();
            boolean[] dp=new boolean[len+1];
            dp[0]=true;
            for (int i=1;i<=len;i++){
                for (int j= i-1;j>=0;j--){
                    String subword = word.substring(j, i);
                    if (dp[j] && set.contains(subword)){
                        dp[i]=true;
                        flag=j;
                        break;
                    }
                }
            }
            if (dp[len]&&flag!=0){
               return word;
            }
        }
        return res;
    }
}
