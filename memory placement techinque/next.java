import java.util.Scanner;

class next{
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);
        int n;//NUmber of blocks
        int m;//NUmber of process

        System.out.print("Enter number of blocks: ");
        n=sc.nextInt();

        int[] block=new int[n];
        System.out.println("Enter Size of blocks:");
        for(int i=0;i<n;i++) {
            System.out.print("Block:"+(i+1)+": ");
            block[i]=sc.nextInt();
        }
        int []allocated=new int[n];

        System.out.print("Enter number of process: ");
        m=sc.nextInt();

        int[] process=new int[n];
        System.out.println("Enter Size of process:");
        for(int i=0;i<m;i++) {
            System.out.print("Process "+(i+1)+": ");
            process[i]=sc.nextInt();
        }

        
        int [] index=new int[m];

        int last=0;
        for(int i=0;i<m;i++) {
            int j=last;
            boolean allocatedFlag=false;
        
        int count=0;
        while(count<n) {
            if(allocated[j]==0 && block[j]>=process[i]) {
                allocated[j]=1;
                index[i]=j;
                last=(j+1)%n; //next search start from here
                allocatedFlag=true;
                block[j]=block[j]-process[i];
                break;
            }
            count++;
            j=(j+1)%n;
        }

        if(!allocatedFlag) {
            index[i]=-1;
        }
     
    }

     System.out.println("Process no\tProcess Size\tBlock\tPartation");
        for(int i=0;i<m;i++)  {
            if(index[i]==-1) {
            System.out.println((i+1)+"\t"+process[i]+"\tNot allocated");    
            }
            System.out.println((i+1)+"  \t  "+process[i]+"  \t  "+(index[i]+1)+"  \t  "+block[index[i]]);
        }
}

}