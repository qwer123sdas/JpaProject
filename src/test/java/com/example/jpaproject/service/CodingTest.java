package com.example.jpaproject.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CodingTest {
    Solution solution = new Solution();
    @Test
    public void 해결1() throws Exception{
        //given
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        int nn = 6;
        int[] arr11 = {46, 33, 33 ,22, 31, 50};
        int[] arr22 = {27 ,56, 19, 14, 14, 10};

        //when
        String[] result = solution.solution1(n, arr1, arr2);
        String[] result2 = solution.solution1(nn, arr11, arr22);

        //then
        String[] expected = {"#####","# # #", "### #", "#  ##", "#####"};
        String[] expected2 = {"######","###  #","##  ##"," #### "," #####","### # "};

        Assertions.assertThat(result).isEqualTo(expected);
        Assertions.assertThat(result2).isEqualTo(expected2);

    }
    class Solution{
        public String[] solution1(int n, int[] arr1, int[] arr2) {
            String[] temp = new String[arr1.length];

            for(int i = 0; i < arr1.length; i++){
                String tempString  = Integer.toBinaryString(arr1[i] | arr2[i]);
                temp[i] = tempString.replace("0", " ")
                                    .replace("1", "#");
                if(temp[i].length() < n){
                    temp[i] = " " + temp[i];
                }
            }

            //String[] answer = new String[n];

/*            for(int i = 0; i < n; i++ ){
                answer[i] = "";
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < temp[i].length(); j++){
                    if(temp[i].charAt(j) == '1'){
                        answer[i] += "#";
                    }else{
                        answer[i] += " ";
                    }
                }
            }*/

            return temp;
        }
    }
}
