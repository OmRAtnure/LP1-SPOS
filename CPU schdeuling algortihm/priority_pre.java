import java.util.Scanner;

class priority_pre{
    public static void main(String []args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("ENter number of process: ");
        int n=sc.nextInt();

        int [] pid=new int[n];
        int [] at=new int[n];
        int [] bt=new int[n];
        int [] priority=new int[n];
        int [] ct=new int[n];
        int [] tat=new int[n];
        int [] wt=new int[n];
        int [] completed=new int[n];
        int [] remaining=new int[n];
  
        for(int i=0;i<n;i++) {
            pid[i]=i+1;
            System.out.println("Enter arrivaal time for process "+pid[i]+": ");
            at[i]=sc.nextInt();

            System.out.println("Enter brust time for process "+pid[i]+": ");
            bt[i]=sc.nextInt();
            remaining[i]=bt[i];

            System.out.println("Enter priority for process "+pid[i]+": ");
            priority[i]=sc.nextInt();
        }

        int time=0;
        int count=0;
        while(count<n) {
            int index=-1;
            int best=Integer.MIN_VALUE;
            for(int i=0;i<n;i++) {
                if(at[i]<time && completed[i]==0 && priority[i]>best) {
                    index=i;
                    best=priority[i];
                }
            }

            if(index==-1) {
                time++;
            }else{
                remaining[index]--;
                if(remaining[index]==0) {
                    ct[index]=time;
                    tat[index]=ct[index]-at[index];
                    wt[index]=tat[index]-bt[index];
                    completed[index]=1;
                    count++;
                }
                time++;
            }
        }

        System.out.println("process\tAT\tBT\tpriority\tCT\tTAT\tWT");
        for(int i=0;i<n;i++) {
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+priority[i]+"\t"+ ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }

    }
}