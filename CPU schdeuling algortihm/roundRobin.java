import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class roundRobin{
    public static void main(String []args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter number of process for roundRobin: ");
        int n=sc.nextInt();

        Queue<Integer> ready=new LinkedList<>();
        int [] pid=new int[n];
        int [] at=new int[n];
        int [] bt=new int[n];
        int [] rt=new int[n];
        int [] ct=new int[n];
        int [] tat=new int[n];
        int [] wt=new int[n];
        int [] isQuead=new int[n];
        int [] remaining=new int[n];

  
        for(int i=0;i<n;i++) {
            pid[i]=i+1;
            System.out.println("Enter arrivaal time for process "+pid[i]+": ");
            at[i]=sc.nextInt();

            System.out.println("Enter brust time for process "+pid[i]+": ");
            bt[i]=sc.nextInt();
            remaining[i]=bt[i];

        }

        int time=0;
        int count=0;
        int index=-1;
        while(count<n) {
            // boolean executed=false;

            for(int i=0;i<n;i++) {
                if(at[i]<=time && remaining[i]>0 && index!=i && isQuead[i]==0) {
                    ready.offer(i);
                    isQuead[i]=1;
                }
            }

            if(index!=-1 && remaining[index]>0) {
                ready.offer(index);
                isQuead[index]=1;
            }

            if(ready.isEmpty()) {
                time++;
            }else{
                index=ready.peek();
                ready.poll();
                isQuead[index]=0;
                if(remaining[index]==bt[index]) {
                    rt[index]=time-at[index];
                }
                if(remaining[index]>2) {
                    time=time+2;
                    remaining[index]=remaining[index]-2;
                }else{
                    time=time+remaining[index];
                    remaining[index]=0;
                    ct[index]=time;
                    tat[index]=ct[index]-at[index];
                    wt[index]=tat[index]-bt[index];
                    count++;
                }
            }
        }

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT\tRT");
        for(int i=0;i<n;i++) {
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]+"\t"+rt[i]);
        }


    }
}