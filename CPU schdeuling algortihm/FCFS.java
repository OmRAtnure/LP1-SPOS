import java.util.Scanner;

class FCFS{
    public static void main(String []args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter number of procss: ");
        int n=sc.nextInt();

        int[] pid=new int[n];
        int[] at=new int[n];
        int[] bt=new int[n];
        int[] ct=new int[n];
        int[] tat=new int[n];
        int[] wt=new int[n];

        //Take input for arrival and brust time for each process
        for(int i=0;i<n;i++) {
            pid[i]=i+1;
            System.out.println("Enter arrival time for process "+(i+1)+": ");
            at[i]=sc.nextInt();
            System.out.println("Enter brust time for process "+(i+1)+": ");
            bt[i]=sc.nextInt();
        }

        //sort using arrivai time for FCFC basisi
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(at[i]>at[j]) {
                    int temp;

                    //swap pid;
                    temp=pid[i];
                    pid[i]=pid[j];
                    pid[j]=temp;

                    //swap at;
                    temp=at[i];
                    at[i]=at[j];
                    at[j]=temp;

                    //swap bt;
                    temp=bt[i];
                    bt[i]=bt[j];
                    bt[j]=temp;
                }
            }
        }

        //calculatin completino time
        ct[0]=at[0]+bt[0];
        for(int i=1;i<n;i++) {
            if(at[i]>ct[i-1]) {
                ct[i]=at[i]+bt[i];//CPU was idelbefore this proces started
            } else {
                ct[i]=ct[i-1]+bt[i];
            }
        }

        //calculatin turnaround time and waiting time
        int totalTat=0;
        int totalWt=0;
        for(int i=0;i<n;i++) {
            tat[i]=ct[i]-at[i];
            totalTat=totalTat+tat[i];

            wt[i]=tat[i]-bt[i];
            totalWt=totalWt+wt[i];
        }

        //Display result
        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i<n;i++) {
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }

        System.out.println("Average turnaround time "+(totalTat/n));
        System.out.println("Average waiting time "+(totalWt/n));

    }
}