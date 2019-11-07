#include <stdio.h>
#include <string.h>
#include <math.h>

int hash(int prehash,char str[],int patlen,int i){
    int k;
    int j=i+patlen;
    int hashval=0;
    if(prehash==0){
        for(k=0;k<j;k++){
            hashval+=(int)str[k];
        }
    }
    else
    {
        hashval=0;
        if(j<strlen(str)){
            hashval=prehash-(int)str[i]+(int)str[j];
        }
    }
    return hashval;
}

void main(){
    char str[50];
    char pat[10];
    gets(str);
    gets(pat);
    int patl=strlen(pat);
    int prehash=0,hashval=0;
    int i,m;
    int flag=1;
    int pathash=hash(0,pat,patl,0);
    for(i=0;i<strlen(str);i++){
        hashval=hash(prehash,str,patl,i);
        prehash=hashval;
        if(prehash==pathash){
            for(m=i;m<i+patl;m++){
                if(str[m]==pat[m-i]){
                    continue;
                }else{
                    flag=0;
                    break;
                }
            }
            if(flag==1){
                printf("Pattern found\n");
            }
        }
    }
}
