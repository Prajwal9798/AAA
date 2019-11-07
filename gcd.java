import java.util.*;

class gcd{

    int x,y;
    int a,b;

    gcd(int a,int b){
        this.a=a;
        this.b=b;
    }

    int gcdExtended(int a,int b,int x,int y){
        if(a==0){
            x=0;
            y=1;
            return b;
        }
        int x1=1,y1=1;
        int g=gcdExtended(b%a,a,x1,y1);

        x=y1-(b/a)*x1;
        y=x1;

        return g;
    }

    public static void main(String args[]){
        int n1,n2;
        int GCD;

        Scanner in=new Scanner(System.in);
        n1=in.nextInt();
        n2=in.nextInt();
        in.close();
        gcd g=new gcd(n1,n2);
        GCD=g.gcdExtended(n1,n2,1,1);
        System.out.println("GCD = "+GCD);
    }
}