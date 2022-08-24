package com.example.jpaproject.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class CodingTest {
    Solution solution = new Solution();

    @Test
    public void 랭킹() throws Exception{
        //given
        int k = 3;
        String[] user_scores = {"alex 100", "ch 200", "coco 150", "luna 100", "alex 120", "coco 300", "ch 110"};
        //when
        int result = solution.solution3(k, user_scores);
        //then
        Assertions.assertThat(result).isEqualTo(4);

    }
    @Test
    public void 신규아이디추천() throws Exception{
        //given
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        String expected = "bat.y.abcdefghi";

        //when
        String result = solution.solution2(new_id);

        //then
        Assertions.assertThat(expected).isEqualTo(result);
        
    }
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
    @Test
    public void 투썸() throws Exception{
        //given
        int[] arr = {2,1,3,1,4,2,1,3};
        int expect = 2;
        //when
        int result = solution.solution5(arr);
        //then
        Assertions.assertThat(result).isEqualTo(expect);
    }
    @Test
    public void 폭탄() throws Exception{
        //given
        int N = 3;  // 인원수
        int M = 3;  // 폭탄 최종 카운트
        int K = 2;  // 짝수면 오른쪽으로 K번, 홀수면 왼쪽 K번
        int answer = 6;

        //when
        int result = solution.solution4(N, M, K);
        //then
        Assertions.assertThat(result).isEqualTo(answer);

    }
    class Solution{
        public int solution5(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            int answer = Integer.MAX_VALUE;
            for(int i = 0; i < arr.length; i++){
                if(!map.containsKey(arr[i])){
                    map.put(arr[i], i);
                }else{
                    int before = map.get(arr[i]);
                    System.out.println(arr[i]);
                    System.out.println("befor = " + before);
                    System.out.println("after = " + (i - before));
                    map.replace(arr[i], i);  // 1, 3
                    answer = Math.min(answer, i - before);
                }
            }


            return answer;
        }
        public int solution4(int N, int M, int K){
            // 3, 3, 2
            // 인원수
            // 폭탄 최종 카운트
            // 짝수면 오른쪽으로 K번, 홀수면 왼쪽 K번

            LinkedList<Integer> queue = new LinkedList<>();
            for(int i = 0; i < N; i++){
                queue.add(0);
            };

            int count = 0;
            while(!queue.contains(M)){
                if(queue.peek() % 2 == 1){
                    //홀수 : 왼쪽
                    for(int i = 0; i < K; i++){
                        int first = queue.poll();  // 1 -> 2
                        queue.add(first);   // 231 - > 312
                    }
                    queue.set(0, queue.peek() + 1);
                }else{
                    // 짝수 : 오른쪽
                    for(int i = 0; i < K; i++){
                        // 3 - > 2
                        int fr = queue.poll();  // 3 -> 2
                        int se = queue.poll();  // 1

                        queue.add(fr);  // 231
                        queue.add(se);
                    }
                    queue.set(0, queue.peek() + 1);
                    System.out.println(queue.peek());
                }
            }
            int answer = 0;
           for(int i = 0; i < N; i++){
               answer += queue.poll();
           }

            return answer - 1;
        }


        public int solution3(int K, String[] user_scores) {
            int count = 0;

            Map<String, Integer> map = new HashMap<>();
            // 첫번 째 목록
            for(int i = 0; i < K; i++){
                String temp = user_scores[i];
                String[] user_score = temp.split(" ");
                String name = user_score[0];
                int score = Integer.valueOf(user_score[1]);

            }
            // 100 200 150

            //"luna 100", "alex 120", "coco 300", "ch 110"



            return count;
        }
        public String solution2(String new_id) {
            String answer = "";
            new_id = new_id.toLowerCase();
            new_id = new_id.replaceAll("[^a-z\\d\\-_.]*", "")
                        .replaceAll("\\.{2,}", ".")
                    .replaceAll("^[.]|[.]&", "");
            return answer;
        }
        
        
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
