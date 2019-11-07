#include<stdio.h>
#include<stdlib.h>
#include<time.h>

void merge(int arr[],int l,int m,int r){
    int i,j,k=l;
    int n1=m-l+1;
    int n2=r-m;

    int L[n1],R[n2];

    for(i=0;i<n1;i++){
        L[i]=arr[l+i];
    }
    for(j=0;j<n2;j++){
        R[j]=arr[m+1+j];
    }
    i=0;
    j=0;

    while(i<n1&&j<n2){
        if(L[i]<R[j]){
            arr[k]=L[i];
            i++;
        }
        else
        {
            arr[k]=R[j];
            j++;
        }
        k++;
    }

    while(i<n1){
        arr[k]=L[i];
        i++;
        k++;
    }

    while(j<n2){
        arr[k]=R[j];
        j++;
        k++;
    }
}

void mergeSort(int arr[],int l,int r){
    if(l<r){
        int m=l+(r-l)/2;
        mergeSort(arr,l,m);
        mergeSort(arr,m+1,r);
        merge(arr,l,m,r);
    }
}

void main(){
    int arr[50000];
    int i,size;
    for(i=0;i<50000;i++){
        arr[i]=rand()%20000;
    }
    size=sizeof(arr)/sizeof(arr[0]);
    clock_t t1=clock();
    mergeSort(arr,0,size-1);
    clock_t t2=clock();  
    double time=((double)(t2-t1));
    printf("Time elapsed : %lf\n",time);
}