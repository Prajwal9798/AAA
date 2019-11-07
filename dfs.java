import java.util.*;

class dfs{

    static int visited[];
    static int count;
    static int v;

    dfs(int n){
        visited=new int[n];
        for(int i=0;i<n;i++){
            visited[i]=0;
        }
        count=0;
        v=n;
    }

    void dfsTraversal(int adj[][],int vertex){
        if(visited[vertex]==0){
            System.out.println(vertex);
            visited[vertex]=1;
            count++;
            if(count<v){
                for(int i=0;i<v;i++){
                    if(visited[i]!=1&&adj[vertex][i]==1){
                        dfsTraversal(adj,i);
                    }
                }
            }
        }
    }

    public static void main(String args[]){

        int adj[][];
        int n;
        int start;

        Scanner in =new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        n=in.nextInt();
        adj=new int[n][n];

        System.out.println("Enter the adjacency matrix for the graph");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                adj[i][j]=0;
                adj[i][j]=in.nextInt();
            }
        }
        System.out.println("Enter the start vertex");
        start=in.nextInt();
        
        in.close();

        dfs traversal=new dfs(n);
        traversal.dfsTraversal(adj,start);
    }
}