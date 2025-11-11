import java.util.Scanner;

class FCFSnew{
    public static void main(String []args) {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of process: ");
        int n=sc.nextInt();

        int [] pid=new int[n];
        int [] at=new int[n];
        int [] bt=new int[n];
        int [] ct=new int[n];
        int [] tat=new int[n];
        int [] wt=new int[n];
        int [] completed=new int[n];
        
        for(int i=0;i<n;i++) {
            pid[i]=i+1;
            System.out.println("Enter arrival time for process P"+pid[i]);
            at[i]=sc.nextInt();

            System.out.println("Enter brust time for process P"+pid[i]);
            bt[i]=sc.nextInt();
        }

        int count=0;
        int time=0;
        while(count<n) {
            int index=-1;
            int leastArrival=Integer.MAX_VALUE;

            for(int i=0;i<n;i++) {
                if(at[i]<=time && completed[i]==0 && at[i]<leastArrival) {
                    index=i;
                    leastArrival=at[i];
                }
            }

            if(index==-1) {
                time++;
            }else{
                ct[index]=time+bt[index];
                tat[index]=ct[index]-at[index];
                wt[index]=tat[index]-bt[index];
                completed[index]=1;
                time=time+bt[index];
                count++;
                
            }
        }

        System.out.println("Process\tat\tbt\tct\ttat\twt");
        for(int i=0;i<n;i++) {
            System.out.println("P"+pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
    }
}