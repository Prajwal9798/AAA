import java.util.*;

class grahamscan{
   static Stack<point> points=new Stack<point>();

    point findLowestPoint(point p[],int n){
        int lx=p[0].x,ly=p[0].y;
        point p1=null;
        for(int i=1;i<n;i++){
            int x=p[i].x;
            int y=p[i].y;
            if(y<ly){
                lx=x;
                ly=y;
                p1=p[i];
            }
            if(y==ly){
                if(x<lx){
                    lx=x;
                    p1=p[i];
                }
            }
        }
        return p1;
    }

    double polarAngle(point p,point p1){
        double angle=0;
        if(p.x-p1.x!=0){
            angle=Math.toDegrees(Math.atan((double)(p.y-p1.y)/(double)(p.x-p1.x)));
        }
        else{
            if(p.y-p1.y>0){
                angle=90.0;
            }
            else if(p.y-p1.y<0){
                angle=-90.0;
            }
            else{
                angle=0;
            }
        }
        return angle;
    }

    void sortPolar(point p[],int n){
        point temp;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                if(p[j].angle>p[j+1].angle){
                    temp=p[j];
                    p[j]=p[j+1];
                    p[j+1]=temp;
                }
            }
        }
    }

    double distSquare(point p1,point p2){
        double d=0;
        d=Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2);
        return d;
    }
    
    point getFurthestPoint(point simPoints[],int n,point p1) {
    	point fp=null;
    	double dist=0;
    	for(int i=0;i<n;i++) {
    		if(distSquare(p1, simPoints[i])>dist) {
    			dist=distSquare(p1, simPoints[i]);
    			fp=simPoints[i];
    		}
    	}
    	return fp;
    }
    
    point[] modifiedPoints(point pq[],int n,point p1) {
    	point modP[]=new point[n];
    	Stack<point> simPoints=new Stack<point>();
    	point p2;
    	int count=0,countModP=0;
    	int visited[]=new int[n];
    	for(int i=0;i<n;i++) {
    		visited[i]=0;
    	}
    	for(int i=0;i<n;i++) {
    		count=0;
    		p2=pq[i];
    		for(int j=0;j<n;j++) {
    			if(p2.angle==pq[j].angle) {
    				if(visited[j]!=1) {
    					simPoints.push(pq[j]);
        				visited[j]=1;
        				count++;
    				}
    			}
    		}
    		point toBeAdded[]=new point[count];
			count=0;
			while(!simPoints.isEmpty()) {
				toBeAdded[count++]=simPoints.pop();
			}
			if(count>1) {
				modP[countModP++]=getFurthestPoint(toBeAdded, count, p1);
			}else if(count==1) {
				modP[countModP++]=toBeAdded[0];
			}
    	}
    	return modP;
    }
    
    int ori(point q,point r,point s,point p1) {
    	int val=(r.y-q.y)*(s.x-r.x)-(r.x-q.x)*(s.y-r.y);
    	if(val==0) return 0;
    	if(val>0) return 2;
    	else return 1;
    }

    public static void main(String args[]){

        grahamscan gs=new grahamscan();

        point p[]=new point[7];
        p[0]=new point(0,3);
        p[1]=new point(2,2);
        p[2]=new point(1,1);
        p[3]=new point(2,1);
        p[4]=new point(3,0);
        p[5]=new point(0,0);
        p[6]=new point(3,3);

        int n=7;

        point p1;

        p1=gs.findLowestPoint(p,n);
        point np[]=new point[7];
        int count=0;
        for(int i=0;i<n;i++){
            if(!p1.equals(p[i])){
                np[count++]=p[i];
            } 
        }
        n=count;

        for(int i=0;i<n;i++){
            np[i].angle=gs.polarAngle(np[i],p1);
        }

        gs.sortPolar(np,n);
        
//        System.out.println("Initial points");
//        for(int i=0;i<n;i++){
//            np[i].printWithAngle(p1);
//        }

        point nnp[]=new point[n];
        nnp=gs.modifiedPoints(np, n,p1);
        count=0;
        //System.out.println("Modified points");
        for(int i=0;i<n;i++){
        	if(nnp[i]!=null) {
        		//nnp[i].printWithAngle(p1);
        		count++;
        	}
        }
        n=count;
        //System.out.println(n);
        if(n<2) {
        	System.out.println("Convex Hull not possibe");
        	return;
        }
        points.push(p1);
        points.push(nnp[0]);
        points.push(nnp[1]);
        point nextToTop,top,pt;
        for(int i=2;i<n;i++) {
        	pt=nnp[i];
        	top=points.pop();
        	nextToTop=points.pop();
        	points.push(nextToTop);
        	points.push(top);
        	while(gs.ori(nextToTop, top, pt,p1)==2) {
        		points.pop();
        		top=points.pop();
        		nextToTop=points.pop();
        		points.push(nextToTop);
        		points.push(top);
        	}
        	points.push(pt);
        }
        while(!points.isEmpty()) {
        	System.out.println(points.pop().print());
        }
    }
}

class point{
    int x,y;
    double angle;
    point(int i,int j){
        this.x=i;
        this.y=j;
        this.angle=0;
    }

    String print(){
        String s=new String();
        s="("+Integer.toString(this.x)+","+Integer.toString(this.y)+")";
        return s;
    }

    void printWithAngle(point p1){
        String s=new String();
        String s1=new String();
        String s2=new String();
        s1=p1.print();
        s2=this.print();
        s="Point "+s2+" - Polar angle wrt "+s1+" is "+Double.toString(this.angle);
        System.out.println(s);
    }

    boolean equals(point p){
        boolean value=false;
        if(this.x==p.x && this.y==p.y){
            value=true;
        }
        return value;
    }
}
