import java.util.Scanner;

class best{
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

        for(int i=0;i<m;i++) {
            int best=-1;
            for(int j=0;j<n;j++) {
                if(allocated[j]==0 && block[j]>=process[i]) {

                    if(best==-1 || block[j]<block[best]) {
                        best=j;
                    }
                }
            }

            if(best==-1) {
                index[i]=-1;
            }else{
                index[i]=best;
                allocated[best]=1;
                block[best]=block[best]-process[i];
            }
        }

        System.out.println("Process no\tProcess Size\tBlock\tPartation");
        for(int i=0;i<m;i++)  {
            if(index[i]==-1) {
            System.out.println((i+1)+"\t"+process[i]+"\tNot allocated");    
            }
            System.out.println((i+1)+"\t\t"+process[i]+"\t\t"+(index[i]+1)+"\t\t"+block[index[i]]);
        }
    }
}