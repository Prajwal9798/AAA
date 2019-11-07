class prims{

    void printMST(int parent[], int graph[][],int n) 
    { 
        System.out.println("Edge \tWeight"); 
        for (int i = 1; i < n; i++) 
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]); 
    }

    void prim(int adj[][],int n){
        int mstSet[]=new int[n];
        int key[]=new int[n];
        int parent[]=new int[n];

        for(int i=0;i<n;i++){
            key[i]=99999;
            mstSet[i]=0;
        }
        key[0]=0;
        parent[0]=-1;

        int min=99999;
        int minIndex=0;
        int count=0;
        while(count<n){
            for(int i=0;i<n;i++){
                if(mstSet[i]==0&&key[i]<min){
                    min=key[i];
                    minIndex=i;
                }
            }
            mstSet[minIndex]=1;
            count++;
            for(int i=0;i<n;i++){
                if(i!=minIndex&&adj[minIndex][i]!=0){
                    if(key[i]>adj[minIndex][i]){
                        key[i]=adj[minIndex][i];
                        parent[i]=minIndex;
                    }
                }
            }
            min=99999;
        }
        printMST(parent,adj,n);
    }

    public static void main(String args[]){

        int adj[][]={
            {
                0,2,0,6,0
            },
            {
                2,0,3,8,5
            },
            {
                0,3,0,0,7
            },
            {
                6,8,0,0,9
            },
            {
                0,5,7,9,0
            },
        };

        int v=5;

        prims p=new prims();
        p.prim(adj,v);

    }
}