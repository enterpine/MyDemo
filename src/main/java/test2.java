import java.util.ArrayList;
import java.util.Scanner;



public class test2 {

     static ArrayList ar = new ArrayList<String>();

     public static void main(String[] args){
          Scanner sc = new Scanner(System.in);
          try {
               while (1==1) {
                    ar.clear();
                    int len = sc.nextInt();

                    int[] input = new int[len];
                    for(int i =0;i<len;i++) {
                         input[i] = sc.nextInt();
                    }
                    input = sort(len, input);
                    int newlen = 0;
                    for(int i=0;i< input.length;i++) {
                         if(i==0){newlen=newlen+1;}
                         if(i>0){
                              if(input[i]==input[i-1]){}
                              if((input[i]!=input[i-1])){ newlen=newlen+1 ;}
                         }
                    }

                    int[] result = new int[newlen];
                    int newidx = 0;
                    for(int i=0;i< input.length;i++) {
                         if(i==0){
                              result[newidx]=input[i];
                              newidx=newidx+1;
                         }
                         if(i>0){
                              if(input[i]==input[i-1]){}
                              if((input[i]!=input[i-1])){
                                   result[newidx]=input[i];
                                   newidx=newidx+1;
                              }
                         }
                    }
                    System.out.println(result.length);
                    for(int i=0;i<result.length;i++){
                         System.out.print(result[i]);
                    }


               }
          }
          catch (Exception e){
               System.out.print(e.getMessage().toString());
          }
     }
     public static int[] sort(int a,int[] input){
          int len = input.length;
          for(int i = 0;i<len;i++){
               for(int j = i;j<len;j++){
                    if(input[i]>input[j]) {
                         int tmp = 0;
                         tmp = input[j];
                         input[j] = input[i];
                         input[i] = tmp;
                    }
               }
          }



          return input;
     }
}
