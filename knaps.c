#include <stdio.h>

int max(int a, int b){
    int max=(a>b)?a:b;
    return max;
}

int knapSack(int W, int wt[], int val[], int n) 
{ 
   int i, w,j; 
   int K[n+1][W+1]; 
  
   // Build table K[][] in bottom up manner 
   for (i = 0; i <= n; i++) 
   { 
       for (w = 0; w <= W; w++) 
       { 
           if (i==0 || w==0) 
               K[i][w] = 0; 
           else if (wt[i-1] <= w) 
                 K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]); 
           else
                 K[i][w] = K[i-1][w]; 
       } 
   } 
   for(i=1;i<=6;i++){
       for(j=1;j<=8;j++){
           printf("%d ",K[i][j]);
       }
       printf("\n");
   }
  
   return K[n][W]; 
} 

void main(){
    int W;
    int V=0;
    int w[]={5,1,4,3,8,9};
    int v[]={37,65,34,11,100};
    printf("%d\n",knapSack(8,w,v,6));
}