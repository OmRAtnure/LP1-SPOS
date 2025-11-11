import java.util.Scanner;

class first{
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
            for(int j=0;j<n;j++) {
                if(process[i]<=block[j] && allocated[j]==0) {
                    index[i]=j;
                    allocated[j]=1;
                    block[j]=block[j]-process[i];
                    break;
                }
                if(j==n-1) {
                    index[i]=-1;
                }
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