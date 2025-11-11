import java.util.Scanner;

class SJF_PRI{
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter number of process: ");
        int n=sc.nextInt();

        int [] pid=new int[n];
        int [] at=new int[n];
        int [] bt=new int[n];
        int [] ct=new int[n];
        int [] tat=new int[n];
        int [] wt=new int[n];
        int [] rt=new int[n];
        int [] remaining=new int[n];
        int [] completed=new int[n];
        
        for(int i=0;i<n;i++) {
            pid[i]=i+1;
            System.out.println("Entre arrivaial time for process "+pid[i]+" : ");
            at[i]=sc.nextInt();

            System.out.println("Entre brust time for process "+pid[i]+" : ");
            bt[i]=sc.nextInt();
            remaining[i]=bt[i];
            
        }

        int count=0;
        int time=0;
        while(count<n) {
            int minIndex=-1;
            int minRem=Integer.MAX_VALUE;

            for(int i=0;i<n;i++) {
                if(at[i]<=time && remaining[i]<minRem && completed[i]==0) {
                    minIndex=i;
                    minRem=remaining[i];
                }
            }

            if(minIndex==-1) {
                time++;
            } else{
                if(remaining[minIndex]==bt[minIndex]) {
                    rt[minIndex]=time-at[minIndex];
                }
                time++;
                remaining[minIndex]--;
                if(remaining[minIndex]==0) {
                    completed[minIndex]=1;
                    count++;
                    ct[minIndex]=time;
                    tat[minIndex]=ct[minIndex]-at[minIndex];
                    wt[minIndex]=tat[minIndex]-bt[minIndex];

                }
            }
        }

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT\tRT");
        for(int i=0;i<n;i++) {
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]+"\t"+rt[i]);
        }
    }
}