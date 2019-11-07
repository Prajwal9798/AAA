import java.util.*;

class bfs{

    static int visited[];

    bfs(int n){
        visited=new int[n];
        for(int i=0;i<n;i++){
            visited[i]=0;
        }
    }
    
    void bfs(int[][] adj,int n,int start){
        Queue q=new Queue(n);
        q.insert(start);
        visited[start]=1;

        while(!q.isEmpty()){
            int current=q.delete();

            System.out.print(current+" ");
            
            for(int i=0;i<n;i++){
                if(adj[current][i]==1){
                    int neighbour = i;
                    if(visited[neighbour]!=1){
                        q.insert(neighbour);
                        visited[neighbour]=1;
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        int adj[][];
        int n=4;

        System.out.println("Enter the number of vertices");
        n=in.nextInt();
    
        adj=new int[n][n];
        int start;

        System.out.println("Enter the adj matrix");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                adj[i][j]=0;
                adj[i][j]=in.nextInt();
            }
        }
        
        System.out.println("Enter the start node");
        start=in.nextInt();

        in.close();

        System.out.println("\nBFS Traversal is : ");
        bfs b=new bfs(n);

        b.bfs(adj,n,start);
    }
}

class Queue{
    int queue[];
    int front=0;
    int rear=-1;

    Queue(int n){
        queue=new int[n];
    }

    void insert(int item){
        if(rear<queue.length){
            queue[++rear]=item;
        }
        else{
            System.out.println("Queue full");
        }
    }

    int delete(){
        int item=-1;
        if(rear!=-1){
            item=queue[front++];
            if(front==rear+1){
                front=0;
                rear=-1;
            }
        }
        else{
            System.out.println("Queue empty");
        }
        return item;
    }

    boolean isEmpty(){
        boolean flag=false;
        if(rear==-1){
            flag=true;
        }
        return flag;
    }
}