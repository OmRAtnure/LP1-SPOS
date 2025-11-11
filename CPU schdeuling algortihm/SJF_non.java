import java.util.Scanner;

class SJF_non{
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter numbr of process");
        int n=sc.nextInt();

        int[] at=new int[n];
        int[] bt=new int[n];
        int[] ct=new int[n];
        int[] wt=new int[n];
        int[] tat=new int[n];
        int[] pid=new int[n];
        int[] completed=new int[n];
        
        for(int i=0;i<n;i++) {
            pid[i]=i+1;

            System.out.println("Enter arrival time for process "+pid[i]+": ");
            at[i]=sc.nextInt();

            System.out.println("Enter brust time for process "+pid[i]+": ");
            bt[i]=sc.nextInt();
        }

        int time=0;
        int count=0;
        while(count<n) {
            int minIndex=-1;
            int minBrust=Integer.MAX_VALUE;

            for(int i=0;i<n;i++) {
                if(at[i]<=time && completed[i]==0 & bt[i]<minBrust ) {
                    minIndex=i;
                    minBrust=bt[i];
                }
            }

            if(minIndex==-1) {
                time++;
            }else{
                ct[minIndex]=time+bt[minIndex];
                tat[minIndex]=ct[minIndex]-at[minIndex];
                wt[minIndex]=tat[minIndex]-bt[minIndex];
                completed[minIndex]=1;
                time=time+bt[minIndex];
                count++;

            }
        }

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i<n;i++) {
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
        
        
    }
}