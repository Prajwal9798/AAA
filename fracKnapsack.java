import java.util.*;

class fracKnapsack{
    public static void main(String args[]){
        int weight[];
        int cost[];
        int n;
        int ratio[];
        int capacity,profit=0;

        Scanner in=new Scanner(System.in);

        System.out.println("Enter number of items");
        n=in.nextInt();

        weight=new int[n];
        cost=new int[n];
        ratio=new int[n];

        System.out.println("Enter the profit of items");
        for(int i=0;i<n;i++){
            cost[i]=in.nextInt();
        }

        System.out.println("Enter the weight of items");
        for(int i=0;i<n;i++){
            weight[i]=in.nextInt();
            ratio[i]=cost[i]/weight[i];
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                if(ratio[j]<ratio[j+1]){
                    int tempr,tempc,tempw;
                    tempr=ratio[j];
                    tempc=cost[j];
                    tempw=weight[j];
                    ratio[j]=ratio[j+1];
                    cost[j]=cost[j+1];
                    weight[j]=weight[j+1];
                    ratio[j+1]=tempr;
                    cost[j+1]=tempc;
                    weight[j+1]=tempw;
                }
            }
        }

        System.out.println("Enter capacity of knapsack");
        capacity=in.nextInt();

        int count=0;
        while(capacity>0){
            if(capacity>=weight[count]){
                profit+=cost[count];
                capacity=capacity-weight[count];
            }
            else{
                profit=cost[count]*(capacity/weight[count]);
                capacity=0;
            }
            count++;
            if(count>=n){
                break;
            }
        }
        System.out.println("Profit = "+profit);
    }
}