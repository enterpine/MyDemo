package com.jinsong.leetcode;

public class LeetCode11 {


    public static int maxArea(int[] height) {
        int maxarea = 0;

        int l = 0 ;
        int r = height.length-1;

        if(l==r || height.length==0 ) {
            return 0;
        }
        if(height.length == 1){
            return Math.min(height[l], height[r]) * (r - l);
        }

        while(l<r) {
            int cur = Math.min(height[l], height[r]) * (r - l);
            maxarea = Math.max(cur, maxarea);
           //System.out.println(String.format("index:%d %d. height:%d %d.area:%d", l, r, height[l], height[r], maxarea));

            int l_t = l + 1;
            int r_t = r - 1;

            if(height[l]>height[r]){
                r=r_t;
            }else if(height[l]<height[r]){
                l=l_t;
            }else if(height[l]==height[r]){
                if(height[l_t]>height[r_t]){
                    l=l_t;
                }else{
                    r=r_t;
                }
            }
        }

        return maxarea ;

//            int next_l = Math.min(height[l], height[r]) * (r - l_t);
//            int next_r = Math.min(height[l], height[r_t]) * (r_t - l);
//
//            //cur，next_l，next_r都不同时
//            if (next_l > cur && next_l > next_r) {//左移后大于当前，且大于右移
//                l = l_t;
//            } else if (next_r > cur && next_r > next_l) //右移后大于当前，且大于左移动
//            {
//                r = r_t;
//            }
//            //cur = next_l >next_r
//            else if (cur == next_l && next_l > next_r) {
//                l = l_t;
//            }
//            //cur = next_r > next_l
//            else if (cur == next_r && next_r > next_l) {
//                r = r_t;
//            }
//            //cur = next_r = next_l
//            else if(cur == next_r && cur == next_l){
//                l=l_t;
//                r=r_t;
//            }else if(cur>next_l && next_l == next_r){
//                //左移动条件
//                if(height[l] < height[l_t]){
//                    l=l_t;
//                }//右移动条件
//                else if(height[r] < height[r_t]){
//                    r=r_t;
//                }
//                else{
//                    if(r>l){
//                        l=l_t;
//                    }else if(r==l){
//                        r=r_t;
//                        l=l_t;
//                    }else{
//                        r=r_t;
//                    }
//                    //System.out.println(String.format("aa%d%d%d",cur,next_l,next_r));
//                }
//            }else if(cur>next_l && cur>next_r ){
//
//                if(next_r>next_l){
//                    r=r_t;
//                }else if(next_r<next_l){
//                    l=l_t;
//                }else{System.out.println("2");}
//
//            }



    }

    public static void main(String[] args) {

        int []  a = new int[]  {1,3,2,5,25,24,5};
        System.out.println(maxArea(a));
    }
}
